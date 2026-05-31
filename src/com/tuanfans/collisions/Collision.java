package com.tuanfans.collisions;

import com.tuanfans.AbstractGameObject;

/**
 * @author TuanFans
 * @date 2026/5/31
 */
public interface Collision {
    boolean collide(AbstractGameObject ago1, AbstractGameObject ago2);
}
