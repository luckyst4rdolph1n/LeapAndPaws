import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class PowerUps extends Entity{
    public double x, y;

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getWidth(){
        return w;
    }

    public double getHeight(){
        return h;
    }
    
    abstract void draw(Graphics2D g2d);

}