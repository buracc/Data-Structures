package sorting.timetests;

import sorting.HighScoreListTest;
import sorting.interfaces.HighScoreList;
import sorting.sorts.InsertionSortHighScores;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InsertionSortTests {

    HighScoreList highScores = new InsertionSortHighScores();

    @Test
    public void thousandPlayers() {
        int maxSize = 1000;
        HighScoreListTest.generatePlayers(maxSize, highScores);

        assertEquals(maxSize, highScores.getHighScores(maxSize).size());
    }

    @Test
    public void twoThousandPlayers() {
        int maxSize = 2000;
        HighScoreListTest.generatePlayers(maxSize, highScores);

        assertEquals(maxSize, highScores.getHighScores(maxSize).size());
    }

    @Test
    public void fourThousandPlayers() {
        int maxSize = 4000;
        HighScoreListTest.generatePlayers(maxSize, highScores);

        assertEquals(maxSize, highScores.getHighScores(maxSize).size());
    }

    @Test
    public void eightThousandPlayers() {
        int maxSize = 8000;
        HighScoreListTest.generatePlayers(maxSize, highScores);

        assertEquals(maxSize, highScores.getHighScores(maxSize).size());
    }

    @Test
    public void sixTeenThousandPlayers() {
        int maxSize = 16000;
        HighScoreListTest.generatePlayers(maxSize, highScores);

        assertEquals(maxSize, highScores.getHighScores(maxSize).size());
    }

    @Test
    public void thirtyTwoThousandPlayers() {
        int maxSize = 32000;
        HighScoreListTest.generatePlayers(maxSize, highScores);

        assertEquals(maxSize, highScores.getHighScores(maxSize).size());
    }

    @Test
    public void sixtyFourThousandPlayers() {
        int maxSize = 64000;
        HighScoreListTest.generatePlayers(maxSize, highScores);

        assertEquals(maxSize, highScores.getHighScores(maxSize).size());
    }

    @Test
    public void oneTwentyEightThousandPlayers() {
        int maxSize = 128000;
        HighScoreListTest.generatePlayers(maxSize, highScores);

        assertEquals(maxSize, highScores.getHighScores(maxSize).size());
    }

    @Test
    public void twoFiftySixThousandPlayers() {
        int maxSize = 256000;
        HighScoreListTest.generatePlayers(maxSize, highScores);

        assertEquals(maxSize, highScores.getHighScores(maxSize).size());
    }
}
