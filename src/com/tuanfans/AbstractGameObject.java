package com.tuanfans;

import java.awt.*;

/**
 * @author TuanFans
 * @date 2026/5/31
 */
public abstract class AbstractGameObject {
    boolean live = true;
    public boolean isLive(){
        return live;
    }
    public void setLive(boolean live){
        this.live = live;
    }
    public abstract void draw(Graphics2D g2);
}
