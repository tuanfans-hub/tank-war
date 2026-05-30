package com.tuanfans.explode;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * @author TuanFans
 * @date 2026/5/30
 */
public class Explode {
    int x,y;
    public static final int SIZE = 30;
    int step = 0;
    public static final int STEP_MAX = 16;
    static final Image[] images = new Image[STEP_MAX];
    public boolean live = true;

    public Explode(int x,int y){
        this.x = x;
        this.y = y;
        for(int i=0;i<STEP_MAX;i++){
            Image currentImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("images/e" + (i+1) + ".gif"))).getImage();
            if (currentImage != null) {
                images[i] =  currentImage;
            }
        }
    }

    public void setLive(boolean live){
        this.live = live;
    }

    public boolean isLive(){
        return live;
    }

    public void draw(Graphics2D g2){
        Graphics2D g2d = (Graphics2D) g2.create();
        g2d.drawImage(images[step],x,y,Explode.SIZE,Explode.SIZE,null);
        step++;
        if(step>=STEP_MAX) live=false;
    }
}
