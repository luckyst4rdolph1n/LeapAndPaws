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
    private ReadFromServer rfsRunnable;
    private WriteToServer wtsRunnable;

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
        rfsRunnable = new ReadFromServer(in);
        wtsRunnable = new WriteToServer(out);
        rfsRunnable.waitForStartMsg();
    }catch(IOException ex){
        System.out.println("IOException from connectToServer");
    }
}

private class ReadFromServer implements Runnable {
    private DataInputStream dataIn;
    public ReadFromServer(DataInputStream in){
        dataIn = in;
        System.out.println("RFS Runnable created");
    }
    public void run(){
        try{
            while(true){
                //enemy.setX(dataIn.readDouble());
                //enemy.setY(dataIn.readDouble());
            }

        }catch(IOException ex){
            System.out.println("IOException from WTS run()")
        }
    }
    public void waitForStartMsg(){
        try{
            String startMsg = dataIn.readUTF();
            System.out.println("Message from server: "+startMsg);
            Thread readThread = new Thread(rfsRunnable);
            Thread writeThread = new Thread(wtsRunnable);
            readThread.start();
            writeThread.start();
        }catch(IOException ex){
            System.out.println("IOException from waitForStartMsg()");
        }
    }
}

private class WriteToServer implements Runnable {
    private DataOutputStream dataOut;
    public WriteToServer(DataOutputStream out){
        dataOut = out;
        System.out.println("WTS Runnable created");
    }
    public void run(){
        try{
            while (true){
                dataOut.writeDouble(); //get ung x sa loob paren
                dataOut.writeFloat(); //get yung y
                dataOut.flush();
                try{
                    Thread.sleep(20);
                } catch(InterruptedException er){
                    System.out.println("InterruptedException from WTS run()");
                }
            }

        }catch(IOException ex){
            System.out.println("IOException from WTS run");
        }
    }
}

public static void main(String[] args) {
    GameFrame gf = new GameFrame(800, 600);
    gf.connectToServer();
    gf.setUpGUI();
}

}
