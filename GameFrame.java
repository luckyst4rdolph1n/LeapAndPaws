import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class GameFrame implements Runnable{
    private JFrame frame;
    private int width;
    private int height;
    private GameCanvas gc;
    public boolean up, enemyUp;
    private Socket socket;
    public int playerID;
    public Player player1, player2;
    public KeyHandler playerKey;
    Thread gameThread;
    private ReadFromServer rfsRunnable;
    private WriteToServer wtsRunnable;

    private int FPS = 60;

    Background bg1;
    Platforms baseGround, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14;
    boolean running;
    public ArrayList<Platforms> platforms;

public GameFrame(int w, int h){
    frame = new JFrame();
    createGameScene();
    playerKey = new KeyHandler();
    
    w = width;
    h = height;
    //this.addKeyListener(playerHandler);
    

    
}

public void connectToServer(){
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
                player2.setX(dataIn.readDouble());
                player2.setY(dataIn.readDouble());
            }

        }catch(IOException ex){
            System.out.println("IOException from WTS run()");
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
                dataOut.writeDouble(player1.getX()); //get ung x sa loob paren
                dataOut.writeDouble(player1.getY()); //get yung y
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
//}


public void setUpGUI(){
    frame.setSize(width, height);
    frame.setTitle("Leap & Paws - Player # " + playerID);
    createPlayers();
    gc = new GameCanvas(800, 600, player1, player2, bg1, platforms);
    frame.add(gc);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    startGameThread();
    frame.addKeyListener(playerKey);
    //frame.addKeyListener(gc.enemy1.enemyKey);

}

private void createPlayers(){
    if(playerID == 1){
        player1 = new Player(100, 530, "/resources/Asset9.png");
        player2 = new Player(300, 530, "/resources/frog2.png");
    }else{
        player1 = new Player(300, 530, "/resources/frog2.png");
        player2 = new Player(100, 530, "/resources/Asset9.png");
    }
}

private void createGameScene(){
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

        gc.repaint();

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
    platformCollisions(platforms, player2);

    /*if(enemy1.isColliding(baseGround) || enemy1.isColliding(p1)){
        enemy1.colliding();
    }else{
        enemy1.update();
    }*/
    
}

public void actions(){
    if(playerKey.playerUp == true && isOnPlatform(player1, platforms) == true){
        player1.vely = -player1.jumpVel;
    }else if(playerKey.playerDown == true){
        player1.vely = player1.speed;
    }else if(playerKey.playerRight == true){
        player1.velx = player1.speed;
    }else if(playerKey.playerLeft == true){
        player1.velx = -player1.speed;
    }
}

}
