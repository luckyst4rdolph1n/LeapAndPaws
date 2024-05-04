import javax.swing.*;

public class GameFrame{
    private JFrame frame;
    private int width;
    private int height;
    private GameCanvas gc;
    public boolean up, enemyUp;

public GameFrame(int w, int h){
    frame = new JFrame();

    w = width;
    h = height;

    gc = new GameCanvas(800, 600);
    
}

public void setUpGUI(){
    frame.setSize(width, height);
    frame.setTitle("Leap & Paws");
    frame.add(gc);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    gc.startGameThread();
    frame.addKeyListener(gc.player1.playerKey);
    frame.addKeyListener(gc.enemy1.enemyKey);

}

}
