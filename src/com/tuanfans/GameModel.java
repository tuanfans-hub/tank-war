package com.tuanfans;

import com.tuanfans.collisions.CollisionChain;
import com.tuanfans.tank.EnemyTankFactory;
import com.tuanfans.tank.PlayerTank;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author TuanFans
 * @date 2026/6/1
 * @desc 管理应用数据：实现Model与View分离
 */
public class GameModel {
    private static GameModel INSTANCE;
    final PlayerTank pt ;
    final ArrayList<AbstractGameObject> gameObjects;
    final CollisionChain collisionChain;
    public static boolean gameOver = false;
    public static Level gameLevel = Level.CUSTOM;
    private GameModel(){
        this.pt = new PlayerTank(100,100, Direction.UP);
        this.gameObjects = new ArrayList<>(EnemyTankFactory.initEnemyTanks(gameLevel));
        this.collisionChain = new CollisionChain();
    }
    public static GameModel getInstance(){
        if(INSTANCE==null){
            INSTANCE = new GameModel();
        }
        return INSTANCE;
    }
    public PlayerTank getPt() {
        return pt;
    }

    public ArrayList<AbstractGameObject> getGameObjects() {
        return gameObjects;
    }

    public CollisionChain getCollisionChain() {
        return collisionChain;
    }
    public void add(AbstractGameObject gameObject){
        gameObjects.add(gameObject);
    }

    public void remove(AbstractGameObject gameObject){
        gameObjects.remove(gameObject);
    }
    public void update(){
        pt.move();
        synchronized(gameObjects){
            for(int i=gameObjects.size()-1;i>=0;i--){
                if(gameObjects.get(i) instanceof Moveable moveObj){
                    moveObj.move();
                }
            }
        }
    }
    public void draw(Graphics2D g2){
        Graphics2D g2d = (Graphics2D) g2.create();
        g2d.setColor(Color.WHITE);
        g2d.drawString("gameObjects："+gameObjects.size(),10,20);
        pt.draw(g2d);
        synchronized(gameObjects){
            for(int i=gameObjects.size()-1;i>=0;i--){
                AbstractGameObject obj1 = gameObjects.get(i);
                for(int j=i-1;j>=0;j--){
                    if(!obj1.isLive()) break;
                    AbstractGameObject obj2 = gameObjects.get(j);
                    // 碰撞检测
                    collisionChain.collide(obj1,obj2);
                }
                obj1.draw(g2d);
            }
        }
        g2d.dispose();
    }
}
