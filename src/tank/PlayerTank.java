package tank;

import view.TankPanel;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author TuanFans
 * @date 2026/5/29
 */
public class PlayerTank extends Tank{
    private boolean isUpKey, isDownKey, isLeftKey, isRightKey;

    public PlayerTank(int x, int y,Direction dir){
        this.x = x;
        this.y = y;
        this.speed = 5;
        this.direction = dir;
    }

    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_UP -> {
                resetDirection();
                this.isUpKey = true;
            }
            case KeyEvent.VK_DOWN -> {
                resetDirection();
                this.isDownKey = true;
            }
            case KeyEvent.VK_LEFT -> {
                resetDirection();
                this.isLeftKey = true;
            }
            case KeyEvent.VK_RIGHT -> {
                resetDirection();
                this.isRightKey = true;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_UP -> isUpKey = false;
            case KeyEvent.VK_DOWN -> isDownKey = false;
            case KeyEvent.VK_LEFT -> isLeftKey = false;
            case KeyEvent.VK_RIGHT -> isRightKey = false;
        }
    }

    private void resetDirection() {
        isUpKey = false;
        isDownKey = false;
        isLeftKey = false;
        isRightKey = false;
    }

    @Override
    public void move() {
        if(isUpKey){
            direction = Direction.UP;
            y-=speed;
        }
        if(isDownKey){
            direction = Direction.DOWN;
            y+=speed;
        }
        if(isLeftKey){
            direction = Direction.LEFT;
            x-=speed;
        }
        if(isRightKey){
            direction = Direction.RIGHT;
            x+=speed;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.fillRect(x,y,50,50);
    }


}
