import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Platforms{

    public BufferedImage pf;
    public String imgPath;
    private int x, y, w, h;

    public Platforms(String imgPath, int x, int y, int w, int h){
        this.imgPath = imgPath;
        this.x = x; //0
        this.y = y; //570;
        this.w = w;//800;
        this.h = h;//31;
        setImages();
    }

    public void setImages(){
        try{
            pf = ImageIO.read(getClass().getResourceAsStream(imgPath));
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

    public int getTop(){
        int top = y;
        return top;
    }

    public int getBottom(){
        int bottom = y + h;
        return bottom;
    }

    public int getRight(){
        int right = x + w;
        return right;
    }

    public int getLeft(){
        int left = x;
        return left;
    }

    public void draw(Graphics2D g2d){
        BufferedImage img = pf;
        g2d.drawImage(img, x, y, w, h, null);
    }
}
