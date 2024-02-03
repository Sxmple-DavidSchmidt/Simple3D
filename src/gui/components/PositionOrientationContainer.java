package gui.components;

import util.Vec3;
import world.objects.Object3D;

import javax.swing.*;
import java.awt.*;

public class PositionOrientationContainer extends ControlElementContainer {
    SingleValueControlElement p1, p2, p3 ;
    SingleValueControlElement o1, o2, o3;


    public PositionOrientationContainer(Object3D object) {
        this.object = object;

        setLayout(new GridLayout(8, 1));
        p1 = new SingleValueControlElement(0, "x", this);
        p2 = new SingleValueControlElement(1, "y", this);
        p3 = new SingleValueControlElement(2, "z", this);

        o1 = new SingleValueControlElement(3, "x", this);
        o2 = new SingleValueControlElement(4, "y", this);
        o3 = new SingleValueControlElement(5, "z", this);

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
        if (object == null) return;

        switch (id) {
            case 0 -> object.getOrigin().x = value;
            case 1 -> object.getOrigin().y = value;
            case 2 -> object.getOrigin().z = value;
            case 3 -> object.getOrientation().x = value;
            case 4 -> object.getOrientation().y = value;
            case 5 -> object.getOrientation().z = value;
        }
    }

    @Override
    public void update() {
        if (object == null) return;

        Vec3 orientation = object.getOrientation();
        Vec3 origin = object.getOrigin();

        o1.setValue(orientation.x);
        o2.setValue(orientation.y);
        o3.setValue(orientation.z);

        p1.setValue(origin.x);
        p2.setValue(origin.y);
        p3.setValue(origin.z);
    }
}
