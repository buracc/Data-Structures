package nl.hva.ict.ds;

import nl.hva.ict.ds.interfaces.HighScoreList;
import nl.hva.ict.ds.objects.Player;
import nl.hva.ict.ds.sorts.BucketSortHighScores;
import nl.hva.ict.ds.sorts.InsertionSortHighScores;
import nl.hva.ict.ds.sorts.PriorityQueueHighScores;
import org.junit.Before;
import org.junit.Test;

import java.security.SecureRandom;
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
        // Here you should select your implementation to be tested.
//        highScores = new DummyHighScores();
//        highScores = new InsertionSortHighScores();
        highScores = new BucketSortHighScores();
//        highScores = new PriorityQueueHighScores();

        nearlyHeadlessNick = new Player("Nicholas", "de Mimsy-Porpington", getHighScore() % 200);
        dumbledore = new Player("Albus", "Dumbledore", nearlyHeadlessNick.getHighScore() * 1000);
        //dumbledore's score can de MAX_HIGH_SCORE overschrijden, hoort dit zo?
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
        highScores.add(harry);// Nico je bent add(harry) vergeten

        assertEquals(harry, highScores.getHighScores(1).get(0));
    }

    // Extra unit tests go here

    private long getHighScore() {
        return randomizer.nextInt(MAX_HIGH_SCORE);
    }

    @Test
    public void getByName() {
        highScores.add(dumbledore);
        assertEquals(dumbledore, highScores.findPlayer("albus", "dumbledore").get(0));
    }

    @Test
    public void getByFirstName() {
        highScores.add(dumbledore);
        assertEquals(dumbledore, highScores.findPlayerByFirstName("albus").get(0));
    }

    @Test
    public void getByLastName() {
        highScores.add(dumbledore);
        assertEquals(dumbledore, highScores.findPlayerByLastName("dumbledore").get(0));
    }
}