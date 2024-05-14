import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.*;

public class Flag extends Entity{
    
    BufferedImage flag;
    double x, y, width, height;

    public Flag(){
        x = 745;
        y = 56;
        width = 26;
        height = 27;

        getPowerUpImage();
    }

    public void getPowerUpImage(){
        try{
            flag = ImageIO.read(getClass().getResourceAsStream("/resources/flag.png"));
        }catch(IOException e){

        }
    }

    /*public void addSpeed(double speedx){

    }*/

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

    public void draw(Graphics2D g2d){
        BufferedImage image = flag;
        g2d.drawImage(image, (int)x, (int)y, (int)width, (int)height, null);
    }
}