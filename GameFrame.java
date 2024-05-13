import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.awt.event.*;

public class GameFrame{
    private JFrame frame;
    private int width;
    private int height;
    public Player dog;
    public Enemy frog;
    private GameCanvas gc;
    public boolean up, enemyUp;
    private Socket socket;
    private int playerID;

public GameFrame(int w, int h){
    frame = new JFrame();

    w = width;
    h = height;
    createEntities();
    gc = new GameCanvas(800, 600, dog, frog);
    
}

public void setUpGUI(){
    frame.setSize(width, height);
    frame.setTitle("Leap & Paws - Player #" + playerID);
    frame.add(gc);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    gc.startGameThread();
    frame.addKeyListener(gc.playerKey);
    frame.addKeyListener(gc.frog.enemyKey);

}

private void createEntities(){
    if(playerID == 1){
        dog = new Player(100,530);
        frog = new Enemy(200,530);
    }else {
        frog = new Enemy(200,530);
        dog = new Player(100,530);
    }
}

private void connectToServer(){
    try{
        socket = new Socket("localhost", 40200);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        playerID = in.readInt();
        System.out.println("You are player #" + playerID);
        if(playerID ==1){
            System.out.println("Waiting for Player #2 to connect...");
        }
    }catch(IOException ex){
        System.out.println("IOException from connectToServer");
    }
}


public static void main(String[] args) {
    GameFrame gf = new GameFrame(800, 600);
    gf.connectToServer();
    gf.setUpGUI();
}

}
