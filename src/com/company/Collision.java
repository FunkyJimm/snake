package com.company;

import java.util.ArrayList;
import java.util.List;

public class Collision {
    private int windowWidth;
    private int windowHeight;
    private int snakeHeadLastPosX;
    private int snakeHeadLastPosY;
    private SnakeTail snakeTail;
    private SnakeMovement snakeMovement;
    private List<Fruit> fruits;

    public Collision(int windowWidth, int windowHeight, SnakeTail snakeTail, SnakeMovement snakeMovement, List<Fruit> fruits) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight - 20;
        this.snakeTail = snakeTail;
        this.snakeMovement = snakeMovement;
        this.fruits = fruits;
    }

    public boolean checkHeadCollision() {
        snakeHeadLastPosX = snakeMovement.getSnakeLastHeadPosX(0);
        snakeHeadLastPosY = snakeMovement.getSnakeLastHeadPosY(0);

        for(int i = 1; i < snakeTail.getSnakeTail().size(); i++) {
            if(snakeHeadLastPosX == snakeTail.getSnakeTail().get(i).getBodyX() &&
                    snakeHeadLastPosY == snakeTail.getSnakeTail().get(i).getBodyY()) {
                return true;
            }
        }
        return false;
    }

    public void borderTeleport() {
        SnakeBody snakeHead = snakeTail.snakeTail.get(0);
        snakeHeadLastPosX = snakeMovement.getSnakeLastHeadPosX(0);
        snakeHeadLastPosY = snakeMovement.getSnakeLastHeadPosY(0);

        if(snakeHeadLastPosX < 0) {
            snakeHead.setBodyX(windowWidth - 40);
        }
        if(snakeHeadLastPosX >= windowWidth) {
            snakeHead.setBodyX(0);
        }
        if(snakeHeadLastPosY < 0) {
            snakeHead.setBodyY(windowHeight - 40);
        }
        if(snakeHeadLastPosY >= windowHeight) {
            snakeHead.setBodyY(0);
        }
    }

    public boolean borderCollision() {
        SnakeBody snakeHead = snakeTail.snakeTail.get(0);
        snakeHeadLastPosX = snakeMovement.getSnakeLastHeadPosX(0);
        snakeHeadLastPosY = snakeMovement.getSnakeLastHeadPosY(0);

        if(snakeHeadLastPosX < 0) {
            return true;
        }
        if(snakeHeadLastPosX >= windowWidth) {
            return true;
        }
        if(snakeHeadLastPosY < 0) {
            return true;
        }
        if(snakeHeadLastPosY >= windowHeight) {
            return true;
        }
        return false;
    }

    public int fruitCollision() {
        for(int i = 0; i < fruits.size(); i++) {
            if((snakeMovement.getSnakeLastHeadPosX(0) == fruits.get(i).getFruitX() &&
                    snakeMovement.getSnakeLastHeadPosY(0) == fruits.get(i).getFruitY()) ||
                    (snakeMovement.getSnakeLastHeadPosX(1) == fruits.get(i).getFruitX() &&
                            snakeMovement.getSnakeLastHeadPosY(1) == fruits.get(i).getFruitY())) {
                fruits.remove(i);
                return 1;
            }
        }
        return 0;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }
}
