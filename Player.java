import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.*;

public class Player extends Entity{

    KeyHandler playerKey;
    public int x, y, speed, width, height;
    public int velx, vely, jump;
    public double yFinalVel, yMoveVel;
    public int gravity;
    private String movement;
    public boolean jumping, collidesBot, collidesTop, collidesSideL,collidesSideR, grav, collides, onTop;
    public boolean jumpingLol = true;


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
        gravity = 2;
        yMoveVel = 20;
        jump = -10;
    }

    /*public void colliding(){

        if(movement == "down" || grav == true){
            if(playerKey.playerUp == true){
                vely = jump;
                y-=vely;
                movement = "up";
                //System.out.println(vely);
            }
            if(playerKey.playerRight == true){
                x+=speed;
                movement = "right";
            }
            if(playerKey.playerLeft == true){
                x-=speed;
                movement = "left";
            }// subtracting 1 so that the entity is no longer colliding with the platform from below and it can proceed to calling the update method
        }if(movement == "up"){
            if(playerKey.playerDown == true){
                vely = speed;
                y+=vely;
                movement = "down";
            }if(playerKey.playerRight == true){
                x+=speed;
                movement = "right";
            }if(playerKey.playerLeft == true){
                x-=speed;
                movement = "left";
            }
        }if (movement == "right"){
            if(playerKey.playerUp == true){
                vely = jump;
                y-=vely;
                movement = "up";
            }if(playerKey.playerDown == true){
                vely = speed;
                y-=vely;
                movement = "down";
            }if(playerKey.playerLeft == true){
                x-=speed;
                movement = "left";
            }
        } if (movement == "left"){

            if(playerKey.playerUp == true){
                vely = jump;
                y-=vely;
                movement = "up";
                //System.out.println(vely);
            }if(playerKey.playerDown == true){
                vely = speed;
                y-=vely;
                movement = "down";
            }if(playerKey.playerRight == true){
                x+=speed;
                movement = "right";
            }
        }
    }*/

    public void collidingBot(){
        y+=1;
        collidesBot = false;
        collides = false;
    }

    public void collidingSideL(){
        x-=1;
        collidesSideL = false;
        collides = false;
    }

    public void collidingSideR(){
        x+=1;
        collidesSideR = false;
        collides = false;
    }

    public void collidingTop(){
        onTop = true;
        y-=0.5;
        vely=0;
        collidesTop =false;
        
    }

    public void updateWithJump(){
        if(playerKey.playerUp == true && !jumping){
            y-=jump;
            movement = "up";
        }
        if(playerKey.playerDown == true){
            vely = speed;
            y+=vely;
            movement = "down";
        }
        if(playerKey.playerRight == true){
            x+=speed;
            movement = "right";
        }if(playerKey.playerLeft == true){
            x-=speed;
            movement = "left";
        }
    }

    public void draw(Graphics2D g2d){
        BufferedImage image = player;
        g2d.drawImage(image, x, y, width, height, null);
    }

    public boolean isColliding(Platforms platformBox){
        if(this.x + this.width < platformBox.getX() || 
        this.x > platformBox.getX() + platformBox.getWidth() || this.y + this.height < platformBox.getY() ||
        this.y > platformBox.getY() + platformBox.getHeight()){
            return collides;
        }else{
            collides = true;
            return collides;
        }
    }


    public boolean isCollidingSideL(Platforms platformBox){
        if(this.x + this.width <= platformBox.getX()){
            collidesSideL = false;
            return collidesSideL;
        }else{
            collidesSideL = true;
            return collidesSideL;
        }
    }

    public boolean isCollidingSideR(Platforms platformBox){
        if(this.x >= platformBox.getX() + platformBox.getWidth()){
            collidesSideR = false;
            return collidesSideR;
        }else{
            collidesSideR = true;
            return collidesSideR;
        }
    }

    public boolean isCollidingTop(Platforms platformBox){
        
        if(this.y + this.height <= platformBox.getY()){
            collidesTop = false;

            return collidesTop;
        }else{
            collidesTop = true;
            return collidesTop;
        }
    }

    public boolean isCollidingBot(Platforms platformBox){
        
        if(this.y >= platformBox.getY() + platformBox.getHeight()){
            collidesBot = false;
            return collidesBot;
        }else{
            collidesBot = true;
            return collidesBot;
        }
    }

}
