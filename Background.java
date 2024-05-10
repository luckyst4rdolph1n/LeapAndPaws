import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Background {
    
    public BufferedImage bg;
    private int bgx, bgy, bgw, bgh;
    public String path;

    public Background(String path){
        bgx = 0;
        bgy = 0;
        bgw = 800;
        bgh = 600;
        this.path = path;
        setBackgroundImage();
    }

    public void setBackgroundImage(){
        try{
            bg = ImageIO.read(getClass().getResourceAsStream(path));
        }catch(IOException e){

        }   
    }

    public void draw(Graphics2D g2d){
        BufferedImage image = bg;
        
        g2d.drawImage(image, bgx, bgy, bgw, bgh, null);
    }
    
}
