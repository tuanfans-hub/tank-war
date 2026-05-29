import view.TankPanel;

import javax.swing.*;

/**
 * @author TuanFans
 * @date 2026/5/29
 */
public class Main {
    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setTitle("坦克大战v1.0");
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TankPanel tp = new TankPanel();
        window.add(tp);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        tp.startGameThread();
    }
}
