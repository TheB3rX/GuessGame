package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class UserGUI extends JFrame {
    private JPanel panel;
    private JButton jBVerifyNumber;
    private JLabel jWelcome;
    private JTextField jInput;
    private JList jScoreList;
    private JMenuBar jMenuBar;

    private ActionListener listener;

    public UserGUI(ActionListener actionListener) {
        this.listener = actionListener;
        initializeGUI();
        pack();
    }

    private void initializeGUI() {
        String[] jScoreListItems = {};
        //construct components
        jBVerifyNumber = new JButton("Verificar");
        jWelcome = new JLabel("Bienvenido, ingrese solo números");
        jInput = new JTextField(5);
        jScoreList = new JList(jScoreListItems);
        jMenuBar = new JMenuBar();

        setJMenuBar();
        sendToController();
        setOnlyKeys(jInput);
        addGUIComponents();
        setComponentBounds();
        adjustGUI();
    }

    private void setJMenuBar() {
        //create a JMenuBar with a JMenu with options to exit the program and to show developer info
        JMenu jMenu = new JMenu("Opciones");
        jMenu.setMnemonic(KeyEvent.VK_O);
        jMenu.getAccessibleContext().setAccessibleDescription("Opciones");
        JMenuItem jMenuItem = new JMenuItem("Salir");
        jMenuItem.setMnemonic(KeyEvent.VK_S);
        jMenuItem.getAccessibleContext().setAccessibleDescription("Salir");
        jMenuItem.addActionListener(listener);
        jMenuItem.setActionCommand("exit");
        jMenu.add(jMenuItem);
        jMenuItem = new JMenuItem("Acerca de");
        jMenuItem.setMnemonic(KeyEvent.VK_A);
        jMenuItem.getAccessibleContext().setAccessibleDescription("Acerca de");
        jMenuItem.addActionListener(listener);
        jMenuItem.setActionCommand("about");
        jMenu.add(jMenuItem);
        jMenuBar.add(jMenu);
        add(jMenuBar);

    }

    private void adjustGUI() {
        setPreferredSize(new Dimension(270, 150));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setComponentBounds() {
        //set component bounds (only needed by Absolute Positioning)
        jBVerifyNumber.setBounds(75, 80, 100, 20);
        jWelcome.setBounds(40, 25, 195, 25);
        jInput.setBounds(50, 50, 155, 25);
        jScoreList.setBounds(30, 110, 195, 185);
        jMenuBar.setBounds(0, 0, 270, 20);
    }

    private void addGUIComponents() {
        //add components
        add(jBVerifyNumber);
        add(jWelcome);
        add(jInput);
        add(jScoreList);
        //Set frame name
        setTitle("NumberGame");
    }

    public void sendToController() {
        //when JButton is pressed used actionPerformed method return the number
        jBVerifyNumber.setActionCommand("Verify");
        jBVerifyNumber.addActionListener(listener);
    }

    private void setOnlyKeys(JTextField jInput) {
        //make only can type numbers and backspace, if text is bigger than 10, it will be deleted
        jInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
                if (jInput.getText().length() > 6) {
                    e.consume();
                }
                //if the user press enter and theres text, the actionPerformed method will be called
                if (c == KeyEvent.VK_ENTER && jInput.getText().length() > 0) {
                    jBVerifyNumber.doClick();
                }
            }
        });
    }

    public int getNumber() {
        return Integer.parseInt(jInput.getText());
    }

    public void resetInput() {
        jInput.setText("");
    }

    public void showMessage(String data) {
        JOptionPane.showMessageDialog(null, data);
    }

    public void setTimeoutAlert() {
        //alert user of continuos usage of the app for five minutes or he'll be kicked out
        JOptionPane.showMessageDialog(null, "El tiempo de inactividad maximo es de 5 minutos");
    }

    public void showDisconnection() {
        //alert user of disconnection
        JOptionPane.showMessageDialog(null, "Se ha desconectado del servidor");
    }

    public boolean showReconnection() {
        //alert user if he wants to reconnect
        int option = JOptionPane.showConfirmDialog(null, "El servidor se ha desconectado, desea reconectar?", "Reconectar", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            return true;
        }
        return false;
    }
}
