import gui.Window;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(1920 / 2, 1080 / 2);
        frame.add(new Window(), BorderLayout.CENTER);
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