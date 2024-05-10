import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.*;

public class Player extends Entity{

    public int x, y, speed, width, height;
    public int velx, vely, jumpVel;
    public int gravity;
    public boolean collides, grav;


    public Player(){
        getPlayerImage();
        setDefaultValues();
    }
    
    public void getPlayerImage(){
        try{
            player = ImageIO.read(getClass().getResourceAsStream("/resources/Asset9.png"));
        }catch(IOException e){

        }
    }

    public void setDefaultValues(){
        x = 100;
        y = 530;
        speed = 5;
        width = 40;
        height = 40;
        gravity = 1;
        jumpVel = 15;
    }

    public void setBottom(int top){
        y = top - height;
    }

    public void setTop(int bottom){
        y = bottom;
    }

    public void setRight(int left){
        x = left - width;
    }

    public void setLeft(int right){
        x = right;
    }


    public void update(){
        
    }

    public void draw(Graphics2D g2d){
        BufferedImage image = player;
        g2d.drawImage(image, x, y, width, height, null);
    }
   
}
