import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;

public class PlatformOne extends Platforms{
    
    public BufferedImage platform1;
    private int x, y, w, h;

    public PlatformOne(){
        setImages();
        x = 582;
        y = 331;
        w = 160;
        h = 33;
    }

    public void setImages(){
        try{
            platform1 = ImageIO.read(getClass().getResourceAsStream("/resources/platform1.png"));
        }catch(IOException e){
           

        }
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getWidth(){
        return w;
    }

    public int getHeight(){
        return h;
    }

    public void draw(Graphics2D g2d){
        BufferedImage img = platform1;
        g2d.drawImage(img, x, y, w, h, null);
    }
}
