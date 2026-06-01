package com.tuanfans.tank;


import com.tuanfans.*;
import com.tuanfans.bullet.Bullet;
import com.tuanfans.explode.Explode;
import com.tuanfans.view.TankPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * @author TuanFans
 * @date 2026/5/29
 */
public abstract class Tank extends AbstractGameObject implements Moveable {
    int x,y,speed;
    int oldX,oldY;
    public static final int SIZE = 40;
    Image image;
    Direction direction;
    Group group;
    Rectangle2D.Double rect;

    public void setXY(int x,int y){
        this.setX(x);
        this.setY(y);
    }

    public void back(){
        this.setX(oldX);
        this.setY(oldY);
    }

    public int getX(){
        return x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return y;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getOldX(){
        return oldX;
    }

    public int getOldY(){
        return oldY;
    }

    public Group getGroup(){
        return group;
    }

    public Direction getDirection(){
        return direction;
    }

    public Rectangle2D.Double getRect(){
        return rect;
    }

    @Override
    public abstract void move();

    abstract void shoot();

    @Override
    public void draw(Graphics2D g2){
        if(!this.isLive()) {
            GameModel.getInstance().remove(this);
            return;
        }
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
        GameModel.getInstance().add(new Explode(x+SIZE/2-Explode.SIZE/2,y+SIZE/2-Explode.SIZE/2));
    }
}
