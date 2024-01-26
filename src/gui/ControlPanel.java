package gui;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    public ControlPanel() {
        setLayout(new GridLayout(6, 0));

        JSlider px = new JSlider(-50, 50, (int) Controller.CAMERA_POSITION_X);
        px.setPaintLabels(true);
        px.setPaintTicks(true);
        px.setMajorTickSpacing(25);
        px.setMinorTickSpacing(5);
        px.addChangeListener(e -> Controller.CAMERA_POSITION_X = px.getValue());

        JSlider py = new JSlider(-50, 50, (int) Controller.CAMERA_POSITION_Y);
        py.setPaintLabels(true);
        py.setPaintTicks(true);
        py.setMajorTickSpacing(25);
        py.setMinorTickSpacing(5);
        py.addChangeListener(e -> Controller.CAMERA_POSITION_Y = py.getValue());

        JSlider pz = new JSlider(-50, 50, (int) Controller.CAMERA_POSITION_Z);
        pz.setPaintLabels(true);
        pz.setPaintTicks(true);
        pz.setMajorTickSpacing(25);
        pz.setMinorTickSpacing(5);
        pz.addChangeListener(e -> Controller.CAMERA_POSITION_Z = pz.getValue());

        add(px);
        add(py);
        add(pz);

        JSlider ox = new JSlider(-180, 180, 0);
        ox.setPaintLabels(true);
        ox.setPaintTicks(true);
        ox.setMajorTickSpacing(60);
        ox.setMinorTickSpacing(30);
        ox.addChangeListener(e -> Controller.CAMERA_ORIENTATION_X = ox.getValue() / 360.0);

        JSlider oy = new JSlider(-180, 180, 0);
        oy.setPaintLabels(true);
        oy.setPaintTicks(true);
        oy.setMajorTickSpacing(60);
        oy.setMinorTickSpacing(30);
        oy.addChangeListener(e -> Controller.CAMERA_ORIENTATION_Y = oy.getValue() / 360.0);

        JSlider oz = new JSlider(-180, 180, 0);
        oz.setPaintLabels(true);
        oz.setPaintTicks(true);
        oz.setMajorTickSpacing(60);
        oz.setMinorTickSpacing(30);
        oz.addChangeListener(e -> Controller.CAMERA_ORIENTATION_Z = oz.getValue() / 360.0);

        add(ox);
        add(oy);
        add(oz);
    }
}
