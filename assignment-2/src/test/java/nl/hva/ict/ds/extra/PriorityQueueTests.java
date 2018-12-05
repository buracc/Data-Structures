package nl.hva.ict.ds.extra;

import com.github.javafaker.Faker;
import nl.hva.ict.ds.interfaces.HighScoreList;
import nl.hva.ict.ds.objects.Player;
import nl.hva.ict.ds.sorts.PriorityQueueHighScores;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class PriorityQueueTests {

    private static final int MAX_HIGH_SCORE = 1000000;
    HighScoreList highScores = new PriorityQueueHighScores();
    Faker faker = new Faker();

    @Test
    public void thousandPlayers() {
        for (int i = 0; i < 1000; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            Random rand = new Random();
            long score = rand.nextInt(MAX_HIGH_SCORE);


            Player p = new Player(firstName, lastName, score);
            highScores.add(p);
        }

        assertEquals(1000, highScores.getHighScores(1000).size());
    }

    @Test
    public void twoThousandPlayers() {
        for (int i = 0; i < 2000; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            Random rand = new Random();
            long score = rand.nextInt(MAX_HIGH_SCORE);


            Player p = new Player(firstName, lastName, score);
            highScores.add(p);
        }

        assertEquals(2000, highScores.getHighScores(2000).size());
    }

    @Test
    public void fourThousandPlayers() {
        for (int i = 0; i < 4000; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            Random rand = new Random();
            long score = rand.nextInt(MAX_HIGH_SCORE);


            Player p = new Player(firstName, lastName, score);
            highScores.add(p);
        }

        assertEquals(4000, highScores.getHighScores(4000).size());
    }

    @Test
    public void eightThousandPlayers() {
        for (int i = 0; i < 8000; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            Random rand = new Random();
            long score = rand.nextInt(MAX_HIGH_SCORE);


            Player p = new Player(firstName, lastName, score);
            highScores.add(p);
        }

        assertEquals(8000, highScores.getHighScores(8000).size());
    }

    @Test
    public void sixTeenThousandPlayers() {
        for (int i = 0; i < 16000; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            Random rand = new Random();
            long score = rand.nextInt(MAX_HIGH_SCORE);


            Player p = new Player(firstName, lastName, score);
            highScores.add(p);
        }

        assertEquals(16000, highScores.getHighScores(16000).size());
    }

    @Test
    public void thirtyTwoThousandPlayers() {
        for (int i = 0; i < 32000; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            Random rand = new Random();
            long score = rand.nextInt(MAX_HIGH_SCORE);


            Player p = new Player(firstName, lastName, score);
            highScores.add(p);
        }

        assertEquals(32000, highScores.getHighScores(32000).size());
    }

    @Test
    public void sixtyFourThousandPlayers() {
        for (int i = 0; i < 64000; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            Random rand = new Random();
            long score = rand.nextInt(MAX_HIGH_SCORE);


            Player p = new Player(firstName, lastName, score);
            highScores.add(p);
        }

        assertEquals(64000, highScores.getHighScores(64000).size());
    }

    @Test
    public void oneTwentyEightThousandPlayers() {
        for (int i = 0; i < 128000; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            Random rand = new Random();
            long score = rand.nextInt(MAX_HIGH_SCORE);


            Player p = new Player(firstName, lastName, score);
            highScores.add(p);
        }

        assertEquals(128000, highScores.getHighScores(128000).size());
    }

    @Test
    public void twoFiftySixThousandPlayers() {
        for (int i = 0; i < 256000; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            Random rand = new Random();
            long score = rand.nextInt(MAX_HIGH_SCORE);


            Player p = new Player(firstName, lastName, score);
            highScores.add(p);
        }

        assertEquals(256000, highScores.getHighScores(256000).size());
    }
}
