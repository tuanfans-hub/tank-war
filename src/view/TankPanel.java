package view;

import tank.PlayerTank;
import tank.Tank;

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
    PlayerTank pt ;

    Thread gameThread;

    public KeyListener keyHandler;

    public TankPanel(){
        this.setPreferredSize(new Dimension(800,600));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        // 获取焦点
        this.setFocusable(true);

        this.keyHandler = new KeyHandler();
        this.addKeyListener(keyHandler);
        this.pt = new PlayerTank(100,100, Tank.Direction.UP);

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
        g2.dispose();
    }

    private void setLocation(){
        pt.move();
    }

    @Override
    public void run() {
        if(gameThread!=null){
            try{
                long last = System.nanoTime();
                double interval = 1000000000.0/60;
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
