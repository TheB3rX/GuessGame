package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SplashApp extends JFrame {
    private JProgressBar progressBar;
    private JLabel label;
    public SplashApp() {
        createSplash();
        addProgressBar();
        addText();
        runningBar();
    }

    private void addText() {
        label = new JLabel("Loading... NumberApp");
        //set label to center
        label.setHorizontalAlignment(JLabel.CENTER);
        //set label font
        label.setFont(new Font("Serif", Font.PLAIN, 20));
        //set label color
        label.setForeground(Color.BLUE);
        add(label);
    }

    private void addProgressBar() {
        progressBar = new JProgressBar(0, getWidth());
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setBorderPainted(true);
        progressBar.setBackground(Color.WHITE);
        progressBar.setForeground(Color.BLUE);
        progressBar.setBounds(0, 180, 400, 20);
        add(progressBar);
    }
    public void runningBar() {
        int i = 0;
        while (i < 420) {
            progressBar.setValue(i);
            i++;
            try {
                Thread.sleep(10 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        setVisible(false);
    }

    private void createSplash() {
        getContentPane().setLayout(null);
        setUndecorated(true);
        setSize(new Dimension(400, 200));
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}

