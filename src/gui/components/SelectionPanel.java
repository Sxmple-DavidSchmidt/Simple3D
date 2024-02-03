package gui.components;

import util.Vec3;
import world.World;
import world.objects.Object3D;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class SelectionPanel extends JPanel {
    private World world;
    private JList<Object3D> objects;

    public SelectionPanel() {
        setLayout(new GridLayout(2, 1));
        objects = new JList<>();
        PositionOrientationContainer poc = new PositionOrientationContainer(null);
        objects.addListSelectionListener(e -> {
            Object3D object = objects.getSelectedValue();
            poc.setObject(object);
        });

        add(objects);
        add(poc);
    }

    public void setWorld(World world) {
        this.world = world;
        this.objects.setListData(world.getObjects());
    }
}
