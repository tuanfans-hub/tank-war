package com.tuanfans.view;

import com.tuanfans.*;
import com.tuanfans.collisions.BulletTankCollision;
import com.tuanfans.collisions.Collision;
import com.tuanfans.collisions.CollisionChain;
import com.tuanfans.tank.EnemyTankFactory;
import com.tuanfans.tank.PlayerTank;
import com.tuanfans.wall.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * @author TuanFans
 * @date 2026/5/29
 */
public class TankPanel extends JPanel implements Runnable {
    private static TankPanel INSTANCE;
    final PlayerTank pt ;
//    final ArrayList<EnemyTank> enemyTanks;
//    final ArrayList<Tank> tanks;
//    public static final ArrayList<Bullet> bullets = new ArrayList<>();
//    public static final ArrayList<Explode> explodes = new ArrayList<>();
    final ArrayList<AbstractGameObject> gameObjects;
    final CollisionChain collisionChain;
    Thread gameThread;
    public KeyListener keyHandler;

    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    public static final int GAME_FPS = 60;
    public static boolean isGameOver = false;
    public static Level gameLevel = Level.CUSTOM;
    private TankPanel(){
        this.setPreferredSize(new Dimension(GAME_WIDTH,GAME_HEIGHT));
        this.setBackground(Color.BLACK);
        // 启用双缓冲技术，将图形先绘制到内存缓冲区，再一次性显示到屏幕，有效消除画面闪烁现象，提升游戏渲染的流畅度和视觉体验。
        this.setDoubleBuffered(true);

        // 获取焦点
        this.setFocusable(true);

        this.keyHandler = new KeyHandler();
        this.addKeyListener(keyHandler);
        this.pt = new PlayerTank(100,100, Direction.UP);
//        this.enemyTanks = EnemyTankFactory.initEnemyTanks(gameLevel);
//        this.tanks = new ArrayList<>(enemyTanks);
//        this.tanks.add(pt);
        this.gameObjects = new ArrayList<>(EnemyTankFactory.initEnemyTanks(gameLevel));
        this.add(new Wall(300,200,300,50));
        this.collisionChain = new CollisionChain();
    }

    public static TankPanel getInstance(){
        if(INSTANCE==null){
            INSTANCE = new TankPanel();
        }
        return INSTANCE;
    }

    public void add(AbstractGameObject gameObject){
        gameObjects.add(gameObject);
    }

    public void remove(AbstractGameObject gameObject){
        gameObjects.remove(gameObject);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 =  (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.drawString("gameObjects："+gameObjects.size(),10,20);
        pt.draw(g2);
//        synchronized(bullets){
//            for(int i=bullets.size()-1;i>=0;i--){
//                bullets.get(i).draw(g2);
//            }
//        }
//        synchronized(enemyTanks){
//            for(int i=enemyTanks.size()-1;i>=0;i--){
//                enemyTanks.get(i).draw(g2);
//            }
//        }
//        synchronized(explodes){
//            for(int i=explodes.size()-1;i>=0;i--){
//                Explode explode = explodes.get(i);
//                if(explode.isLive()) explode.draw(g2);
//                else explodes.remove(i);
//            }
//        }
        synchronized(gameObjects){
            for(int i=gameObjects.size()-1;i>=0;i--){
                AbstractGameObject obj1 = gameObjects.get(i);
                for(int j=i-1;j>=0;j--){
                    if(!obj1.isLive()) break;
                    AbstractGameObject obj2 = gameObjects.get(j);
                    // 碰撞检测
                    collisionChain.collide(obj1,obj2);
                }
                obj1.draw(g2);
            }
        }
        g2.dispose();
    }

    private void setLocation(){
        pt.move();
//        synchronized(bullets){
//            for(int i=bullets.size()-1;i>=0;i--){
//                // 检测子弹是否击中坦克
//                bullets.get(i).collision(tanks);
//                if(bullets.get(i).isLive()) bullets.get(i).move();
//                else bullets.remove(i);
//            }
//        }
//        synchronized(tanks){
//            for(int i=tanks.size()-1;i>=0;i--){
//                Tank tank = tanks.get(i);
//                if(tank.isLive()) tank.move();
//                else {
//                    tanks.remove(i);
//                    if(tank.getGroup() == Group.ENEMY){
//                        enemyTanks.remove(i);
//                    }else{
//                        isGameOver = true;
//                        System.out.println("游戏结束");
//                    }
//                }
//            }
//        }
        synchronized(gameObjects){
            for(int i=gameObjects.size()-1;i>=0;i--){
                if(gameObjects.get(i) instanceof Moveable moveObj){
                    moveObj.move();
                }
            }
        }

    }

    @Override
    public void run() {
        if(gameThread!=null){
            try{
                long last = System.nanoTime();
                double interval = 1000000000.0/GAME_FPS;
                double delta = 0;
                while(true){
                    long current = System.nanoTime();
                    delta += (current - last)/interval;
                    last = current;
                    if(delta>=1){
                        setLocation();
                        this.repaint();
                        delta--;
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            pt.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            pt.keyReleased(e);
        }


    }
}
