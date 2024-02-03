package gui.components;

import world.objects.Object3D;

import javax.swing.*;
import java.awt.*;

public abstract class ControlElementContainer extends JPanel {
    protected Object3D object;

    public abstract void registerUpdate(int id, double value);
    public void update () {};
    public void setObject(Object3D object) {
        this.object = object;
        this.update();
    }
}
