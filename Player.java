import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.*;

public class Player extends Entity{

    KeyHandler playerKey;
    private int x, y, speed, width, height;
    public int velx, vely, jump;
    private int gravity;
    private String movement;
    public boolean collidesBot, collidesTop, collidesSideL,collidesSideR, grav, collides, onTop;
    public boolean falling = false;


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
        gravity = 5;
        jump = 10;
    }

    public void colliding(){

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
    }

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

    public void update(){
        if(falling){ //&& collides == false ito mismong gravity
            grav = true;
            vely = speed;
            y+=vely;
        }
        if(playerKey.playerUp == true){
            //grav = false;
            vely = jump;
            y-=vely;
            movement = "up";
        }if(playerKey.playerDown == true){
            vely = speed;
            y-=vely;
            movement = "down";
        }if(playerKey.playerRight == true){
            x+=speed;
            movement = "right";
        }if(playerKey.playerLeft == true){
            x-=speed;
            movement = "left";
        }

        //if(vely <= gravity && y<530){//&& collides == false
            //vely += 0.1;
        //}
    }

    public void draw(Graphics2D g2d){
        BufferedImage image = player;
        g2d.drawImage(image, x, y, width, height, null);
    }

    public boolean isColliding(Platforms platformBox){
        if(this.x + this.width <= platformBox.getX() || 
        this.x >= platformBox.getX() + platformBox.getWidth() || this.y + this.height <= platformBox.getY() ||
        this.y >= platformBox.getY() + platformBox.getHeight()){
            collides = false;
            falling = true;
            return collides;
        }else{
            collides = true;
            falling = false;
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
            falling = true;
            return collidesTop;
        }else{
            collidesTop = true;
            falling = false;
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
