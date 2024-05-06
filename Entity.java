import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    public int x, y, speed;

    public BufferedImage player, enemy;
    public Rectangle hitbox;

    abstract void draw(Graphics2D g2d);

    abstract void update();

    //abstract boolean isColliding(Platforms platformBox);
}
