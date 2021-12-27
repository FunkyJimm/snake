package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private final int STARTING_SNAKE_SIZE = 3;
    private final String SNAKE_EAT_SOUND = "res//snake_eating.wav";
    private final String SNAKE_DEATH_SOUND = "res//snake_death.wav";

    private SnakeTail snakeTail;
    private SnakeMovement snakeMovement;
    private Window window;
    private GameOptions gameOptions;
    private Collision collision;
    private Rendering rendering;
    private List<Fruit> fruits = new ArrayList<>();
    private Sounds sounds;
    private int windowWidth;
    private int windowHeight;
    private int gameSpeed;
    private int numberOfFruits;
    private int gameScore;
    private boolean borderCollision;
    private boolean sound;
    private int possibleBoxesX;
    private int possibleBoxesY;

    public Game(SnakeTail snakeTail, SnakeMovement snakeMovement, Window window, GameOptions gameOptions, Colors colors) {
        this.snakeTail = snakeTail;
        this.snakeMovement = snakeMovement;
        this.window = window;
        this.gameOptions = gameOptions;

        this.windowWidth = (Integer) gameOptions.getGameOptions().get("windowWidth");
        this.windowHeight = (Integer) gameOptions.getGameOptions().get("windowHeight");
        this.gameSpeed = (Integer) gameOptions.getGameOptions().get("gameSpeed");
        this.numberOfFruits = (Integer) gameOptions.getGameOptions().get("numberOfFruits");
        this.borderCollision = (Boolean) gameOptions.getGameOptions().get("borderCollision");
        this.sound = (Boolean) gameOptions.getGameOptions().get("sound");

        collision = new Collision(windowWidth, windowHeight, snakeTail, snakeMovement, fruits);
        rendering = new Rendering(snakeTail, fruits, colors);

        possibleBoxesX = findBoxesPossible(windowWidth);
        possibleBoxesY = findBoxesPossible(windowHeight);

        window.getWindow().getContentPane().removeAll();
        window.getWindow().add(rendering);
        window.setGameIsRunning(true);
    }

    public boolean startNewGame() {
        int fruitsOnBoard = 0;

        window.getWindow().revalidate();

        // Creating new Snake
        for(int i = 0; i < STARTING_SNAKE_SIZE; i++) {
            snakeTail.getSnakeTail().add(new SnakeBody((collision.getWindowWidth() / 2) - 20,
                    ((collision.getWindowHeight() / 2) - 20 - (i * 40))));
        }

        while(true) {
            snakeMovement.snakeGoForward();
            window.getWindow().repaint();

            try {
                Thread.sleep(gameSpeed);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }

            if(fruitsOnBoard < numberOfFruits) {
                fruits.add(spawnFruit());
                fruitsOnBoard++;
            }

            if (collision.checkHeadCollision()) {
                if(sound)
                    sounds = new Sounds(SNAKE_DEATH_SOUND);
                return true;
            }

            if(borderCollision == false) {
                collision.borderTeleport();
            } else {
                if(collision.borderCollision()) {
                    if(sound)
                        sounds = new Sounds(SNAKE_DEATH_SOUND);
                    return true;
                }
            }

            if(collision.fruitCollision() == 1) {
                snakeTail.getSnakeTail().add(new SnakeBody(snakeMovement.getSnakeLastHeadPosX(0),
                        snakeMovement.getSnakeLastHeadPosY(0)));
                if(sound)
                    sounds = new Sounds(SNAKE_EAT_SOUND);
                fruitsOnBoard--;
                gameScore += (10 * (snakeTail.getSnakeTail().size() - STARTING_SNAKE_SIZE));
            }
        }
    }

    private Fruit spawnFruit() {
        Random random = new Random();
        int fruitRandX = 40 * random.nextInt(possibleBoxesX);
        int fruitRandY = 40 * random.nextInt(possibleBoxesY);
        return new Fruit(fruitRandX, fruitRandY);
    }

    // Function what findings how many boxes about dimensions 40x40 could be found in typed size
    public int findBoxesPossible(int size) {
        int boxesPossible= 0;
        for(int i = 0; i <= size; i += 40) {
            if((i % size) != 0) boxesPossible++;
        }
        return boxesPossible;
    }

    public int getGameScore() {
        return gameScore;
    }
}
