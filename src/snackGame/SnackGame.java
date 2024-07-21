package snackGame;

import javax.swing.*;

public class SnackGame extends JFrame {
    SnackGame(){
        super("Snake Game Developed By Rishi Kumar");
        add( new Board());
        pack();

        setSize(300,300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args) {
         new SnackGame();
    }
}
