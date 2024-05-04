import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BaseGround extends Platforms{

    public BufferedImage baseGround;
    private int x, y, w, h;

    public BaseGround(){
        setImages();
        x = 0;
        y = 570;
        w = 800;
        h = 31;
    }

    public void setImages(){
        try{
            baseGround = ImageIO.read(getClass().getResourceAsStream("/resources/groundBase.png"));
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
        BufferedImage img = baseGround;
        g2d.drawImage(img, x, y, w, h, null);
    }
}
