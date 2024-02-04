package gui.components;

import javax.swing.*;
import java.awt.*;

public class SingleValueControlElement extends JPanel {
    private final ControlElementContainer parent;
    private final int id;
    private double value;
    private final JTextField tfValue;


    public SingleValueControlElement(ControlElementContainer parent, int id, String descriptor, double increment) {
        this.value = 0;
        this.id = id;
        this.parent = parent;

        setLayout(new BorderLayout());
        tfValue = new JTextField();
        tfValue.addActionListener(e -> {
            double dValue;
            try {
                dValue = Double.parseDouble(tfValue.getText());
            } catch (NumberFormatException exception) {
                dValue = value;
            }

            setValue(dValue);
        });

        JLabel jlDesc = new JLabel(descriptor, JLabel.CENTER);
        JButton jbDec = new JButton("<");
        JButton jbInc = new JButton(">");

        jbDec.addActionListener(e -> setValue(value - increment));
        jbInc.addActionListener(e -> setValue(value + increment));

        add(jlDesc, BorderLayout.NORTH);
        add(tfValue, BorderLayout.CENTER);
        add(jbDec, BorderLayout.WEST);
        add(jbInc, BorderLayout.EAST);
    }

    public void setValue(double value) {
        this.value = value;
        this.tfValue.setText(String.valueOf(value));
        registerUpdate();
    }

    public void registerUpdate() {
        this.parent.registerUpdate(id, value);
    }
}
