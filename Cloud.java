import java.awt.Graphics;
import java.awt.Color;

//imports for drawing Images
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Cloud
{
    private int x;
    private int y;
    private int vx;
    private int vy;
    private int diam;
    private Color col;
    private int w, h;
    private ImageIcon imgIcon;
    
    public Cloud(int xCoor, int yCoor, int d, Color c, int width)
    {
        x = xCoor;
        y = yCoor;
        diam = d;
        col = c;         
        vx = -10;
           
        imgIcon = new ImageIcon(Cloud.class.getResource("Cloud.png"));
        
        
        //gui 
        w = width;        
    }
    public String toString()
    {
        String output;
        output = "Cloud at(" + x +", " + y + ") with diam " + diam;
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
    public void act()
    {   
        x+=vx;       
        System.out.println(x);
    }
    public void setVX(int vX)
    {
        vx = vX;
    }
    public void setVY(int vY)
    {
        vy = vY;
    }
    public void drawSelf(Graphics g)    
    {
        Graphics2D  g2d = (Graphics2D)g;
        Image img = imgIcon.getImage();
        g2d.drawImage(img, x, y, diam, diam, null);
        //g.setColor(col);
        //g.fillOval(x, y, diam, diam);
        
        if((x+diam)< 0)
        {
            x = w-50;
            
            int rand = (int)(Math.random()*3+1);
            if(rand == 1)
            {
                vx = -10;
            }
             else if(rand == 2)
            {
                vx = -12;
            } 
            else
            {
                vx = -14;
            }        
        }
    }
}
   
