package nl.hva.ict.ds.extra;

import com.github.javafaker.Faker;
import nl.hva.ict.ds.interfaces.HighScoreList;
import nl.hva.ict.ds.objects.Player;
import nl.hva.ict.ds.sorts.PriorityQueueHighScores;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class PriorityQueueSmallTests {

    private static final int MAX_HIGH_SCORE = 1000000;
    HighScoreList highScores = new PriorityQueueHighScores();
    Faker faker = new Faker();

    @Test
    public void oneHundredPlayers() {
        for (int i = 0; i < 100; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            Random rand = new Random();
            long score = rand.nextInt(MAX_HIGH_SCORE);


            Player p = new Player(firstName, lastName, score);
            highScores.add(p);
        }

        assertEquals(100, highScores.getHighScores(100).size());
    }

    @Test
    public void twoHundredPlayers() {
        for (int i = 0; i < 200; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            Random rand = new Random();
            long score = rand.nextInt(MAX_HIGH_SCORE);


            Player p = new Player(firstName, lastName, score);
            highScores.add(p);
        }

        assertEquals(200, highScores.getHighScores(200).size());
    }

    @Test
    public void fourHundredPlayers() {
        for (int i = 0; i < 400; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            Random rand = new Random();
            long score = rand.nextInt(MAX_HIGH_SCORE);


            Player p = new Player(firstName, lastName, score);
            highScores.add(p);
        }

        assertEquals(400, highScores.getHighScores(400).size());
    }

    @Test
    public void eightHundredPlayers() {
        for (int i = 0; i < 800; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            Random rand = new Random();
            long score = rand.nextInt(MAX_HIGH_SCORE);


            Player p = new Player(firstName, lastName, score);
            highScores.add(p);
        }

        assertEquals(800, highScores.getHighScores(800).size());
    }

    @Test
    public void sixTeenHundredPlayers() {
        for (int i = 0; i < 1600; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            Random rand = new Random();
            long score = rand.nextInt(MAX_HIGH_SCORE);


            Player p = new Player(firstName, lastName, score);
            highScores.add(p);
        }

        assertEquals(1600, highScores.getHighScores(1600).size());
    }

    @Test
    public void thirtyTwoHundredPlayers() {
        for (int i = 0; i < 3200; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            Random rand = new Random();
            long score = rand.nextInt(MAX_HIGH_SCORE);


            Player p = new Player(firstName, lastName, score);
            highScores.add(p);
        }

        assertEquals(3200, highScores.getHighScores(3200).size());
    }

    @Test
    public void sixtyFourHundredPlayers() {
        for (int i = 0; i < 6400; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            Random rand = new Random();
            long score = rand.nextInt(MAX_HIGH_SCORE);


            Player p = new Player(firstName, lastName, score);
            highScores.add(p);
        }

        assertEquals(6400, highScores.getHighScores(6400).size());
    }

    @Test
    public void twelveEightHundredPlayers() {
        for (int i = 0; i < 12800; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            Random rand = new Random();
            long score = rand.nextInt(MAX_HIGH_SCORE);


            Player p = new Player(firstName, lastName, score);
            highScores.add(p);
        }

        assertEquals(12800, highScores.getHighScores(12800).size());
    }

    @Test
    public void twentyFiveSixHundredPlayers() {
        for (int i = 0; i < 25600; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            Random rand = new Random();
            long score = rand.nextInt(MAX_HIGH_SCORE);


            Player p = new Player(firstName, lastName, score);
            highScores.add(p);
        }

        assertEquals(25600, highScores.getHighScores(25600).size());
    }
}
