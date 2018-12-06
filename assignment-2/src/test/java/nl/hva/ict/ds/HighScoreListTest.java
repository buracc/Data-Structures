package nl.hva.ict.ds;

import com.github.javafaker.Faker;
import nl.hva.ict.ds.interfaces.HighScoreList;
import nl.hva.ict.ds.objects.Player;
import nl.hva.ict.ds.sorts.BucketSortHighScores;
import nl.hva.ict.ds.sorts.DummyHighScores;
import nl.hva.ict.ds.sorts.InsertionSortHighScores;
import nl.hva.ict.ds.sorts.PriorityQueueHighScores;
import org.junit.Before;
import org.junit.Test;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class contains some unit tests. They by no means ensure that all the requirements are implemented
 * correctly.
 */
public class HighScoreListTest {
    private static final int MAX_HIGH_SCORE = 100000;
    private Random randomizer = new SecureRandom();
    private HighScoreList highScores;
    private Player nearlyHeadlessNick;
    private Player dumbledore;


    @Before
    public void setup() {
//         Here you should select your implementation to be tested.
//        highScores = new DummyHighScores();
//        highScores = new InsertionSortHighScores();
        highScores = new BucketSortHighScores();
//        highScores = new PriorityQueueHighScores();

        nearlyHeadlessNick = new Player("Nicholas", "de Mimsy-Porpington", getHighScore() % 200);
        dumbledore = new Player("Albus", "Dumbledore", nearlyHeadlessNick.getHighScore() * 1000);
        //dumbledore's score can de MAX_HIGH_SCORE overschrijden
    }

    public static void generatePlayers(int amount, HighScoreList highScores) {
        Faker faker = new Faker();
        for (int i = 0; i < amount; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            Random rand = new Random();
            long score = rand.nextInt(MAX_HIGH_SCORE);


            Player p = new Player(firstName, lastName, score);
            highScores.add(p);
        }
    }

    @Test
    public void noPlayerNoHighScore() {
        assertTrue("There are high-score while there should be no high-scores!", highScores.getHighScores(1).isEmpty());
    }

    @Test
    public void whenNoHighScoreIsAskedForNonShouldBeGiven() {
        highScores.add(dumbledore);

        assertEquals(0, highScores.getHighScores(0).size());
    }

    @Test
    public void noMoreHighScoresCanBeGivenThenPresent() {
        highScores.add(nearlyHeadlessNick);
        highScores.add(dumbledore);

        assertEquals(2, highScores.getHighScores(10).size());
    }

    @Test
    public void keepAllHighScores() {
        highScores.add(nearlyHeadlessNick);
        highScores.add(dumbledore);

        assertEquals(2, highScores.getHighScores(2).size());
    }

    @Test
    public void singlePlayerHasHighScore() {
        highScores.add(dumbledore);

        assertEquals(dumbledore, highScores.getHighScores(1).get(0));
    }

    @Test
    public void harryBeatsDumbledore() {
        highScores.add(dumbledore);
        Player harry = new Player("Harry", "Potter", dumbledore.getHighScore() + 1);

        highScores.add(harry);// Ben je add(harry) vergeten?

        assertEquals(harry, highScores.getHighScores(1).get(0));
    }

    // Extra unit tests go here

    private long getHighScore() {
        return randomizer.nextInt(MAX_HIGH_SCORE);
    }




    /* Extra unit tests */

    /**
     * Test de grootte van de lijst
     */
    @Test
    public void checkSize() {
        int maxSize = 20000;
        generatePlayers(maxSize, highScores);

        assertEquals(maxSize, highScores.getHighScores(maxSize).size());
    }

    /**
     * Test het zoeken op voornaam en achternaam
     */
    @Test
    public void getByName() {
        highScores.add(dumbledore);
        assertEquals(dumbledore, highScores.findPlayer("albus", "dumbledore").get(0));
    }

    /**
     * Test het zoeken op voornaam
     */
    @Test
    public void getByFirstName() {
        highScores.add(dumbledore);
        assertEquals(dumbledore, highScores.findPlayer("albus", "").get(0));
    }

    /**
     * Test het zoeken van meerdere op voornaam
     */
    @Test
    public void getMultipleByFirstName() {
        highScores.add(dumbledore);
        highScores.add(new Player("Albus", "Potter", 1000));
        assertEquals(2, highScores.findPlayer("albus", "").size());
    }

    /**
     * Test het zoeken op achternaam
     */
    @Test
    public void getByLastName() {
        highScores.add(dumbledore);
        assertEquals(dumbledore, highScores.findPlayer("", "dumbledore").get(0));
    }

    /**
     * Test de volgorde door te kijken of de hoogste score op de eerste plaats staat
     */
    @Test
    public void checkHighestScore() {
        int maxSize = 20000;
        generatePlayers(maxSize, highScores);
        long highestScore = Integer.MIN_VALUE;

        for (Player p : highScores.getHighScores(maxSize)) {
            if (p.getHighScore() >= highestScore) {
                highestScore = p.getHighScore();
            }
        }

        assertEquals(highestScore, highScores.getHighScores(maxSize).get(0).getHighScore());
    }

    /**
     * Test de volgorde door te kijken of de laagste score op de laatste plaats staat
     */
    @Test
    public void checkLowestScore() {
        int maxSize = 20000;
        generatePlayers(maxSize, highScores);
        long lowestScore = Integer.MAX_VALUE;

        for (Player p : highScores.getHighScores(maxSize)) {
            if (p.getHighScore() <= lowestScore) {
                lowestScore = p.getHighScore();
            }
        }

        assertEquals(lowestScore, highScores.getHighScores(maxSize).get(maxSize-1).getHighScore());
    }

}