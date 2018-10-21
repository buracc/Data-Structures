package nl.hva.ict.ds;

import java.security.SecureRandom;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        final int MAX_HIGH_SCORE = 100000;
        Random randomizer = new SecureRandom();
        HighScoreList highScoreList = new BucketSortHighScores();
        Player nearlyHeadlessNick = new Player("Nicholas", "de Mimsy-Porpington", randomizer.nextInt(MAX_HIGH_SCORE) % 200);

        Player dumbledore = new Player("Albus", "Dumbledore", nearlyHeadlessNick.getHighScore() * 1000);

        highScoreList.add(dumbledore);
        Player harry = new Player("Harry", "Potter", dumbledore.getHighScore() + 1);
        highScoreList.add(harry);
        System.out.println(highScoreList.getHighScores(1).get(0).getFirstName());
    }
}
