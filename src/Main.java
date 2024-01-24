import gui.Window;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(1920 / 2, 1080 / 2);

        Window w = new Window();
        w.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        frame.add(w, BorderLayout.CENTER);
        frame.add(new JLabel("Slider-settings coming soon"), BorderLayout.NORTH);

        frame.setVisible(true);

        new Thread(() -> {
            while (true) {
                frame.repaint();
                try {
                    Thread.sleep(15);
                } catch (InterruptedException ex) {
                    System.out.println("Failed to sleep thread");
                }
            }
        }).start();
    }
}