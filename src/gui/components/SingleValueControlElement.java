package gui.components;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SingleValueControlElement extends JPanel {
    private ControlElementContainer parent;
    private int id;
    private double value;
    private JTextField tfValue;


    public SingleValueControlElement(int id, String descriptor, ControlElementContainer parent) {
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

        JLabel jlDesc = new JLabel(descriptor);
        JButton jbDec = new JButton("<");
        JButton jbInc = new JButton(">");

        jbDec.addActionListener(e -> setValue(value + 5));
        jbInc.addActionListener(e -> setValue(value - 5));

        add(jlDesc, BorderLayout.WEST);
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
