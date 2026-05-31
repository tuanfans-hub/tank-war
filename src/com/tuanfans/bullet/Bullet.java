package com.tuanfans.bullet;

import com.tuanfans.AbstractGameObject;
import com.tuanfans.Direction;
import com.tuanfans.Group;
import com.tuanfans.Moveable;
import com.tuanfans.tank.Tank;
import com.tuanfans.view.TankPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

/**
 * @author TuanFans
 * @date 2026/5/29
 */
public class Bullet extends AbstractGameObject implements Moveable {
    int x,y,speed;
    public static final int SIZE = 10;
    Direction direction;
    Image image;
    Group group;
    private Rectangle2D.Double rect;

    private Bullet(){}

    public Rectangle2D.Double getRect(){
        return rect;
    }

    public Group getGroup(){
        return group;
    }
    public static Bullet createBullet(int x, int y, Direction dir, Group group){
        Bullet bullet = new Bullet();
        bullet.x = x;
        bullet.y = y;
        bullet.speed = 10;
        bullet.direction = dir;
        bullet.image = new ImageIcon(Objects.requireNonNull(Bullet.class.getClassLoader().getResource("images/bulletU.png"))).getImage();
        bullet.group = group;
        bullet.rect = new Rectangle2D.Double(x, y, Bullet.SIZE, Bullet.SIZE);
        return bullet;
    }

    @Override
    public void move(){
        switch (direction){
            case Direction.UP:
                y-=speed;
                break;
            case Direction.DOWN:
                y+=speed;
                break;
            case Direction.LEFT:
                x-=speed;
                break;
            case Direction.RIGHT:
                x+=speed;
                break;
        }
        rect.x = x;
        rect.y = y;
        checkBullet();
    }

    // 碰撞检测：检测子弹是否与坦克相撞
    public void collision(Tank tank){
        Rectangle2D.Double bRect = new Rectangle2D.Double(x, y, Bullet.SIZE, Bullet.SIZE);
        Rectangle2D.Double tRect = new Rectangle2D.Double(tank.getX(), tank.getY(), Tank.SIZE, Tank.SIZE);
        if (bRect.intersects(tRect)) {
            if (tank.getGroup() != this.group) {
                tank.setLive(false);
                this.setLive(false);
                tank.explode();
            }
        }
    }

    // 检测子弹是否超出可视范围
    private void checkBullet(){
        if(x<=0||x>=TankPanel.GAME_WIDTH||y<=0||y>=TankPanel.GAME_HEIGHT){
            this.setLive(false);
        }
    }

    @Override
    public void draw(Graphics2D g2){
        if(!this.isLive()) {
            TankPanel.getInstance().remove(this);
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
        // 释放副本，恢复画笔状态。防止其他图形受该画笔状态变化的影响
        g2d.dispose();
    }

}
