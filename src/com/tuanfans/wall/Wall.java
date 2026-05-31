package com.tuanfans.wall;

import com.tuanfans.AbstractGameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

/**
 * @author TuanFans
 * @date 2026/5/31
 */
public class Wall extends AbstractGameObject {
    int x,y,w,h;
    Image image;
    private Rectangle2D.Double rect;

    public Wall(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.image = new ImageIcon(Objects.requireNonNull(Wall.class.getClassLoader().getResource("images/square2.jpg"))).getImage();
        this.rect = new Rectangle2D.Double(x,y,w,h);
    }

    public Rectangle2D getRect() {
        return rect;
    }
    @Override
    public void draw(Graphics2D g2) {
        Graphics2D g2d = (Graphics2D) g2.create();
        g2d.drawImage(image,x,y,w,h,null);
    }
}
