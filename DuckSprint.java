import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Date;
import javax.swing.ImageIcon;


public class DuckSprint extends JComponent implements KeyListener, MouseListener, MouseMotionListener
{
    //instance variables
    private int WIDTH;
    private int HEIGHT;
    private Cloud cloud, cloud2;
    private Duck Mduck, bDuck;    
    private Garbage gbg, gbg2;
    private int HourS, minS, secondS, milS;
    private boolean alive;
    private ImageIcon[] imgIcons;
    private long startTime, currentTime;
    
    //Default Constructor
    public DuckSprint()
    {
        //initializing instance variables
        WIDTH = 1000;
        HEIGHT = 600;
        
        cloud = new Cloud(WIDTH-500, 50, 100, Color.GRAY, WIDTH);
        cloud2 = new Cloud(WIDTH-30, 0, 100, Color.GRAY, WIDTH);
        
        Mduck = new Duck(50, 350, 150, Color.black,HEIGHT );
        //bDuck = new Duck(50, 200, Color.YELLOW, HEIGHT);
                
        gbg = new Garbage(1000, 400, 100, Color.GRAY, 1000, Mduck);
        
        alive = true;
        
        imgIcons =  new ImageIcon[2];
        imgIcons[0] = new ImageIcon(Cloud.class.getResource("BLUESKY.jpeg"));
        imgIcons[1] = new ImageIcon(Cloud.class.getResource("GRASS.jpg"));
        
        Date today = new Date();  
        HourS = today.getHours();
        minS=today.getMinutes();
        secondS = today.getSeconds();      
        
        //time stuff Suriel
        startTime = System.currentTimeMillis();
        
        //Setting up the GUI
        JFrame gui = new JFrame(); //This makes the gui box
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Makes sure program can close
        gui.setTitle("Duck Sprint"); //This is the title of the game, you can change it
        gui.setPreferredSize(new Dimension(WIDTH + 5, HEIGHT + 30)); //Setting the size for gui
        gui.setResizable(false); //Makes it so the gui cant be resized
        gui.getContentPane().add(this); //Adding this class to the gui

         /*If after you finish everything, you can declare your buttons or other things
          *at this spot. AFTER gui.getContentPane().add(this) and BEFORE gui.pack();
          */

        gui.pack(); //Packs everything together
        gui.setLocationRelativeTo(null); //Makes so the gui opens in the center of screen
        gui.setVisible(true); //Makes the gui visible
        gui.addKeyListener(this);//stating that this object will listen to the keyboard
        gui.addMouseListener(this); //stating that this object will listen to the Mouse
        gui.addMouseMotionListener(this); //stating that this object will acknowledge when the Mouse moves

    }    
    //All your UI drawing goes in here
    public void paintComponent(Graphics g)
    {       
        
        //Drawing a Cyan Rectangle to be the background
        /*g.setColor(Color.CYAN);
        g.fillRect(0, 0, WIDTH, HEIGHT);*/
        Graphics2D  g2d = (Graphics2D)g;
        Image img = imgIcons[0].getImage();
        g2d.drawImage(img, 0, 0, WIDTH, HEIGHT,null);
        
        //Drawing the ground
        /*g.setColor(Color.GREEN);
        g.fillRect(0, 500, WIDTH,100);*/
        img = imgIcons[1].getImage();
        g2d.drawImage(img, 0, 500, WIDTH, 100,null);
        //
        //Drawing CLOUD
        cloud.drawSelf(g);
        cloud2.drawSelf(g);

         //Drawing the autonomous garbage
        gbg.drawSelf(g);
        
        //Drawing the user-controlled duck
        Mduck.drawSelf(g);
        //bDuck.drawSelf(g);
        
        //Drawing time
        Date today = new Date();  
        int HourN = today.getHours();
        int minN =today.getMinutes();
        int secN = today.getSeconds();   
        
        int sec = (int)currentTime/1000;
        
        
        /*int endH, endM, endS;
        endH = (HourN-HourS);
        endM = (minN-minS);
        endS = sec;
        
        if(secN > secondS)
        {
            sec = secN-secondS;
        }
        if(secondS > secN)
        {
            sec = secondS - secN;
        }*/
        
        
        
                                
        String time = "time:" + sec/60 + ":" + sec%60;
        if(sec%60 < 10)//1 digit
            time = "time:" + sec/60 + ":0" + sec%60;
        
          
        //GAMEOVER
        if(alive == false)
        {
            Font f = new Font("TimesNewRoman", Font.BOLD, 50);
            g.setFont(f);   
            g.setColor(Color.RED);        
            g.drawString("GAME OVER", WIDTH/3, HEIGHT/3); 
            
            g.drawString("X_X",450, HEIGHT/2);
            
            f = new Font("Arial", Font.BOLD, 50);
            g.setFont(f);   
            g.setColor(Color.RED);        
            g.drawString(time, 380, 100);
        }
        else
        {            
            currentTime = System.currentTimeMillis() - startTime;
            Font f = new Font("TimesNewRoman", Font.BOLD, 50);
            g.setFont(f);   
            g.setColor(Color.BLUE);        
            g.drawString("DUCK SPRINT", WIDTH/3, HEIGHT/3); 
            
            f = new Font("Arial", Font.BOLD, 50);
            g.setFont(f);   
            g.setColor(Color.WHITE);        
            g.drawString(time, 380, 100);
        }

    }
    public void loop()
    {
        //making the cloud move
        cloud.act();
        cloud2.act();
        
        //handling when the duck jumps
        Mduck.move();
        //bDuck.move();

        //moving the garbage
        gbg.act(WIDTH, HEIGHT);
        

        //dead duck
        if(gbg.detectCollision(Mduck) == true)
        {
            cloud.setVX(0);
            cloud2.setVX(0);
            Mduck.die();
            alive = false;
        }          
            
        
        //Do not write below this
        repaint();
    }
    //These methods are required by the compiler.  
    //You might write code in these methods depending on your goal.
    public void keyPressed(KeyEvent e) 
    {
        int key = e.getKeyCode();
        System.out.println("key = " + key);
        
        if(key == 32)
        {
            Mduck.jump();
            //bDuck.jump();
        }
        if(key == 40)
        {
            Mduck.crouch();
            //bDuck.crouch();
        }
        
    }
    public void keyReleased(KeyEvent e) 
    {        
        
    }
    public void keyTyped(KeyEvent e) 
    {
    }
    public void mousePressed(MouseEvent e)
    {
    }
    public void mouseReleased(MouseEvent e)
    {
    }
    public void mouseClicked(MouseEvent e)
    {
    }
    public void mouseEntered(MouseEvent e)
    {
    }
    public void mouseExited(MouseEvent e)
    {
    }
    public void mouseMoved(MouseEvent e)
    {
    }
    public void mouseDragged(MouseEvent e)
    {
    }
    public void start(final int ticks){
        Thread gameThread = new Thread(){
            public void run(){
                while(true){
                    loop();
                    try{
                        Thread.sleep(1000 / ticks);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };	
        gameThread.start();
    }

    public static void main(String[] args)
    {
        DuckSprint g = new DuckSprint();
        g.start(60);
    }
    
}
