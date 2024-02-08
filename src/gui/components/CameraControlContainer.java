package gui.components;

import util.Vec3;
import world.Camera;

import javax.swing.*;
import java.awt.*;

public class CameraControlContainer extends ControlElementContainer<Camera> {
    SingleValueControlElement p1, p2, p3 ;
    SingleValueControlElement o1, o2, o3;


    public CameraControlContainer(Camera target) {
        this.target = target;

        setLayout(new BorderLayout(0, 0));
        p1 = new SingleValueControlElement(this, 0, "x", 1);
        p2 = new SingleValueControlElement(this, 1, "y", 1);
        p3 = new SingleValueControlElement(this, 2, "z", 1);

        o1 = new SingleValueControlElement(this, 3, "x", 0.1);
        o2 = new SingleValueControlElement(this, 4, "y", 0.1);
        o3 = new SingleValueControlElement(this, 5, "z", 0.1);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2, 1));

        JPanel positionPanel = new JPanel();
        JPanel positionSubPanel = new JPanel();
        positionSubPanel.setLayout(new GridLayout(3, 1));
        positionSubPanel.add(p1);
        positionSubPanel.add(p2);
        positionSubPanel.add(p3);
        positionPanel.setLayout(new BorderLayout());
        positionPanel.add(new JLabel("Position", JLabel.CENTER), BorderLayout.NORTH);
        positionPanel.add(positionSubPanel, BorderLayout.CENTER);

        JPanel orientationPanel = new JPanel();
        JPanel orientationSubPanel = new JPanel();
        orientationSubPanel.setLayout(new GridLayout(3, 1));
        orientationSubPanel.add(o1);
        orientationSubPanel.add(o2);
        orientationSubPanel.add(o3);
        orientationPanel.setLayout(new BorderLayout());
        orientationPanel.add(new JLabel("Orientation", JLabel.CENTER), BorderLayout.NORTH);
        orientationPanel.add(orientationSubPanel, BorderLayout.CENTER);

        controlPanel.add(positionPanel);
        controlPanel.add(orientationPanel);

        add(new JLabel("Camera position / orientation", JLabel.CENTER), BorderLayout.NORTH);
        add(controlPanel, BorderLayout.CENTER);
    }

    @Override
    public void registerUpdate(int id, double value) {
        if (target == null) return;

        Vec3 position = target.getPosition();
        Vec3 orientation = target.getOrientation();

        switch (id) {
            case 0 -> position.x = value;
            case 1 -> position.y = value;
            case 2 -> position.z = value;
            case 3 -> orientation.x = value;
            case 4 -> orientation.y = value;
            case 5 -> orientation.z = value;
        }
    }

    @Override
    public void update() {
        if (target == null) return;

        Vec3 orientation = target.getOrientation();
        Vec3 origin = target.getPosition();

        o1.setValue(orientation.x);
        o2.setValue(orientation.y);
        o3.setValue(orientation.z);

        p1.setValue(origin.x);
        p2.setValue(origin.y);
        p3.setValue(origin.z);
    }
}
