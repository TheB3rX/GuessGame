package views;

import javax.swing.*;
import java.awt.*;

public class HistoryInfo extends JFrame {
    private JPanel panel;
    private JList jScoreList;

    public HistoryInfo(){
        initializeGUI();
        pack();
    }

    private void initializeGUI() {
        String[] jScoreListItems = {};
        //construct components
        jScoreList = new JList(jScoreListItems);
        addGUIComponents();
        setComponentBounds();
        adjustGUI();
    }

    private void adjustGUI() {
        setTitle("Historial de usuario");
        setPreferredSize(new Dimension(420, 310));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void setComponentBounds() {
        panel.setLayout(null);
        jScoreList.setBounds(10, 10, 380, 250);
        panel.add(jScoreList);
    }

    private void addGUIComponents() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.add(jScoreList);
        add(panel);
    }
}
