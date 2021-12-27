package com.company;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Math.abs;

public class Menu implements ActionListener, ChangeListener {
    private GameOptions gameOptions;
    private Window window;
    private Ranking ranking;
    private Game game;
    private Colors colors;
    private Colors tempColors;
    private GameStyles gameStyles;

    private int windowWidth;
    private int windowHeight;
    private int windowType;
    private int gameSpeed;
    private int numberOfFruits;
    private boolean borderCollision;
    private boolean sound;

    private JLabel titleLabel;
    private JButton newGameButton;
    private JButton rankingButton;
    private JButton optionsButton;
    private JButton exitButton;
    private JLabel footerLabel;

    private JLabel rankingLabel;
    private JTextArea rankingContainer;
    private JButton rankingReturnButton;

    private JLabel optionsLabel;
    private JLabel windowSizeLabel;
    private ButtonGroup windowSizeSwitcher;
    private JRadioButton windowSizeS, windowSizeM, windowSizeL;
    private JLabel graphicsLabel;
    private JComboBox graphicsComboBox;

    private JLabel gameSpeedLabel;
    private JSlider gameSpeedSlider;
    private JLabel numberOfFruitsLabel;
    private JSlider numberOfFruitsSlider;
    private JLabel collisionLabel;
    private ButtonGroup collisionSwitcher;
    private JRadioButton collisionRadioButtonOFF, collisionRadioButtonON;
    private JLabel soundLabel;
    private ButtonGroup soundSwitcher;
    private JRadioButton soundRadioButtonOFF, soundRadioButtonON;
    private JButton optionsConfirmButton;
    private JButton optionsReturnButton;

    private JLabel scoreLabel;
    private JButton restartButton;
    private JButton returnButton;

    private JLayeredPane mainMenu;
    private JLayeredPane rankingMenu;
    private JLayeredPane optionsMenu;
    private JLayeredPane gameMenu;

    public Menu() {
        this.gameOptions = new GameOptions();

        this.windowWidth = (Integer) gameOptions.getGameOptions().get("windowWidth");
        this.windowHeight = (Integer) gameOptions.getGameOptions().get("windowHeight");
        this.gameSpeed = (Integer) gameOptions.getGameOptions().get("gameSpeed");
        this.numberOfFruits = (Integer) gameOptions.getGameOptions().get("numberOfFruits");
        this.sound = (Boolean) gameOptions.getGameOptions().get("sound");

        this.window = new Window(windowWidth, windowHeight, "Snake");
        this.ranking = new Ranking();

        this.gameStyles = new GameStyles();
        this.colors = gameStyles.getGameStyles().get("black");

        drawGUI();
    }

    public void drawGUI() {
        // Main menu
        {
            titleLabel = new JLabel("Snake", SwingConstants.CENTER);
            titleLabel.setBounds((windowWidth / 2) - 100, (windowHeight / 2) - 200, 200, 50);
            titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 48));

            newGameButton = new JButton("New Game");
            newGameButton.setBounds((windowWidth / 2) - 100, (windowHeight / 2) - 100, 200, 50);
            newGameButton.addActionListener(this);

            rankingButton = new JButton("Rankings");
            rankingButton.setBounds((windowWidth / 2) - 100, (windowHeight / 2) - 50, 200, 50);
            rankingButton.addActionListener(this);

            optionsButton = new JButton("Options");
            optionsButton.setBounds((windowWidth / 2) - 100, (windowHeight / 2), 200, 50);
            optionsButton.addActionListener(this);

            exitButton = new JButton("Exit");
            exitButton.setBounds((windowWidth / 2) - 100, (windowHeight / 2) + 50, 200, 50);
            exitButton.addActionListener(this);

            footerLabel = new JLabel("FunkyJimm - 2020", SwingConstants.CENTER);
            footerLabel.setBounds((windowWidth / 2) - 100, windowHeight - 100, 200, 50);
            footerLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

            mainMenu = new JLayeredPane();
            mainMenu.add(titleLabel);
            mainMenu.add(newGameButton);
            mainMenu.add(rankingButton);
            mainMenu.add(optionsButton);
            mainMenu.add(exitButton);
            mainMenu.add(footerLabel);
        }

        // Ranking menu
        {
            rankingLabel = new JLabel("Best Scores", SwingConstants.CENTER);
            rankingLabel.setBounds((windowWidth / 2) - 100, (windowHeight / 2) - 200, 200, 50);
            rankingLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

            rankingContainer = new JTextArea();
            rankingContainer.setBounds((windowWidth / 2) - 50, (windowHeight / 2) - 140, 100, 210);
            rankingContainer.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            rankingContainer.setEditable(false);

            rankingReturnButton = new JButton("Return");
            rankingReturnButton.setBounds((windowWidth / 2) - 100, (windowHeight / 2) + 100, 200, 50);
            rankingReturnButton.addActionListener(this);

            rankingMenu = new JLayeredPane();
            rankingMenu.add(rankingLabel);
            rankingMenu.add(rankingContainer);
            rankingMenu.add(rankingReturnButton);
        }

        // Options menu
        {
            optionsLabel = new JLabel("Options", SwingConstants.CENTER);
            optionsLabel.setBounds((windowWidth / 2) - 100, (windowHeight / 2) - 300, 200, 50);
            optionsLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

            // Window size layer
            windowSizeLabel = new JLabel("Window size:", SwingConstants.CENTER);
            windowSizeLabel.setBounds((windowWidth / 2) - 100, (windowHeight / 2) - 250, 200, 50);
            windowSizeLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

            windowSizeSwitcher = new ButtonGroup();
            windowSizeS = new JRadioButton("Classic", false);
            windowSizeS.setBounds((windowWidth / 2) - 100, (windowHeight / 2) - 200, 75, 20);
            windowSizeS.setOpaque(false);
            windowSizeS.addActionListener(this);
            windowSizeM = new JRadioButton("Normal", false);
            windowSizeM.setBounds((windowWidth / 2) - 25, (windowHeight / 2) - 200, 75, 20);
            windowSizeM.setOpaque(false);
            windowSizeM.addActionListener(this);
            windowSizeL = new JRadioButton("Wide", false);
            windowSizeL.setBounds((windowWidth / 2) + 50, (windowHeight / 2) - 200, 75, 20);
            windowSizeL.setOpaque(false);
            windowSizeL.addActionListener(this);
            windowSizeSwitcher.add(windowSizeS);
            windowSizeSwitcher.add(windowSizeM);
            windowSizeSwitcher.add(windowSizeL);

            // Graphics layer
            graphicsLabel = new JLabel("Graphics style:", SwingConstants.CENTER);
            graphicsLabel.setBounds((windowWidth / 2) - 125, (windowHeight / 2) - 175, 150, 50);
            graphicsLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

            graphicsComboBox = new JComboBox();
            graphicsComboBox.setBounds((windowWidth / 2) + 10, (windowHeight / 2) - 158, 100, 20);
            graphicsComboBox.addItem("Black");
            graphicsComboBox.addItem("White");
            graphicsComboBox.addItem("Classic");
            graphicsComboBox.addItem("Funky");
            graphicsComboBox.addItem("Cyber");
            graphicsComboBox.addActionListener(this);

            // Game speed layer
            gameSpeedLabel = new JLabel("Speed delay: " + gameSpeed, SwingConstants.CENTER);
            gameSpeedLabel.setBounds((windowWidth / 2) - 100, (windowHeight / 2) - 125, 200, 50);
            gameSpeedLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

            gameSpeedSlider = new JSlider(30, 230, gameSpeed);
            gameSpeedSlider.setBounds((windowWidth / 2) - 100, (windowHeight / 2) - 75, 200, 50);
            gameSpeedSlider.setMajorTickSpacing(50);
            gameSpeedSlider.setMinorTickSpacing(25);
            gameSpeedSlider.setPaintTicks(true);
            gameSpeedSlider.setPaintLabels(true);
            gameSpeedSlider.setOpaque(false);
            gameSpeedSlider.addChangeListener(this);

            // Number of fruits layer
            numberOfFruitsLabel = new JLabel("Number of fruits: " + numberOfFruits, SwingConstants.CENTER);
            numberOfFruitsLabel.setBounds((windowWidth / 2) - 100, (windowHeight / 2) - 25, 200, 50);
            numberOfFruitsLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

            numberOfFruitsSlider = new JSlider(1, 5, numberOfFruits);
            numberOfFruitsSlider.setBounds((windowWidth / 2) - 100, (windowHeight / 2) + 25, 200, 50);
            numberOfFruitsSlider.setMajorTickSpacing(2);
            numberOfFruitsSlider.setMinorTickSpacing(1);
            numberOfFruitsSlider.setPaintTicks(true);
            numberOfFruitsSlider.setPaintLabels(true);
            numberOfFruitsSlider.setOpaque(false);
            numberOfFruitsSlider.addChangeListener(this);

            // Collision layer
            collisionLabel = new JLabel("Collision:", SwingConstants.CENTER);
            collisionLabel.setBounds((windowWidth / 2) - 150, (windowHeight / 2) + 65, 200, 50);
            collisionLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

            collisionSwitcher = new ButtonGroup();
            collisionRadioButtonOFF = new JRadioButton("OFF");
            collisionRadioButtonOFF.setBounds((windowWidth / 2) - 10, (windowHeight / 2) + 82, 50, 20);
            collisionRadioButtonOFF.setOpaque(false);
            collisionRadioButtonOFF.addActionListener(this);
            collisionRadioButtonON = new JRadioButton("ON");
            collisionRadioButtonON.setBounds((windowWidth / 2) + 40, (windowHeight / 2) + 82, 50, 20);
            collisionRadioButtonON.setOpaque(false);
            collisionRadioButtonON.addActionListener(this);

            if((Boolean) gameOptions.getGameOptions().get("borderCollision")) {
                collisionRadioButtonON.setSelected(true);
            } else {
                collisionRadioButtonOFF.setSelected(true);
            }

            collisionSwitcher.add(collisionRadioButtonOFF);
            collisionSwitcher.add(collisionRadioButtonON);

            // Sounds layer
            soundLabel = new JLabel("Sound:", SwingConstants.CENTER);
            soundLabel.setBounds((windowWidth / 2) - 142, (windowHeight / 2) + 100, 200, 50);
            soundLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

            soundSwitcher = new ButtonGroup();
            soundRadioButtonOFF = new JRadioButton("OFF");
            soundRadioButtonOFF.setBounds((windowWidth / 2) - 10, (windowHeight / 2) + 117, 50, 20);
            soundRadioButtonOFF.setOpaque(false);
            soundRadioButtonOFF.addActionListener(this);
            soundRadioButtonON = new JRadioButton("ON");
            soundRadioButtonON.setBounds((windowWidth / 2) + 40, (windowHeight / 2) + 117, 50, 20);
            soundRadioButtonON.setOpaque(false);
            soundRadioButtonON.addActionListener(this);

            if((Boolean) gameOptions.getGameOptions().get("sound")) {
                soundRadioButtonON.setSelected(true);
            } else {
                soundRadioButtonOFF.setSelected(true);
            }

            soundSwitcher.add(soundRadioButtonOFF);
            soundSwitcher.add(soundRadioButtonON);

            // Buttons layers
            optionsConfirmButton = new JButton("Save");
            optionsConfirmButton.setBounds((windowWidth / 2) - 100, (windowHeight / 2) + 150, 200, 50);
            optionsConfirmButton.addActionListener(this);

            optionsReturnButton = new JButton("Return");
            optionsReturnButton.setBounds((windowWidth / 2) - 100, (windowHeight / 2) + 200, 200, 50);
            optionsReturnButton.addActionListener(this);

            optionsMenu = new JLayeredPane();
            optionsMenu.add(optionsLabel);
            optionsMenu.add(windowSizeLabel);
            optionsMenu.add(windowSizeS);
            optionsMenu.add(windowSizeM);
            optionsMenu.add(windowSizeL);
            optionsMenu.add(graphicsLabel);
            optionsMenu.add(graphicsComboBox);
            optionsMenu.add(gameSpeedLabel);
            optionsMenu.add(gameSpeedSlider);
            optionsMenu.add(numberOfFruitsLabel);
            optionsMenu.add(numberOfFruitsSlider);
            optionsMenu.add(collisionLabel);
            optionsMenu.add(collisionRadioButtonOFF);
            optionsMenu.add(collisionRadioButtonON);
            optionsMenu.add(soundLabel);
            optionsMenu.add(soundRadioButtonOFF);
            optionsMenu.add(soundRadioButtonON);

            optionsMenu.add(optionsConfirmButton);
            optionsMenu.add(optionsReturnButton);
        }

        // In game menu
        {
            scoreLabel = new JLabel("", SwingConstants.CENTER);
            scoreLabel.setBounds((windowWidth / 2) - 200, (windowHeight / 2) - 125, 400, 50);
            scoreLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

            restartButton = new JButton("Restart");
            restartButton.setBounds((windowWidth / 2) - 100, (windowHeight / 2) - 50, 200, 50);
            restartButton.addActionListener(this);

            returnButton = new JButton("Return");
            returnButton.setBounds((windowWidth / 2) - 100, (windowHeight / 2), 200, 50);
            returnButton.addActionListener(this);

            gameMenu = new JLayeredPane();
            gameMenu.add(scoreLabel);
            gameMenu.add(restartButton);
            gameMenu.add(returnButton);
        }
    }

    public void redrawGUI() {
        window.setWindowSize(windowWidth, windowHeight);
        drawGUI();
        optionsMenu();
    }

    public void newGame() {
        SnakeTail snakeTail = new SnakeTail();
        SnakeMovement snakeMovement = new SnakeMovement(snakeTail);
        window.setSnakeMovement(snakeMovement);
        game = new Game(snakeTail, snakeMovement, window, gameOptions, colors);
        new Thread() {
            public void run() {
                if(game.startNewGame()) {
                    window.setGameIsRunning(false);
                    gameMenu();
                }
            }
        }.start();
    }

    public void mainMenu() {
        window.getWindow().getContentPane().removeAll();
        window.getWindow().add(mainMenu);
        window.getWindow().revalidate();
        window.getWindow().repaint();
    }

    public void rankingMenu() {
        int order = 0;

        ranking.readRanking();

        int[] scores = ranking.sortRanking();

        for(int i = scores.length - 1; i >= scores.length - 10 ; i--) {
            order++;
            rankingContainer.append(order + ". " + scores[i] + "\n");
        }

        window.getWindow().getContentPane().removeAll();
        window.getWindow().add(rankingMenu);
        window.getWindow().revalidate();
        window.getWindow().repaint();
    }

    public void optionsMenu() {
        window.getWindow().getContentPane().removeAll();
        window.getWindow().add(optionsMenu);
        window.getWindow().revalidate();
        window.getWindow().repaint();
    }

    public void gameMenu() {
        int gameScore = game.getGameScore();
        window.getWindow().getContentPane().removeAll();
        scoreLabel.setText("Your score: " + gameScore);
        window.getWindow().add(gameMenu);
        window.getWindow().repaint();

        if(gameScore > 0) {
            ranking.createRanking();
            ranking.saveRanking(String.valueOf(gameScore));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == newGameButton) {
            window.getWindow().getContentPane().removeAll();
            window.getWindow().repaint();

            newGame();
        }

        if(e.getSource() == rankingButton) {
            rankingMenu();

            //JOptionPane.showMessageDialog(window, "This option is not supported yet!");
        }

        if(e.getSource() == optionsButton) {
            optionsMenu();
        }

        if(e.getSource() == exitButton) {
            System.exit(0);
        }

        if(e.getSource() == windowSizeS) {
            windowType = 1;
        }

        if(e.getSource() == windowSizeM) {
            windowType = 2;
        }

        if(e.getSource() == windowSizeL) {
            windowType = 3;
        }

        if(graphicsComboBox.getSelectedItem() == "Black") {
            tempColors = gameStyles.getGameStyles().get("black");
        }

        if(graphicsComboBox.getSelectedItem() == "White") {
            tempColors = gameStyles.getGameStyles().get("white");
        }

        if(graphicsComboBox.getSelectedItem() == "Classic") {
            tempColors = gameStyles.getGameStyles().get("classic");
        }

        if(graphicsComboBox.getSelectedItem() == "Funky") {
            tempColors = gameStyles.getGameStyles().get("funky");
        }

        if(graphicsComboBox.getSelectedItem() == "Cyber") {
            tempColors = gameStyles.getGameStyles().get("cyber");
        }

        if(e.getSource() == collisionRadioButtonOFF) {
            borderCollision = false;
        }

        if(e.getSource() == collisionRadioButtonON) {
            borderCollision = true;
        }

        if(e.getSource() == soundRadioButtonOFF) {
            sound = false;
        }

        if(e.getSource() == soundRadioButtonON) {
            sound = true;
        }

        if(e.getSource() == optionsConfirmButton) {
            switch(windowType) {
                case 1:
                    windowWidth = 600;
                    windowHeight = 620;
                    redrawGUI();
                    break;
                case 2:
                    windowWidth = 1000;
                    windowHeight = 780;
                    redrawGUI();
                    break;
                case 3:
                    windowWidth = 1560;
                    windowHeight = 940;
                    redrawGUI();
                    break;
            }

            gameOptions.getGameOptions().replace("windowWidth", windowWidth);
            gameOptions.getGameOptions().replace("windowHeight", windowHeight);
            gameOptions.getGameOptions().replace("gameSpeed", abs(gameSpeed));
            gameOptions.getGameOptions().replace("numberOfFruits", numberOfFruits);
            gameOptions.getGameOptions().replace("borderCollision", borderCollision);
            gameOptions.getGameOptions().replace("sound", sound);
            colors = tempColors;
        }

        if(e.getSource() == restartButton) {
            window.getWindow().getContentPane().removeAll();
            window.getWindow().repaint();

            newGame();
        }

        if(e.getSource() == rankingReturnButton ||
                e.getSource() == optionsReturnButton ||
                e.getSource() == returnButton) {
            window.getWindow().getContentPane().removeAll();
            window.getWindow().repaint();

            mainMenu();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        gameSpeed = gameSpeedSlider.getValue();
        gameSpeedLabel.setText("Game speed: " + gameSpeed);

        numberOfFruits = numberOfFruitsSlider.getValue();
        numberOfFruitsLabel.setText("Number of fruits: " + numberOfFruits);
    }
}
