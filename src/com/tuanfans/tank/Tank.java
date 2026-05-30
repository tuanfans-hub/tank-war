package com.tuanfans.tank;


import com.tuanfans.Direction;

import java.awt.*;

/**
 * @author TuanFans
 * @date 2026/5/29
 */
public abstract class Tank {
    int x,y,speed,size;
    Image image;
    Direction direction;
    public abstract void move();
    public void draw(Graphics2D g2){
        // 创建副本：创建副本，避免对原始对象进行修改
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
        // 释放副本，该画笔将无法再使用。防止其他图形受该画笔状态变化的影响
        g2d.dispose();
    }
}
