package com.company;

import java.awt.*;
import java.util.HashMap;

public class GameStyles {
    private HashMap<String, Colors> gameStyles = new HashMap<>();

    public GameStyles() {
        Colors black = new Colors(new Color(0, 0, 0), new Color(215, 215, 215),
                new Color(255, 255, 255), new Color(0, 255, 0));
        Colors white = new Colors(new Color(255, 255, 255), new Color(40, 40, 40),
                new Color(0, 0, 0), new Color(255, 0, 0));
        Colors classic = new Colors(new Color(150, 200, 150), new Color(80, 80, 80),
                new Color(40, 40, 40), new Color(150, 200, 150), new Color(40, 40, 40),
                new Color(150, 200, 150));
        Colors funky = new Colors(new Color(100, 80, 130), new Color(250, 210, 140),
                new Color(255, 190, 60), new Color(250, 80, 155), new Color(185, 215, 215),
                new Color(255, 255, 255));
        Colors cyber = new Colors(new Color(10, 30, 55), new Color(60, 220, 230),
                new Color(35, 130, 195), new Color(60, 220, 230), new Color(255, 210, 220),
                new Color(240, 55, 90));

        gameStyles.put("black", black);
        gameStyles.put("white", white);
        gameStyles.put("classic", classic);
        gameStyles.put("funky", funky);
        gameStyles.put("cyber", cyber);
    }

    public HashMap<String, Colors> getGameStyles() {
        return gameStyles;
    }
}
