import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    public double x, y, w, h, speed;

    //public BufferedImage player, enemy;
    //public Rectangle hitbox;

    abstract void draw(Graphics2D g2d);

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

    public double getTop(){
        double top = y;
        return top;
    }

    public double getBottom(){
        double bottom = y + h;
        return bottom;
    }

    public double getRight(){
        double right = x + w;
        return right;
    }

    public double getLeft(){
        double left = x;
        return left;
    }
    //abstract void update();

}
