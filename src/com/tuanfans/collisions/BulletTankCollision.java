package com.tuanfans.collisions;

import com.tuanfans.AbstractGameObject;
import com.tuanfans.Group;
import com.tuanfans.bullet.Bullet;
import com.tuanfans.tank.Tank;
import com.tuanfans.view.TankPanel;

/**
 * @author TuanFans
 * @date 2026/5/31
 */
public class BulletTankCollision implements Collision {
    @Override
    public boolean collide(AbstractGameObject ago1, AbstractGameObject ago2) {
        // java 16模式变量
        if(ago1 instanceof Bullet bullet && ago2 instanceof Tank tank){
            if(!bullet.isLive()||!tank.isLive()) return false;
            if (bullet.getRect().intersects(tank.getRect())) {
                if (tank.getGroup() != bullet.getGroup()) {
                    tank.setLive(false);
                    bullet.setLive(false);
                    tank.explode();
                }
            }
        }else if(ago1 instanceof Tank tank && ago2 instanceof Bullet bullet){
            collide(bullet, tank);
        }
        return true;
    }
}
