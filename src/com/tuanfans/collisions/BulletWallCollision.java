package com.tuanfans.collisions;

import com.tuanfans.AbstractGameObject;
import com.tuanfans.bullet.Bullet;
import com.tuanfans.wall.Wall;

/**
 * @author TuanFans
 * @date 2026/5/31
 */
public class BulletWallCollision implements Collision{
    @Override
    public boolean collide(AbstractGameObject ago1, AbstractGameObject ago2) {
        if(ago1 instanceof Bullet bullet && ago2 instanceof Wall wall){
            if(!bullet.isLive()) return false;
            if(bullet.getRect().intersects(wall.getRect())) bullet.setLive(false);
        }else if(ago1 instanceof Wall wall && ago2 instanceof Bullet bullet){
            collide(bullet,wall);
        }
        return true;
    }
}
