package views;

import models.UserInfo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ShowRecords extends JFrame {
    //Create GUI that shows the records that comes from the server
    private JList jList;
    private JScrollPane jScrollPane;
    private JTextField jTextField;

    public ShowRecords(ArrayList<UserInfo> list) {
        initializeGUI(list);
        pack();
    }

    private void initializeGUI(ArrayList<UserInfo> list) {
        setPreferredSize(new Dimension(200, 500));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        addListInfo(list);

        setVisible(true);
    }

    public void addListInfo(ArrayList<UserInfo> list) {
        jList.setModel(modelList(list));
    }

    private DefaultListModel modelList(ArrayList<UserInfo> list) {
        DefaultListModel model = new DefaultListModel();
        for (UserInfo userInfo : list) {
            model.addElement(userInfo.getNumber() + " Attempts");
        }
        return model;
    }


}
