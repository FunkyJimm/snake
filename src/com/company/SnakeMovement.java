package com.company;

public class SnakeMovement {
    private boolean facingUp;
    private boolean facingDown;
    private boolean facingLeft;
    private boolean facingRight;
    private int snakeHeadLastPosX;
    private int snakeHeadLastPosY;
    private SnakeTail snakeTail;

    public SnakeMovement(SnakeTail snakeTail) {
        this.snakeTail = snakeTail;
    }

    public void snakeGoUp() {
        if(!facingDown) {
            facingUp = true;
            facingLeft = false;
            facingRight = false;

            snakeTailFollow();
            snakeTail.getSnakeTail().get(0).setBodyY(snakeHeadLastPosY - 40);
        }
    }

    public void snakeGoDown() {
        if(!facingUp) {
            facingDown = true;
            facingLeft = false;
            facingRight = false;

            snakeTailFollow();
            snakeTail.getSnakeTail().get(0).setBodyY(snakeHeadLastPosY + 40);
        }
    }

    public void snakeGoLeft() {
        if(!facingRight) {
            facingLeft = true;
            facingUp = false;
            facingDown = false;

            snakeTailFollow();
            snakeTail.getSnakeTail().get(0).setBodyX(snakeHeadLastPosX - 40);
        }
    }

    public void snakeGoRight() {
        if(!facingLeft) {
            facingRight = true;
            facingUp = false;
            facingDown = false;

            snakeTailFollow();
            snakeTail.getSnakeTail().get(0).setBodyX(snakeHeadLastPosX + 40);
        }
    }

    public void snakeGoForward() {
        if(facingUp) snakeGoUp();
        if(facingDown) snakeGoDown();
        if(facingLeft) snakeGoLeft();
        if(facingRight) snakeGoRight();
    }

    public boolean isFacingUp() {
        return facingUp;
    }

    public boolean isFacingDown() {
        return facingDown;
    }

    public boolean isFacingLeft() {
        return facingLeft;
    }

    public boolean isFacingRight() {
        return facingRight;
    }

    public int getSnakeLastHeadPosX(int i) {
        return snakeTail.getSnakeTail().get(i).getBodyX();
    }

    public int getSnakeLastHeadPosY(int i) {
        return snakeTail.getSnakeTail().get(i).getBodyY();
    }

    public void snakeTailFollow() {
        for(int i = snakeTail.getSnakeTail().size() - 1; i > 0; i--) {
            snakeHeadLastPosX = getSnakeLastHeadPosX(i - 1);
            snakeHeadLastPosY = getSnakeLastHeadPosY(i - 1);
            snakeTail.getSnakeTail().get(i).setBodyX(snakeHeadLastPosX);
            snakeTail.getSnakeTail().get(i).setBodyY(snakeHeadLastPosY);
        }
    }
}
