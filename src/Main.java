import gui.Window;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        Window w = new Window();
        w.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JFrame frame = new JFrame();
        frame.setTitle("3D-Project");
        frame.setSize(d.width / 2, d.height / 2);
        frame.setLocation(d.width / 4, d.height / 4);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        frame.add(w, BorderLayout.CENTER);
        frame.add(new JSlider(), BorderLayout.WEST);

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