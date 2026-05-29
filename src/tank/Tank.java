package tank;

import view.TankPanel;

import java.awt.*;
import java.awt.event.KeyListener;

/**
 * @author TuanFans
 * @date 2026/5/29
 */
public abstract class Tank {
    int x,y,speed;
    Direction direction;
    public abstract void move();
    public abstract void draw(Graphics2D g);

    public enum Direction{
        UP,DOWN,LEFT,RIGHT
    }
}
