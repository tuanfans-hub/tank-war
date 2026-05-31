package com.tuanfans.collisions;

import com.tuanfans.AbstractGameObject;
import com.tuanfans.ConfigManager;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * @author TuanFans
 * @date 2026/5/31
 * @desc 链式碰撞：责任链设计模式
 */
public class CollisionChain implements Collision {
    private final ArrayList<Collision> collisions = new ArrayList<>();

    public CollisionChain(){
        initCollisions();
    }

    private void initCollisions(){
        String[] collisionNames = ConfigManager.get("collision.kinds").split(",");
        for(String collisionName:collisionNames){
            try {
                Class<?> clazz = Class.forName("com.tuanfans.collisions." + collisionName);
                Collision collision = (Collision)clazz.getDeclaredConstructor().newInstance();
                collisions.add(collision);
            } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException |
                     NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public boolean collide(AbstractGameObject ago1, AbstractGameObject ago2) {
        for(Collision collision:collisions){
            if(!collision.collide(ago1,ago2)){
                return false;
            }
        }
        return true;
    }
}
