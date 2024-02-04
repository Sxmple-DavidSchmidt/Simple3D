package gui.components;

import util.Vec3;
import world.objects.Object3D;

import javax.swing.*;
import java.awt.*;

public class ObjectControlContainer extends ControlElementContainer<Object3D> {
    SingleValueControlElement p1, p2, p3 ;
    SingleValueControlElement o1, o2, o3;


    public ObjectControlContainer(Object3D target) {
        this.target = target;

        setLayout(new GridLayout(8, 1));
        p1 = new SingleValueControlElement(this, 0, "x", 1);
        p2 = new SingleValueControlElement(this, 1, "y", 1);
        p3 = new SingleValueControlElement(this, 2, "z", 1);

        o1 = new SingleValueControlElement(this, 3, "x", 0.1);
        o2 = new SingleValueControlElement(this, 4, "y", 0.1);
        o3 = new SingleValueControlElement(this, 5, "z", 0.1);

        add(new JLabel("Position", JLabel.CENTER));
        add(p1);
        add(p2);
        add(p3);

        add(new JLabel("Orientation", JLabel.CENTER));
        add(o1);
        add(o2);
        add(o3);
    }

    @Override
    public void registerUpdate(int id, double value) {
        if (target == null) return;

        switch (id) {
            case 0 -> target.getOrigin().x = value;
            case 1 -> target.getOrigin().y = value;
            case 2 -> target.getOrigin().z = value;
            case 3 -> target.getOrientation().x = value;
            case 4 -> target.getOrientation().y = value;
            case 5 -> target.getOrientation().z = value;
        }
    }

    @Override
    public void update() {
        if (target == null) return;

        Vec3 orientation = target.getOrientation();
        Vec3 origin = target.getOrigin();

        o1.setValue(orientation.x);
        o2.setValue(orientation.y);
        o3.setValue(orientation.z);

        p1.setValue(origin.x);
        p2.setValue(origin.y);
        p3.setValue(origin.z);
    }
}
