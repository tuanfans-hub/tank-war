package com.tuanfans.bullet;

import com.tuanfans.Direction;
import com.tuanfans.Group;
import com.tuanfans.view.TankPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author TuanFans
 * @date 2026/5/29
 */
public class Bullet {
    int x,y,speed,size;
    Direction direction;
    Image image;
    Group group;

    private Bullet(){}
    public static Bullet createBullet(int x, int y, Direction dir, Group group){
        Bullet bullet = new Bullet();
        bullet.x = x;
        bullet.y = y;
        bullet.speed = 10;
        bullet.size = 10;
        bullet.direction = dir;
        bullet.image = new ImageIcon(Objects.requireNonNull(Bullet.class.getClassLoader().getResource("images/bulletU.png"))).getImage();
        bullet.group = group;
        return bullet;
    }

    public void move(ArrayList<Bullet> bullets){
        switch (direction){
            case Direction.UP:
                y-=speed;
                if(y<=0) bullets.remove(this);
                break;
            case Direction.DOWN:
                y+=speed;
                if(y>=TankPanel.GAME_HEIGHT) bullets.remove(this);
                break;
            case Direction.LEFT:
                x-=speed;
                if(x<=0) bullets.remove(this);
                break;
            case Direction.RIGHT:
                x+=speed;
                if(x>=TankPanel.GAME_WIDTH) bullets.remove(this);
                break;
        }
    }
    public void draw(Graphics2D g2){
        Graphics2D g2d = (Graphics2D) g2.create();
        switch(direction){
            case Direction.UP:
                g2d.drawImage(image,x,y,size,size,null);
                break;

            case Direction.DOWN:
                g2d.rotate(Math.PI,x+ (double) size /2,y+ (double) size /2);
                g2d.drawImage(image,x,y,size,size,null);
                break;

            case Direction.LEFT:
                g2d.rotate(-Math.PI/2,x+ (double) size /2,y+ (double) size /2);
                g2d.drawImage(image,x,y,size,size,null);
                break;

            case Direction.RIGHT:
                g2d.rotate(Math.PI/2,x+ (double) size /2,y+ (double) size /2);
                g2d.drawImage(image,x,y,size,size,null);
                break;
        }
        // 释放副本，恢复画笔状态。防止其他图形受该画笔状态变化的影响
        g2d.dispose();
    }

}
