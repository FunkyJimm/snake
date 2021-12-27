package com.company;

import java.awt.*;

public class Colors {
    private Color backgroundColor;
    private Color snakeHeadColor;
    private Color snakeTailColor;
    private Color snakeBorderColor;
    private Color fruitColor;
    private Color fruitBorderColor;

    public Colors(Color backgroundColor, Color snakeHeadColor, Color snakeTailColor, Color fruitColor) {
        this.backgroundColor = backgroundColor;
        this.snakeHeadColor = snakeHeadColor;
        this.snakeTailColor = snakeTailColor;
        this.fruitColor = fruitColor;
    }

    public Colors(Color backgroundColor, Color snakeHeadColor, Color snakeTailColor, Color snakeBorderColor, Color fruitColor, Color fruitBorderColor) {
        this.backgroundColor = backgroundColor;
        this.snakeHeadColor = snakeHeadColor;
        this.snakeTailColor = snakeTailColor;
        this.snakeBorderColor = snakeBorderColor;
        this.fruitColor = fruitColor;
        this.fruitBorderColor = fruitBorderColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getSnakeHeadColor() {
        return snakeHeadColor;
    }

    public Color getSnakeTailColor() {
        return snakeTailColor;
    }

    public Color getSnakeBorderColor() {
        return snakeBorderColor;
    }

    public Color getFruitColor() {
        return fruitColor;
    }

    public Color getFruitBorderColor() {
        return fruitBorderColor;
    }
}
