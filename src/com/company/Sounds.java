package com.company;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Sounds {
    InputStream sound;
    AudioStream audio;

    public Sounds(String filePath) {
        try {
            sound = new FileInputStream(new File(filePath));
            audio = new AudioStream(sound);
            AudioPlayer.player.start(audio);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Error, system couldn't read audio file!");
        }
    }
}
