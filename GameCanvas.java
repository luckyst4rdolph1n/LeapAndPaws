import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class GameCanvas extends JComponent{
    private int width;
    private int height;

    Thread gameThread;
    Player player1, player2;
    //Enemy enemy1;
    Background bg1;
    Platforms baseGround, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14;
    boolean running;
    public ArrayList<Platforms> platforms;
    public ArrayList<Breezy> breezies;
    public ArrayList<Chocy> chocies;

    public Flag flag;
    //KeyHandler playerKey;

    private int FPS = 60;

    public GameCanvas(int w, int h, Player plyr1, Player plyr2, Background bg1, ArrayList<Platforms> platforms, ArrayList<Breezy> breezies, ArrayList<Chocy> chocies, Flag flag){
        width = w;
        height = h;
        player1 = plyr1;
        player2 = plyr2;
        this.bg1 = bg1;
        this.platforms = platforms;
        this.breezies = breezies;
        this.chocies = chocies;
        this.flag = flag;
        this.setPreferredSize(new Dimension(width, height));
        //player1 = new Player();
        //enemy1 = new Enemy();
        
        //playerKey = new KeyHandler();

    }


    

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        bg1.draw(g2d); 

        flag.draw(g2d);

        for(int i=0; i<platforms.size(); i++){
            platforms.get(i).draw(g2d);
        }

        for(int i=0; i<breezies.size(); i++){
            breezies.get(i).draw(g2d);
        }

        for(int i=0; i<chocies.size(); i++){
            chocies.get(i).draw(g2d);
        }

        

        player1.draw(g2d);
        player2.draw(g2d);
        //enemy1.draw(g2d);

        g2d.dispose();
        
    }
}
