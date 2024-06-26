import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.*;

public class Player extends Entity{

    KeyHandler playerKey;
    private int x, y, speed, width, height;
    private String movement;


    public Player(){
        getPlayerImage();
        setDefaultValues();
        playerKey = new KeyHandler();
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
        speed = 4;
        width = 40;
        height = 40;
    }

    public void colliding(){
        if(movement == "down"){
            y-=1;// subtracting 1 so that the entity is no longer colliding with the platform from below and it can proceed to calling the update method
        }else if (movement == "up"){
            y+=1;
        }else if (movement == "right"){
            x-=1;
        }else if (movement == "left"){
            x+=1;
        }
    }

    public void update(){
        if(playerKey.playerUp == true){
            y-=speed;
            movement = "up";
        }else if(playerKey.playerDown == true){
            y+=speed;
            movement = "down";
        }else if(playerKey.playerRight == true){
            x+=speed;
            movement = "right";
        }else if(playerKey.playerLeft == true){
            x-=speed;
            movement = "left";
        }
    }

    public void draw(Graphics2D g2d){
        BufferedImage image = player;
        g2d.drawImage(image, x, y, width, height, null);
    }

    public boolean isColliding(Platforms platformBox){
        return !(this.x + this.width <= platformBox.getX() || 
        this.x >= platformBox.getX() + platformBox.getWidth() || this.y + this.height <= platformBox.getY() ||
        this.y >= platformBox.getY() + platformBox.getHeight());
    }

    
}
