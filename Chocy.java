import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.*;

public class Chocy extends PowerUps{
    
    BufferedImage chocy;
    double x, y, width, height;

    public Chocy(double x, double y){
        this.x = x;
        this.y = y;
        width = 15;
        height = 22;

        getPowerUpImage();
    }

    public void getPowerUpImage(){
        try{
            chocy = ImageIO.read(getClass().getResourceAsStream("/resources/chocy.png"));
        }catch(IOException e){

        }
    }

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

    public void draw(Graphics2D g2d){
        BufferedImage image = chocy;
        g2d.drawImage(image, (int)x, (int)y, (int)width, (int)height, null);
    }
}
