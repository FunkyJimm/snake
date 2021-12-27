package com.company;

import javax.swing.*;
import javax.swing.border.StrokeBorder;
import java.awt.*;
import java.util.List;

public class Rendering extends JPanel {
    private SnakeTail snakeTail;
    private List<Fruit> fruits;
    private Colors colors;
    private Graphics2D g2d;

    public Rendering(SnakeTail snakeTail, List<Fruit> fruits, Colors colors) {
        this.snakeTail = snakeTail;
        this.fruits = fruits;
        this.colors = colors;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        BasicStroke borderSize = new BasicStroke(2);

        setBackground(colors.getBackgroundColor());

        for(int i = 0; i < snakeTail.getSnakeTail().size(); i++) {
            if(i == 0) g2d.setColor(colors.getSnakeHeadColor());
            else g2d.setColor(colors.getSnakeTailColor());
            g2d.fillRect(snakeTail.getSnakeTail().get(i).getBodyX(), snakeTail.getSnakeTail().get(i).getBodyY(), 40, 40);

            if(colors.getSnakeBorderColor() != null) {
                g2d.setStroke(borderSize);
                g2d.setColor(colors.getSnakeBorderColor());
                g2d.drawRect(snakeTail.getSnakeTail().get(i).getBodyX(), snakeTail.getSnakeTail().get(i).getBodyY(), 40, 40);
            }
        }

        for(int i = 0; i < fruits.size(); i++) {
            g2d.setColor(colors.getFruitColor());
            g2d.fillRect(fruits.get(i).getFruitX(), fruits.get(i).getFruitY(), 40, 40);

            if(colors.getSnakeBorderColor() != null) {
                g2d.setStroke(borderSize);
                g2d.setColor(colors.getFruitBorderColor());
                g2d.drawRect(fruits.get(i).getFruitX(), fruits.get(i).getFruitY(), 40, 40);
            }
        }
    }
}
