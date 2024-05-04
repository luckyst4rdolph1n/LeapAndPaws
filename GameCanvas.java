import java.awt.*;
import javax.swing.*;

public class GameCanvas extends JComponent implements Runnable{
    private int width;
    private int height;

    Thread gameThread;
    Player player1;
    Enemy enemy1;
    Background bg1;
    BaseGround baseGround;
    Platforms p1;

    private int FPS = 60;

    public GameCanvas(int w, int h){
        width = w;
        height = h;
        this.setPreferredSize(new Dimension(width, height));
        player1 = new Player();
        enemy1 = new Enemy();
        bg1 = new Background("/resources/grassland.png");
        baseGround = new BaseGround();
        p1 = new PlatformOne();

    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override
    public void run(){

        double drawInterval = 1000000000/FPS;
        double nextDraw = System.nanoTime() + drawInterval;

        while(gameThread != null){
            
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
        if(player1.isColliding(baseGround) || player1.isColliding(p1)){
            player1.colliding();
        }else{
            player1.update();
        }

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
        baseGround.draw(g2d);
        p1.draw(g2d);

        player1.draw(g2d);
        enemy1.draw(g2d);

        g2d.dispose();
        
    }
}
