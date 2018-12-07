package nl.hva.ict.ds;

import nl.hva.ict.ds.util.NameReader;
import org.junit.Test;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;

/**
 * If you have any tests that should be overwritten or added please put them to this class.
 */
public class ExtendedPlayerFinderTest extends HighScorePlayerFinderTest {

    /**
     * Test of bepaalde spelers niet bestaan in de lijst, en dus de functies niet oneindig gaan zoeken.
     */
    @Test
    public void checkNotFound() {
        List<Player> burakInan = highscores.findPlayer("Burak", "Inan");

        assertEquals(0, burakInan.size());
    }

    /**
     * Test of spelers met dezelfde naam ook worden gevonden.
     */
    @Test
    public void checkSameFirstName() {
        highscores = new HighScorePlayerFinder(7);
        Player potter = new Player("Albus", "Potter", 96);
        Player dumbledore = new Player("Albus", "Dumbledore", 95);
        highscores.add(potter);
        highscores.add(dumbledore);
        List<Player> headless = highscores.findPlayer("Albus", null);

        assertEquals(2, headless.size());
    }

    /**
     * De volgende methodes berekenen het aantal collisions bij verschillende maximum sizes.
     */
    @Test
    public void collisions10501() {
        System.out.println(getCollisions(10501));
    }

    @Test
    public void collisions11701() {
        System.out.println(getCollisions(11701));
    }

    @Test
    public void collisions13309() {
        System.out.println(getCollisions(13309));
    }

    @Test
    public void collisions15401() {
        System.out.println(getCollisions(15401));
    }

    private String getCollisions(int maxSize) {
        String[] firstNames = new NameReader("/firstnames.txt").getNames();
        String[] lastNames = new NameReader("/lastnames.txt").getNames();
        int maxPlayers = 10000;

        highscores = new HighScorePlayerFinder(maxSize); // Please adjust this size!
        Random rand = new Random();
        for (int i = 0; i < maxPlayers; i++) {
            String firstName = firstNames[rand.nextInt(firstNames.length)];
            String lastName = lastNames[rand.nextInt(lastNames.length)];
            highscores.add(new Player(firstName, lastName, rand.nextInt(HighScorePlayerFinderTest.MAX_HIGH_SCORE)));
        }
        System.out.println("Size: " + maxSize);
        return highscores.getAllCollisions();
    }

}
