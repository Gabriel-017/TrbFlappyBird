package model;

import javax.swing.JFrame;

public class Flappy extends JFrame {

    public Flappy() {

       add(new FlappyPanel());

        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
