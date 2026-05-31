package com.tuanfans.tank;


import com.tuanfans.ConfigManager;
import com.tuanfans.Direction;
import com.tuanfans.Group;
import com.tuanfans.bullet.Bullet;
import com.tuanfans.strategy.shoot.DefaultShootStrategy;
import com.tuanfans.strategy.shoot.ShootStrategy;
import com.tuanfans.view.TankPanel;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * @author TuanFans
 * @date 2026/5/29
 */
public class PlayerTank extends Tank{
    private boolean isUpKey, isDownKey, isLeftKey, isRightKey;
    private ShootStrategy shootStrategy;
    public PlayerTank(int x, int y, Direction dir){
        this.x = x;
        this.y = y;
        this.speed = 5;
        this.direction = dir;
        this.group = Group.PLAYER;
        this.image = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("images/GoodTank1.png"))).getImage();

        initShootStrategy();
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
            case KeyEvent.VK_CONTROL -> shoot();
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
            y=y>0?y-speed:0;
        }
        if(isDownKey){
            direction = Direction.DOWN;
            y=y< TankPanel.GAME_HEIGHT-Tank.SIZE?y+speed:TankPanel.GAME_HEIGHT-Tank.SIZE;
        }
        if(isLeftKey){
            direction = Direction.LEFT;
            x=x>0?x-speed:0;
        }
        if(isRightKey){
            direction = Direction.RIGHT;
            x=x<TankPanel.GAME_WIDTH-Tank.SIZE?x+speed:TankPanel.GAME_WIDTH-Tank.SIZE;
        }
    }

    @Override
    void shoot(){
        shootStrategy.shoot(this);
    }

    private void initShootStrategy(){
        try {
            Class<?> clazz = Class.forName("com.tuanfans.strategy.shoot." + ConfigManager.get("shoot.strategy"));
            this.shootStrategy = (ShootStrategy) clazz.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
