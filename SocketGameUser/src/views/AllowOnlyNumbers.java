package views;

import javax.swing.*;
import java.awt.event.KeyAdapter;

public class AllowOnlyNumbers extends KeyAdapter {

    private JTextField tf;
    private JLabel label;
    public AllowOnlyNumbers(JLabel jWelcome, JTextField jInputArea) {
        this.tf = jInputArea;
        this.label = jWelcome;
    }

    @Override
    public void keyTyped(java.awt.event.KeyEvent ke) {
        String value = tf.getText();
        int l = value.length();
        if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
            tf.setEditable(true);
            label.setText("Welcome");
        } else {
            tf.setEditable(false);
            label.setText("* Enter only numeric digits(0-9)");
        }
    }
}
