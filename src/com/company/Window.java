package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Window extends JFrame implements KeyListener {
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    private final int WINDOW_POS_X = dimension.width / 2;
    private final int WINDOW_POS_Y = dimension.height / 2;
    private JFrame window;
    private SnakeMovement snakeMovement;

    private boolean gameIsRunning;
    private boolean firstMove;

    public Window(int width, int height, String title) {
        window = new JFrame();
        window.setBounds(WINDOW_POS_X - width / 2, WINDOW_POS_Y - height / 2, width, height);
        window.setTitle(title);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setResizable(false);

        window.setVisible(true);
        window.setFocusable(true);
        window.addKeyListener(this);
    }

    public JFrame getWindow() {
        return window;
    }

    public void setSnakeMovement(SnakeMovement snakeMovement) {
        this.snakeMovement = snakeMovement;
    }

    public void setWindowSize(int width, int height) {
        window.setBounds(WINDOW_POS_X - width / 2, WINDOW_POS_Y - height / 2, width, height);
    }

    public void setGameIsRunning(Boolean gameIsRunning) {
        this.gameIsRunning = gameIsRunning;
        this.firstMove = false;
        window.revalidate();
    }

//    public void readBackgroundImage() {
//        BufferedImage bg;
//
//        try {
//            bg = ImageIO.read(new File("res//snake.png"));
//            ImageIcon imageIcon = new ImageIcon(bg);
//            window.setContentPane(new JLabel(imageIcon));
//        } catch(IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(gameIsRunning) {
            if(firstMove) {
                if(e.getKeyChar() == 'w' || e.getKeyChar() == 'W') {
                    if(!snakeMovement.isFacingUp())
                        snakeMovement.snakeGoUp();
                }
            }
            if(e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
                if(firstMove == false) firstMove = true;
                if(!snakeMovement.isFacingDown())
                    snakeMovement.snakeGoDown();
            }
            if(e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
                if(firstMove == false) firstMove = true;
                if(!snakeMovement.isFacingLeft())
                    snakeMovement.snakeGoLeft();
            }
            if(e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
                if(firstMove == false) firstMove = true;
                if(!snakeMovement.isFacingRight())
                    snakeMovement.snakeGoRight();
            }
        }
        if(e.getKeyCode() == 27) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
