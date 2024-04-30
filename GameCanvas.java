import java.awt.*;
import javax.swing.*;

public class GameCanvas extends JComponent{
    private int width;
    private int height;

    public GameCanvas(int w, int h){
        width = w;
        height = h;
        this.setPreferredSize(new Dimension(width, height));
    }
    
}
