import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//imports for drawing Images
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Duck
{
    private int x;
    private int y;
    private int vx;
    private int vy;
    private int diam;
    private int h;
    private Color col;    
    private boolean jumping;
    private boolean crouch;
    private boolean dead;
    private ImageIcon[] imgIcons;
    private ImageIcon imgIcon;
    private int ImgTimer, ImgIndex;
    
    public Duck(int xCoor, int yCoor, int d, Color c, int height)
    {
        x = xCoor;        
        y = yCoor;
        h = height;
        diam = d;
        col = c;        
        vy = 0;
        jumping = false;
        crouch = false;
        dead = false;
        imgIcons = new ImageIcon[3];//4 frames for the animation
        imgIcons[0] = new ImageIcon(Duck.class.getResource("DuckLeftBack.png"));
        imgIcons[1] = new ImageIcon(Duck.class.getResource("DuckRightF.png"));
        imgIcons[2] = new ImageIcon(Duck.class.getResource("DuckLeftBack.png"));
        
        imgIcon = new ImageIcon(Duck.class.getResource("DuckDead.png"));
        
        ImgTimer = 0;
        ImgIndex = 0;
    }    
    public String toString()
    {
        String output;
        output = "Duck at(" + x +", " + y + ") with diam " + diam;
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
    public void setY( int ty )
    {
        y+=ty;
    }
    public void setVY( int VY )
    {
        vy = VY;
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
    
    public void jump()
    {
        int jumplimit = y-150;
        if(y+diam >= 500 && y >= jumplimit)
        {
            vy = -20 ;
            jumping = true;
        }
                
    }
    public void crouch()
    {
        //diam /= 2;
    }
    public void die()
    {
        dead = true;
        vy = 0;
    }

    public void move()
    {
        
        if(dead==false)
        {
            y+= vy;
        }
                
        if(jumping == true && y+diam <= 500 /*&& y >=50*/)
        {
            vy++;            
        }
        else
        {
            //grounded
            vy = 0;
            y = 350;
        }
        
        /*if(crouch == true)
        {
            vy = 0;
            diam = diam;          
        }*/
        
        
    }               
    public void drawSelf(Graphics g)    
    {
        ImgTimer++;
        Graphics2D  g2d = (Graphics2D)g;
        //g.setColor(col);        
        //g.fillOval(x, y, diam, diam);       
       
        Image img;
        if(dead == false)
        {
            img = imgIcons[ImgIndex].getImage();
            g2d.drawImage(img, x, y, diam, diam, null);
        }
             
        
        if(ImgTimer%5 == 0 && dead == false )//represents how fast the img will go, higher # slower frames
        {            
            ImgIndex++;
            if(ImgIndex == imgIcons.length)//if we reach the end of the array
            {
                ImgIndex = 0;
            }
        }        
       else if(dead == true)
       {
           img = imgIcon.getImage();
           g2d.drawImage(img, x, y, diam, diam, null);
       }
       
    }
    
    
}
