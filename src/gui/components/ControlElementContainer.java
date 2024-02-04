package gui.components;

import javax.swing.*;

public abstract class ControlElementContainer<TargetType> extends JPanel {
    protected TargetType target;

    public abstract void registerUpdate(int id, double value);
    public void update () {}
    public void setTarget(TargetType target) {
        this.target = target;
        this.update();
    }
}
