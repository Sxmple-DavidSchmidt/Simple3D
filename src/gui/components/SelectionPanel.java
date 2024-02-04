package gui.components;

import world.World;
import world.objects.Object3D;

import javax.swing.*;
import java.awt.*;

public class SelectionPanel extends JPanel {
    private final JList<Object3D> objects;

    public SelectionPanel() {
        setLayout(new GridLayout(2, 1));
        objects = new JList<>();
        ObjectControlContainer poc = new ObjectControlContainer(null);
        objects.addListSelectionListener(e -> poc.setTarget(objects.getSelectedValue()));

        add(objects);
        add(poc);
    }

    public void setWorld(World world) {
        this.objects.setListData(world.getObjects());
    }
}
