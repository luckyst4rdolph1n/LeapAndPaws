/*
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
*/
import java.awt.*;

public abstract class Platforms {
   
    abstract void setImages();

    abstract int getX();

    abstract int getY();

    abstract int getWidth();

    abstract int getHeight();

    abstract void draw(Graphics2D g2d);

    
}
