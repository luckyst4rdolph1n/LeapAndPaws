import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class GameCanvas extends JComponent implements Runnable{
    private int width;
    private int height;

    Thread gameThread;
    Player player1;
    Enemy enemy1;
    Background bg1;
    Platforms baseGround, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14;
    boolean running;
    public ArrayList<Platforms> platforms;
    KeyHandler playerKey;

    private int FPS = 60;

    public GameCanvas(int w, int h){
        width = w;
        height = h;
        this.setPreferredSize(new Dimension(width, height));
        player1 = new Player();
        enemy1 = new Enemy();
        bg1 = new Background("/resources/grassland.png");
        baseGround = new Platforms("/resources/groundBase.png", 0, 570, 800, 31);
        p1 = new Platforms("/resources/platform1.png", 681, 480, 160, 33);
        p2 = new Platforms("/resources/platform2.png", 0, 480, 606, 33);
        p3 = new Platforms("/resources/platform3.png", 730, 375, 72, 33);
        p4 = new Platforms("/resources/platform4.png", 187, 355, 468, 34);
        p5 = new Platforms("/resources/platform5.png", 0, 375, 66, 33);
        p6 = new Platforms("/resources/platform6.png", 623, 287, 161, 33);
        p7 = new Platforms("/resources/smallPlatform.png", 519, 258, 33, 28);
        p8 = new Platforms("/resources/smallPlatform.png", 437, 239, 33, 28);
        p9 = new Platforms("/resources/smallPlatform.png", 323, 239, 33, 28);
        p10 = new Platforms("/resources/smallPlatform.png", 217, 231, 33, 28);
        p11 = new Platforms("/resources/smallPlatform.png", 121, 215, 33, 28);
        p12 = new Platforms("/resources/platform7.png", 0, 173, 101, 33);
        p13 = new Platforms("/resources/smallPlatform.png", 176, 110, 33, 28);
        p14= new Platforms("/resources/platform8.png", 251, 80, 550, 33);
        running = false;
        platforms = new ArrayList<Platforms>();
        addPlatforms();
        playerKey = new KeyHandler();

    }

    public void addPlatforms(){
        platforms.add(baseGround);
        platforms.add(p1);
        platforms.add(p2);
        platforms.add(p3);
        platforms.add(p4);
        platforms.add(p5);
        platforms.add(p6);
        platforms.add(p7);
        platforms.add(p8);
        platforms.add(p9);
        platforms.add(p10);
        platforms.add(p11);
        platforms.add(p12);
        platforms.add(p13);
        platforms.add(p14);
    }

    public void startGameThread(){
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void stopGameThread(){
        running = false;
        try{
            gameThread.join();
        }catch(InterruptedException e){

        }
        
    }

    public void actions(){
        if(playerKey.playerUp == true && isOnPlatform(player1, platforms)){
            player1.vely = -player1.jumpVel;
        }else if(playerKey.playerDown == true){
            player1.vely = player1.speed;
        }else if(playerKey.playerRight == true){
            player1.velx = player1.speed;
        }else if(playerKey.playerLeft == true){
            player1.velx = -player1.speed;
        }
    }

    public boolean checkCollision(Platforms p, Player player){
        if(player.x + player.width <= p.getX() || 
        player.x >= p.getX() + p.getWidth() || player.y + player.height <= p.getY() ||
        player.y >= p.getY() + p.getHeight()){
            return false;
        }else{
            return true;
        }
    }

    public ArrayList<Platforms> Colliding(Player player, ArrayList<Platforms> ps){
        ArrayList<Platforms> collidingPlatforms = new ArrayList<Platforms>();
        for(Platforms a: ps){
            if(checkCollision(a, player)){
                collidingPlatforms.add(a);
            }
        }
        return collidingPlatforms; 
    }

    public void platformCollisions(ArrayList<Platforms> pfs , Player player){
        player.vely += player.gravity;
        player.y += player.vely;
        //System.out.println(player.vely);
        ArrayList<Platforms> collidingPfs = Colliding(player, pfs);
        if(collidingPfs.size() > 0){
            Platforms collided = collidingPfs.get(0);
            if(player.vely>0){
                player.setBottom(collided.getTop());
            }else if(player.vely<0){
                player.setTop(collided.getBottom());
            }
            player.vely = 0;
        }

        player.x += player.velx;
        collidingPfs = Colliding(player, pfs);
        if(collidingPfs.size() > 0){
            Platforms collided = collidingPfs.get(0);
            if(player.velx>0){
                player.setRight(collided.getLeft());
            }else if(player.velx<0){
                player.setLeft(collided.getRight());
            }
        }
        player.velx = 0;
    }
    
    public boolean isOnPlatform(Player player, ArrayList<Platforms> pfs){
        player.y += 5;
        ArrayList<Platforms> collidingPfs = Colliding(player, pfs);
        player.y -= 5;
        if(collidingPfs.size() > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void run(){

        double drawInterval = 1000000000/FPS;
        double nextDraw = System.nanoTime() + drawInterval;

        while(running){
            
            update();

            repaint();

            try{
                double remainTime = nextDraw - System.nanoTime();
                remainTime = remainTime/1000000;

                if(remainTime < 0){
                    remainTime = 0;
                }

                Thread.sleep((long) remainTime);
                nextDraw += drawInterval;
            }catch(InterruptedException e){

            }
            
        }
    }

    public void update(){
        actions();
        platformCollisions(platforms, player1);

        if(enemy1.isColliding(baseGround) || enemy1.isColliding(p1)){
            enemy1.colliding();
        }else{
            enemy1.update();
        }
        
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        bg1.draw(g2d); 
        for(int i=0; i<platforms.size(); i++){
            platforms.get(i).draw(g2d);
        }

        player1.draw(g2d);
        enemy1.draw(g2d);

        g2d.dispose();
        
    }
}
