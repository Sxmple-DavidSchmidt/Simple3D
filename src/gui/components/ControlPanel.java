package gui.components;

import gui.Controller;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    public ControlPanel() {
        setLayout(new BorderLayout());

        CameraControlContainer cco = new CameraControlContainer(Controller.world.getCamera());
        add(cco, BorderLayout.CENTER);
        this.getPreferredSize();
    }
}
