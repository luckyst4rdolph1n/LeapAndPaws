import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.*;

public class Player{

    public BufferedImage player;
    public double x, y, speed, width, height;
    public double velx, vely, jumpVel;
    public double gravity;
    public boolean collides, grav;
    public String imgPath;


    public Player(double x, double y, String imgPath){
        this.x = x;
        this.y = y;
        this.imgPath = imgPath;
        getPlayerImage();
        setDefaultValues();
    }
    
    public void getPlayerImage(){
        try{
            player = ImageIO.read(getClass().getResourceAsStream(imgPath));
        }catch(IOException e){

        }
    }

    public void setDefaultValues(){
        //x = 100;
        //y = 530;
        speed = 5;
        width = 40;
        height = 40;
        gravity = 1;
        jumpVel = 15;
    }

    public void setBottom(double top){
        y = top - height;
    }

    public void setTop(double bottom){
        y = bottom;
    }

    public void setRight(double left){
        x = left - width;
    }

    public void setLeft(double right){
        x = right;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public void setX(double n){
        x = n;
    }

    public void setY(double n){
        y = n;
    }

    public void update(){
        
    }

    public void draw(Graphics2D g2d){
        BufferedImage image = player;
        g2d.drawImage(image, (int) x, (int) y, (int) width, (int) height, null);
    }
   
}
