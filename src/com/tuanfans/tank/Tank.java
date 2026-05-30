package com.tuanfans.tank;


import com.tuanfans.Direction;
import com.tuanfans.Group;
import com.tuanfans.bullet.Bullet;
import com.tuanfans.explode.Explode;
import com.tuanfans.view.TankPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author TuanFans
 * @date 2026/5/29
 */
public abstract class Tank {
    int x,y,speed;
    public static final int SIZE = 40;
    Image image;
    Direction direction;
    Group group;
    boolean live = true;

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Group getGroup(){
        return group;
    }

    public boolean isLive(){
        return live;
    }

    public void setLive(boolean live){
        this.live = live;
    }

    public abstract void move();

    abstract void shoot();
    public void draw(Graphics2D g2){
        // 创建副本：创建副本，避免对原始对象进行修改
        Graphics2D g2d = (Graphics2D) g2.create();
        switch(direction){
            case Direction.UP:
                g2d.drawImage(image,x,y,SIZE,SIZE,null);
                break;

            case Direction.DOWN:
                g2d.rotate(Math.PI,x+ (double) SIZE /2,y+ (double) SIZE /2);
                g2d.drawImage(image,x,y,SIZE,SIZE,null);
                break;

            case Direction.LEFT:
                g2d.rotate(-Math.PI/2,x+ (double) SIZE /2,y+ (double) SIZE /2);
                g2d.drawImage(image,x,y,SIZE,SIZE,null);
                break;

            case Direction.RIGHT:
                g2d.rotate(Math.PI/2,x+ (double) SIZE /2,y+ (double) SIZE /2);
                g2d.drawImage(image,x,y,SIZE,SIZE,null);
                break;
        }
        // 释放副本，该画笔将无法再使用。防止其他图形受该画笔状态变化的影响
        g2d.dispose();
    }

    public boolean collision(ArrayList<? extends Tank> tanks){
        for(Tank tank: tanks){
            if(tank.isLive() && this!=tank){
                Rectangle2D.Double tRect1 = new Rectangle2D.Double(tank.x, tank.y, Tank.SIZE, Tank.SIZE);
                Rectangle2D.Double tRect2 = new Rectangle2D.Double(x, y, Bullet.SIZE, Bullet.SIZE);
                if(tRect1.intersects(tRect2)){
                    return true;
                }
            }
        }
        return false;
    }

    public void explode(){
        TankPanel.explodes.add(new Explode(x+SIZE/2-Explode.SIZE/2,y+SIZE/2-Explode.SIZE/2));
    }
}
