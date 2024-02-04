package gui;

import gui.components.ControlPanel;
import gui.components.SelectionPanel;

import javax.swing.*;
import java.awt.*;

public class UserInterface {
    private final JFrame frame;

    public UserInterface() {
        Window window = new Window();
        ControlPanel controlPanel = new ControlPanel();
        SelectionPanel selectionPanel = new SelectionPanel();

        window.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Controller.window = window;
        selectionPanel.setWorld(Controller.world);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame();
        frame.setTitle("3D-Project");
        frame.setSize(d.width / 2, d.height / 2);
        frame.setLocation(d.width / 4, d.height / 4);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));
        frame.add(controlPanel, BorderLayout.WEST);
        frame.add(window, BorderLayout.CENTER);
        frame.add(selectionPanel, BorderLayout.EAST);

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
