package com.tuanfans.view;

import com.tuanfans.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author TuanFans
 * @date 2026/5/29
 */
public class TankPanel extends JPanel implements Runnable {
    private static TankPanel INSTANCE;
    private static GameModel gameModel;
    Thread gameThread;
    public KeyListener keyHandler;
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    public static final int GAME_FPS = 60;

    private TankPanel(){
        this.setPreferredSize(new Dimension(GAME_WIDTH,GAME_HEIGHT));
        this.setBackground(Color.BLACK);
        // 启用双缓冲技术，将图形先绘制到内存缓冲区，再一次性显示到屏幕，有效消除画面闪烁现象，提升游戏渲染的流畅度和视觉体验。
        this.setDoubleBuffered(true);

        // 获取焦点
        this.setFocusable(true);

        this.keyHandler = new KeyHandler();
        this.addKeyListener(keyHandler);
        gameModel = GameModel.getInstance();
    }

    public static TankPanel getInstance(){
        if(INSTANCE==null){
            INSTANCE = new TankPanel();
        }
        return INSTANCE;
    }


    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 =  (Graphics2D) g;
        gameModel.draw(g2);
    }

    private void update(){
        gameModel.update();
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
                        this.update();
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
            gameModel.getPt().keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            gameModel.getPt().keyReleased(e);
        }


    }
}
