package com.tuanfans.tank;

import com.tuanfans.Direction;
import com.tuanfans.view.TankPanel;

import javax.swing.*;
import java.util.Objects;

/**
 * @author TuanFans
 * @date 2026/5/29
 */
public class EnemyTank extends Tank{
    public EnemyTank(){
        this.speed = (int)(Math.random()*6+5);
        this.size = 40;
        this.x = (int)(Math.random()*(TankPanel.GAME_WIDTH-size));
        this.y = (int)(Math.random()*(TankPanel.GAME_HEIGHT-size));
        this.direction = Direction.values()[(int)(Math.random()*4)];
        this.image = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("images/BadTank1.png"))).getImage();
    }
    @Override
    public void move() {

    }

//    @Override
//    public void draw(Graphics2D g) {
//
//    }
}
