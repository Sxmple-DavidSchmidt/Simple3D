package gui;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class UserInterface {
    private final JFrame frame;

    public UserInterface() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame();
        frame.setTitle("3D-Project");
        frame.setSize(d.width / 2, d.height / 2);
        frame.setLocation(d.width / 4, d.height / 4);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        Window window = new Window();
        window.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        frame.add(window, BorderLayout.CENTER);

        ControlPanel controlPanel = new ControlPanel();
        frame.add(controlPanel, BorderLayout.WEST);

        frame.setVisible(true);

        Controller.window = window;

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
