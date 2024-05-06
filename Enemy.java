import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.*;

public class Enemy extends Entity{
    KeyHandler enemyKey;
    private int x, y, speed, width, height;
    private String movement;
    public int gravity = 3;
    public boolean falling = false;
    public boolean colliding;

    public Enemy(){
        getEnemyImage();
        setDefaultValues();
        enemyKey = new KeyHandler();
    }
    
    public void getEnemyImage(){
        try{
            enemy = ImageIO.read(getClass().getResourceAsStream("/resources/frog2.png"));
        }catch(IOException e){

        }
    }

    public void setDefaultValues(){
        x = 200;
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
        if(falling){
            y+=gravity;
        }
        if(enemyKey.enemyUp == true){
            y-=speed;
            movement = "up";
        }else if(enemyKey.enemyDown == true){
            y+=speed;
            movement = "down";
        }else if(enemyKey.enemyRight == true){
            x+=speed;
            movement = "right";
        }else if(enemyKey.enemyLeft == true){
            x-=speed;
            movement = "left";
        }
    }

    public void draw(Graphics2D g2d){
        BufferedImage image = enemy;
        g2d.drawImage(image, x, y, width, height, null);
    }

    public boolean isColliding(Platforms platformBox){
        if (this.x + this.width <= platformBox.getX() || 
        this.x >= platformBox.getX() + platformBox.getWidth() || this.y + this.height <= platformBox.getY() ||
        this.y >= platformBox.getY() + platformBox.getHeight()){
            falling = true;
            colliding = false;
            return colliding;
        }else{
            colliding = true;
            falling = false;
            return colliding;
        }
    }
}
