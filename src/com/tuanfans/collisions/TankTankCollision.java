package com.tuanfans.collisions;

import com.tuanfans.AbstractGameObject;
import com.tuanfans.tank.Tank;

/**
 * @author TuanFans
 * @date 2026/5/31
 */
public class TankTankCollision implements Collision{
    @Override
    public boolean collide(AbstractGameObject ago1, AbstractGameObject ago2) {
        if(ago1 instanceof Tank tank1 && ago2 instanceof Tank tank2){
            if(tank1==tank2) return false;
            if(!tank1.isLive()||!tank2.isLive()) return false;
            if(tank1.getRect().intersects(tank2.getRect())){
                tank1.back();
                tank2.back();
            }
        }
        return true;
    }
}
