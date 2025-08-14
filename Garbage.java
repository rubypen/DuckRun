import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Garbage
{
    private int x;
    private int y;
    private int vx;
    private int vy;
    private int diam, w;
    private Color col;    
    private Duck ducky;
    private ImageIcon[] imgIcons;    
    private int ImgTimer, ImgIndex;
    
    public Garbage(int xCoor, int yCoor, int d, Color c, int width, Duck duck)
    {
        x = xCoor;
        y = yCoor;
        diam = d;
        col = c;
        int rand = (int)(Math.random()*3+1);
        vx = -10;
        vy = 5;
        w = width;
        ducky = duck;
     
        imgIcons = new ImageIcon[4];//4 frames for the animation
        imgIcons[0] = new ImageIcon(Duck.class.getResource("Can1.png"));
        imgIcons[1] = new ImageIcon(Duck.class.getResource("Can2.png"));
        imgIcons[2] = new ImageIcon(Duck.class.getResource("Can3.png"));
        imgIcons[3] = new ImageIcon(Duck.class.getResource("Can4.png"));
        
        ImgTimer = 0;
        ImgIndex = 0;
        
    }
    public String toString()
    {
        String output;
        output = "Garbage at(" + x +", " + y + ") with diam " + diam;
        return output;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getDiam()
    {
        return diam;
    }
    private double distance(int x1, int y1, int x2, int y2)
    {
        double output;
        output = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        return output;
    }
    public int getCenterX()
    {
        return x+diam/2;
    }
    public int getCenterY()
    {
        return y+diam/2;
    }
    public void act(int w, int h)
    {
        if(this.detectCollision(ducky) == true)
        {
            vx=0;
            ducky.die();
        }
        else      
        {
            x+=vx;  
        }
             
        System.out.println(x);       
        
    }
    public void drawSelf(Graphics g)    
    {
        ImgTimer++;
        Graphics2D  g2d = (Graphics2D)g;
        Image img = imgIcons[ImgIndex].getImage();
        g2d.drawImage(img, x, y, diam, diam, null);
        
        if(ImgTimer%20 == 0 && vx != 0)//represents how fast the img will go, higher # slower frames
        {            
            ImgIndex++;
            if(ImgIndex == imgIcons.length)//if we reach the end of the array
            {
                ImgIndex = 0;
            }
        }        
        
        
        
        //g.setColor(col);
        //g.fillOval(x, y, diam, diam);
                
        
        if((x+vx)< 0)
        {
            x = w-50;
            
            int rand = (int)(Math.random()*4+1);
            if(rand == 1)
            {
                vx = -14;
            }   
            else if(rand == 2)
            {
                vx = -16;
            } 
            else if(rand == 3)
            {
                vx = -18;
            } 
            else if(rand == 4)
            {
                vx = -20;
            }           
            else
            {
                vx = -22;
            } 
        }

    }
    public boolean detectCollision(Duck duck)
    {     
        boolean output = false;
        
        int radius, centerX, centerY;
        
        ducky = duck;
        
        radius = diam/2;
        
        int nextX = x + vx;
        int nextY = y +vy;
        
        centerX = nextX+radius;
        centerY = nextY+radius;
        
        int duckCX = ducky.getCenterX();
        int duckCY = ducky.getCenterY();
        
        
        if(distance(centerX, centerY , duckCX, duckCY) < radius)
        {
            output = true;
        }
        else
        {
            output = false;
        }

        
        return output;
    }
    

}