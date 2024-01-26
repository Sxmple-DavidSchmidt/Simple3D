package gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class UserInterface {
    private JFrame frame;
    private Window window;
    private ControlPanel controlPanel;

    public UserInterface() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame();
        frame.setTitle("3D-Project");
        frame.setSize(d.width / 2, d.height / 2);
        frame.setLocation(d.width / 4, d.height / 4);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        window = new Window();
        window.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        controlPanel = new ControlPanel();

        frame.add(window, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.WEST);
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
