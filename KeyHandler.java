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
        }if(pressed == KeyEvent.VK_DOWN){
            playerDown = true;
        }if(pressed == KeyEvent.VK_RIGHT){
            playerRight = true;
        }if(pressed == KeyEvent.VK_LEFT){
            playerLeft = true;
        }

        /*if(pressed == KeyEvent.VK_W){
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
        }*/
    }

    @Override
    public void keyReleased(KeyEvent e){
        int pressed = e.getKeyCode();

        if(pressed == KeyEvent.VK_UP){
            playerUp = false;
        }if(pressed == KeyEvent.VK_DOWN){
            playerDown = false;
        }if(pressed == KeyEvent.VK_RIGHT){
            playerRight = false;
        }if(pressed == KeyEvent.VK_LEFT){
            playerLeft = false;
        }
        
        /*if(pressed == KeyEvent.VK_W){
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
        }*/
    }
}