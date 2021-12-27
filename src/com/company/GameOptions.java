package com.company;

import java.util.HashMap;

public class GameOptions {
    private HashMap<String, Object> gameOptions = new HashMap<>();

    public GameOptions() {
        this.gameOptions.put("windowWidth", 600);
        this.gameOptions.put("windowHeight", 620);
        this.gameOptions.put("gameSpeed", 70);
        this.gameOptions.put("numberOfFruits", 1);
        this.gameOptions.put("borderCollision", false);
        this.gameOptions.put("sound", true);
    }

    public GameOptions(int windowWidth, int windowHeight, int gameSpeed, int numberOfFruits, boolean borderCollision, boolean sound) {
        this.gameOptions.put("windowWidth", windowWidth);
        this.gameOptions.put("windowHeight", windowHeight);
        this.gameOptions.put("gameSpeed", gameSpeed);
        this.gameOptions.put("numberOfFruits", numberOfFruits);
        this.gameOptions.put("borderCollision", borderCollision);
        this.gameOptions.put("sound", sound);
    }



    public HashMap<String, Object> getGameOptions() {
        return gameOptions;
    }

    public void setGameOptions(HashMap<String, Object> gameOptions) {
        this.gameOptions = gameOptions;
    }
}
