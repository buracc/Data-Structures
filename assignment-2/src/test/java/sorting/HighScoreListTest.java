package sorting;

import com.github.javafaker.Faker;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.junit.After;
import sorting.interfaces.HighScoreList;
import sorting.objects.Player;
import sorting.sorts.BucketSortHighScores;
import org.junit.Before;
import org.junit.Test;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Every.everyItem;


public class HighScoreListTest {
    private static final int MAX_HIGH_SCORE = 100000;
    private Random randomizer = new SecureRandom();
    private HighScoreList highScores;
    private Player nearlyHeadlessNick;
    private Player dumbledore;


    @Before
    public void setup() {
//        De te testen implementatie moet hier gekozen worden door het te uncommenten.
//        highScores = new DummyHighScores();
//        highScores = new InsertionSortHighScores();
        highScores = new BucketSortHighScores();
//        highScores = new PriorityQueueHighScores();

        nearlyHeadlessNick = new Player("Nicholas", "de Mimsy-Porpington", getHighScore() % 200);
        dumbledore = new Player("Albus", "Dumbledore", nearlyHeadlessNick.getHighScore() * 1000);
    }

    /**
     * Geeft een random high score binnen de max high score.
     * @return
     */
    private long getHighScore() {
        return randomizer.nextInt(MAX_HIGH_SCORE);
    }

    /**
     * Genereert een speler, en voegt deze toe aan de lijst.
     * @param amount
     * @param highScores
     */
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

    /**
     * Test dat de lijst leeg is wanneer er geen spelers aanwezig zijn.
     */
    @Test
    public void noPlayerNoHighScore() {
        assertThat(highScores.getHighScores(1), iterableWithSize(0));
    }


    /**
     * Test dat er geen speler wordt teruggegeven wanneer er naar 0 spelers wordt gezocht.
     */
    @Test
    public void whenNoHighScoreIsAskedForNonShouldBeGiven() {
        highScores.add(dumbledore);

        assertThat(highScores.getHighScores(0), hasSize(0));
    }

    /**
     * Test de highScore property van Player object
     */
    @Test
    public void testProperty() {
        highScores.add(dumbledore);

        assertThat(dumbledore, hasProperty("highScore"));
    }

    /**
     * Test twee spelers met dezelfde property
     */
    @Test
    public void testSameProperty() {
        highScores.add(dumbledore);
        Player dumbledore2 = new Player("Albus", "Dumbledore", nearlyHeadlessNick.getHighScore() * 1000);

        assertThat(dumbledore, samePropertyValuesAs(dumbledore2));
    }

    /**
     * Test dat er niet meer dan een bestaand aantal spelers wordt teruggegeven. En dat de volgorde klopt.
     */
    @Test
    public void noMoreHighScoresCanBeGivenThenPresent() {
        highScores.add(nearlyHeadlessNick);
        highScores.add(dumbledore);

        assertThat(highScores.getHighScores(10), both(hasSize(2)).and(containsInAnyOrder(dumbledore, nearlyHeadlessNick)));
    }

    /**
     * Test de grootte van de lijst.
     */
    @Test
    public void keepAllHighScores() {
        highScores.add(nearlyHeadlessNick);
        highScores.add(dumbledore);

        assertEquals(2, highScores.getHighScores(2).size());
    }

    /**
     * Test de rank 1 speler in de lijst.
     */
    @Test
    public void singlePlayerHasHighScore() {
        highScores.add(dumbledore);

        assertEquals(dumbledore, highScores.getHighScores(1).get(0));
    }

    /**
     * Test de rank 1 speler in de lijst met een tweede speler.
     */
    @Test
    public void harryBeatsDumbledore() {
        highScores.add(dumbledore);
        Player harry = new Player("Harry", "Potter", dumbledore.getHighScore() + 1);

        highScores.add(harry);

        assertEquals(harry, highScores.getHighScores(1).get(0));
    }

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

    /**
     * Zet highscores naar null, en voert garbage collection uit om de ongebruikte objecten op te schonen.
     */
    @After
    public void tearDown() {
        highScores = null;
        System.gc();
    }

}

class MatcherCombinator<T> extends BaseMatcher<T> {
    private final List<Matcher<? super T>> matchers = new ArrayList<>();
    private final List<Matcher<? super T>> failedMatchers = new ArrayList<>();

    private MatcherCombinator(final Matcher<? super T> matcher) {
        matchers.add(matcher);
    }

    public MatcherCombinator<T> and(final Matcher<? super T> matcher) {
        matchers.add(matcher);
        return this;
    }

    @Override
    public boolean matches(final Object item) {
        boolean matchesAllMatchers = true;
        for (final Matcher<? super T> matcher : matchers) {
            if (!matcher.matches(item)) {
                failedMatchers.add(matcher);
                matchesAllMatchers = false;
            }
        }
        return matchesAllMatchers;
    }

    @Override
    public void describeTo(final Description description) {
        description.appendValueList("\n", " " + "and" + "\n", "", matchers);
    }

    @Override
    public void describeMismatch(final Object item, final Description description) {
        description.appendText("\n");
        for (Iterator<Matcher<? super T>> iterator = failedMatchers.iterator(); iterator.hasNext();) {
            final Matcher<? super T> matcher = iterator.next();
            description.appendText("Expected: <");
            description.appendDescriptionOf(matcher).appendText(" but ");
            matcher.describeMismatch(item, description);
            if (iterator.hasNext()) {
                description.appendText(">\n");
            }
        }
    }

    public static <LHS> MatcherCombinator<LHS> matches(final Matcher<? super LHS> matcher) {
        return new MatcherCombinator<LHS>(matcher);
    }
}