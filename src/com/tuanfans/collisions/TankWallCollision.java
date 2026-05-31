package com.tuanfans.collisions;

import com.tuanfans.AbstractGameObject;
import com.tuanfans.tank.Tank;
import com.tuanfans.wall.Wall;

/**
 * @author TuanFans
 * @date 2026/5/31
 */
public class TankWallCollision implements Collision{
    @Override
    public boolean collide(AbstractGameObject ago1, AbstractGameObject ago2) {
        if(ago1 instanceof Tank tank && ago2 instanceof Wall wall){
            if(!tank.isLive()) return false;
            if(tank.getRect().intersects(wall.getRect())){
                tank.back();
            }
        }else if(ago1 instanceof Wall wall && ago2 instanceof Tank tank){
            collide(tank,wall);
        }
        return true;
    }
}
