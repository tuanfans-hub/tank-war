package com.tuanfans.tank;

import com.tuanfans.Direction;
import com.tuanfans.Group;
import com.tuanfans.bullet.Bullet;
import com.tuanfans.view.TankPanel;

import javax.swing.*;
import java.util.Objects;

/**
 * @author TuanFans
 * @date 2026/5/29
 */
public class EnemyTank extends Tank{
    private EnemyTank(){
        this.speed = (int)(Math.random()*TankPanel.gameLevel.getFactor()+TankPanel.gameLevel.getBaseSpeed());
        this.x = (int)(Math.random()*(TankPanel.GAME_WIDTH-SIZE));
        this.y = (int)(Math.random()*(TankPanel.GAME_HEIGHT-SIZE));
        this.direction = Direction.values()[(int)(Math.random()*4)];
        this.group = Group.ENEMY;
        this.image = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("images/BadTank1.png"))).getImage();
    }

    public static EnemyTank createEnemyTank(){
        return new EnemyTank();
    }

    @Override
    public void move() {
        if(direction == Direction.UP){
            y=y>0?y-speed:0;
        }
        if(direction == Direction.DOWN){
            y=y< TankPanel.GAME_HEIGHT-Tank.SIZE?y+speed:TankPanel.GAME_HEIGHT-Tank.SIZE;
        }
        if(direction == Direction.LEFT){
            x=x>0?x-speed:0;
        }
        if(direction == Direction.RIGHT){
            x=x<TankPanel.GAME_WIDTH-Tank.SIZE?x+speed:TankPanel.GAME_WIDTH-Tank.SIZE;
        }
        if(Math.random()<0.02){
            int length = Direction.values().length;
            this.direction = Direction.values()[(int)(Math.random()*length)];
        }
        if(Math.random()>=1-TankPanel.gameLevel.getShootRate()){
            shoot();
        }
    }

    @Override
    void shoot(){
        Bullet b = Bullet.createBullet(x+Tank.SIZE/2-Bullet.SIZE/2,y+Tank.SIZE/2-Bullet.SIZE/2,direction, Group.ENEMY);
        TankPanel.bullets.add(b);
    }
}
