import java.awt.event.*;

public class KeyHandler implements KeyListener{

    public boolean playerUp, playerDown, playerRight, playerLeft;
    public boolean enemyUp, enemyDown, enemyRight, enemyLeft;

    @Override
    public void keyTyped(KeyEvent e){

    }

    @Override
    public void keyPressed(KeyEvent e){
        int pressed = e.getKeyCode();

        if(pressed == KeyEvent.VK_UP){
            playerUp = true;
            System.out.println("up");//for debugging
        }else if(pressed == KeyEvent.VK_DOWN){
            playerDown = true;
            System.out.println("down");
        }else if(pressed == KeyEvent.VK_RIGHT){
            playerRight = true;
            System.out.println("right");
        }else if(pressed == KeyEvent.VK_LEFT){
            playerLeft = true;
            System.out.println("left");
        }

        if(pressed == KeyEvent.VK_W){
            enemyUp = true;
            System.out.println("up");
        }else if(pressed == KeyEvent.VK_S){
            enemyDown = true;
            System.out.println("down");
        }else if(pressed == KeyEvent.VK_D){
            enemyRight = true;
            System.out.println("right");
        }else if(pressed == KeyEvent.VK_A){
            enemyLeft = true;
            System.out.println("left");
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        int pressed = e.getKeyCode();

        if(pressed == KeyEvent.VK_UP){
            playerUp = false;
        }else if(pressed == KeyEvent.VK_DOWN){
            playerDown = false;
        }else if(pressed == KeyEvent.VK_RIGHT){
            playerRight = false;
            System.out.println("right");
        }else if(pressed == KeyEvent.VK_LEFT){
            playerLeft = false;
            System.out.println("left");
        }
        
        if(pressed == KeyEvent.VK_W){
            enemyUp = false;
        }else if(pressed == KeyEvent.VK_S){
            enemyDown = false;
            System.out.println("down");
        }else if(pressed == KeyEvent.VK_D){
            enemyRight = false;
            System.out.println("right");
        }else if(pressed == KeyEvent.VK_A){
            enemyLeft = false;
            System.out.println("left");
        }
    }
}