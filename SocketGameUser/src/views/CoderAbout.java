package views;

import javax.swing.*;
import java.awt.*;

public class CoderAbout extends JFrame {
    private JLabel jLabel;

    public CoderAbout() {
        initializeGUI();
        pack();
    }

    private void initializeGUI() {
        jLabel = new JLabel("<html>Created By<br/>Esteban Ruiz<br>Codename: B3rX<br/></html>");

        adjustGUI();
        addInfo();
    }

    private void adjustGUI() {
        setPreferredSize(new Dimension(200, 150));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setVisible(true);
    }

    private void addInfo() {
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        jLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        jLabel.setForeground(Color.BLACK);
        add(jLabel);
    }
}
