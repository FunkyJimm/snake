package com.company;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Ranking {
    List ranking = new ArrayList();

    public Ranking() {

    }

    public void createRanking() {
        try {
            File ranking = new File("ranking.txt");
            if(ranking.createNewFile()) {
            }
            else {
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error, system couldn't read ranking file!");
            e.printStackTrace();
        }
    }

    public void saveRanking(String string) {
        try {
            FileWriter fileWriter = new FileWriter("ranking.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(string);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error, system couldn't save ranking file!");
            e.printStackTrace();
        }
    }

    public void readRanking() {
        ranking.clear();
        try {
             File file = new File("ranking.txt");
             Scanner scanner = new Scanner(file);
             while(scanner.hasNextLine()) {
                 ranking.add(scanner.nextLine());
             }
             scanner.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error, system couldn't read ranking file!");
            e.printStackTrace();
        }
    }

    public int[] sortRanking() {
        int[] scoreRanking = new int[ranking.size()];
        String tempScore = "";
        for(int i = 0; i < ranking.size(); i++) {
            tempScore = ranking.get(i).toString();
            scoreRanking[i] = Integer.parseInt(tempScore);
        }
        Arrays.sort(scoreRanking);
        return scoreRanking;
    }
}
