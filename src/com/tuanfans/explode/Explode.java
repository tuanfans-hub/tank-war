package com.tuanfans.explode;

import com.tuanfans.AbstractGameObject;
import com.tuanfans.GameModel;
import com.tuanfans.view.TankPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * @author TuanFans
 * @date 2026/5/30
 */
public class Explode extends AbstractGameObject {
    int x,y;
    public static final int SIZE = 30;
    int step = 0;
    public static final int STEP_MAX = 16;
    static final Image[] images = new Image[STEP_MAX];

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

    @Override
    public void draw(Graphics2D g2){
        if(!this.isLive()) {
            GameModel.getInstance().remove(this);
            return;
        }
        Graphics2D g2d = (Graphics2D) g2.create();
        g2d.drawImage(images[step],x,y,Explode.SIZE,Explode.SIZE,null);
        step++;
        if(step>=STEP_MAX) this.setLive(false);
    }
}
