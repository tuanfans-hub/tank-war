package com.tuanfans.strategy.shoot;

import com.tuanfans.Group;
import com.tuanfans.bullet.Bullet;
import com.tuanfans.tank.PlayerTank;
import com.tuanfans.tank.Tank;
import com.tuanfans.view.TankPanel;

/**
 * @author TuanFans
 * @date 2026/5/31
 */
public class DefaultShootStrategy implements ShootStrategy{
    @Override
    public void shoot(PlayerTank pt) {
        Bullet b = Bullet.createBullet(pt.getX()+ Tank.SIZE/2-Bullet.SIZE/2,pt.getY()+Tank.SIZE/2-Bullet.SIZE/2,pt.getDirection(), Group.PLAYER);
        TankPanel.getInstance().add(b);
    }
}
