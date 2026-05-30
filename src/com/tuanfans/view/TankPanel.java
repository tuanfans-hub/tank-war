package com.tuanfans.view;

import com.tuanfans.Direction;
import com.tuanfans.bullet.Bullet;
import com.tuanfans.tank.EnemyTank;
import com.tuanfans.tank.EnemyTankFactory;
import com.tuanfans.tank.PlayerTank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * @author TuanFans
 * @date 2026/5/29
 */
public class TankPanel extends JPanel implements Runnable {
    PlayerTank pt ;
    ArrayList<EnemyTank> enemyTanks;
    public static final ArrayList<Bullet> bullets = new ArrayList<>();
    Thread gameThread;

    public KeyListener keyHandler;

    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    public static final int GAME_FPS = 60;

    public TankPanel(){
        this.setPreferredSize(new Dimension(GAME_WIDTH,GAME_HEIGHT));
        this.setBackground(Color.BLACK);
        // 启用双缓冲技术，将图形先绘制到内存缓冲区，再一次性显示到屏幕，有效消除画面闪烁现象，提升游戏渲染的流畅度和视觉体验。
        this.setDoubleBuffered(true);

        // 获取焦点
        this.setFocusable(true);

        this.keyHandler = new KeyHandler();
        this.addKeyListener(keyHandler);
        this.pt = new PlayerTank(100,100, Direction.UP);
        this.enemyTanks = EnemyTankFactory.initEnemyTanks(3);
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
        pt.draw(g2);
        for(EnemyTank enemyTank: enemyTanks){
            enemyTank.draw(g2);
        }
        for(Bullet bullet: bullets){
            bullet.draw(g2);
        }
        g2.dispose();
    }

    private void setLocation(){
        pt.move();
        for(EnemyTank enemyTank: enemyTanks){
            enemyTank.move();
        }
        for(int i=0;i<bullets.size();i++){
            bullets.get(i).move(bullets);
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
