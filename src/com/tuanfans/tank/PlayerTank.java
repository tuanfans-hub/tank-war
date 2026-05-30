package com.tuanfans.tank;


import com.tuanfans.Direction;
import com.tuanfans.Group;
import com.tuanfans.bullet.Bullet;
import com.tuanfans.view.TankPanel;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Objects;

/**
 * @author TuanFans
 * @date 2026/5/29
 */
public class PlayerTank extends Tank{
    private boolean isUpKey, isDownKey, isLeftKey, isRightKey;
    public PlayerTank(int x, int y, Direction dir){
        this.x = x;
        this.y = y;
        this.speed = 5;
        this.size = 40;
        this.direction = dir;
        this.image = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("images/GoodTank1.png"))).getImage();
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
            y=y< TankPanel.GAME_HEIGHT-size?y+speed:TankPanel.GAME_HEIGHT-size;
        }
        if(isLeftKey){
            direction = Direction.LEFT;
            x=x>0?x-speed:0;
        }
        if(isRightKey){
            direction = Direction.RIGHT;
            x=x<TankPanel.GAME_WIDTH-size?x+speed:TankPanel.GAME_WIDTH-size;
        }
    }

    private void shoot(){
        Bullet b = switch(direction){
            case Direction.UP->Bullet.createBullet(x+15,y,direction, Group.PLAYER);
            case Direction.DOWN->Bullet.createBullet(x+15,y+size,direction, Group.PLAYER);
            case Direction.LEFT->Bullet.createBullet(x,y+15,direction, Group.PLAYER);
            case Direction.RIGHT->Bullet.createBullet(x+size,y+15,direction, Group.PLAYER);
        };
        TankPanel.bullets.add(b);
    }

//    @Override
//    public void draw(Graphics2D g2) {
//        switch(direction){
//            case Direction.UP:
//                g2.drawImage(image,x,y,size,size,null);
//                break;
//
//            case Direction.DOWN:
//                g2.rotate(Math.PI,x+ (double) size /2,y+ (double) size /2);
//                g2.drawImage(image,x,y,size,size,null);
//                break;
//
//            case Direction.LEFT:
//                g2.rotate(-Math.PI/2,x+ (double) size /2,y+ (double) size /2);
//                g2.drawImage(image,x,y,size,size,null);
//                break;
//
//            case Direction.RIGHT:
//                g2.rotate(Math.PI/2,x+ (double) size /2,y+ (double) size /2);
//                g2.drawImage(image,x,y,size,size,null);
//                break;
//        }
//        // 释放副本，恢复画笔状态。防止其他图形受该画笔状态变化的影响
//        g2.dispose();
//    }

}
