package nl.hva.ict.ds.sorts;

import nl.hva.ict.ds.interfaces.HighScoreList;
import nl.hva.ict.ds.objects.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BucketSortHighScores implements HighScoreList {
    private List<Player> list = new ArrayList<>();

    @Override
    public void add(Player player) {
        list.add(player);
    }

    /**
     * Geeft een lijst van de top 'x' spelers. In dit geval is 'x' het maximum aantal spelers waar je naar wilt zoeken.
     *
     * Bijvoorbeeld:
     * - Er zijn 200 spelers in de lijst.
     * - Je wilt een lijst van de top 10 spelers hebben, dus je geeft de waarde 10 mee aan de methode.
     * - De methode kijkt of de meegegeven waarde groter of gelijk is aan de grootte van de lijst.
     * - Als dat zo is, dus als de lijst bijvoorbeeld maar 6 spelers heeft, retourneert de methode een lijst van top 6 spelers.
     * - Als dat niet zo is, retourneert de methode de lijst van de top 10 spelers. (10 is dus de waarde die in dit geval
     * is meegegeven aan de methode)
     *
     * @param numberOfHighScores the maximum number of high-scores you want.
     * @return De lijst van gevonden spelers.
     */
    @Override
    public List<Player> getHighScores(int numberOfHighScores) {
        List<Player> customizedList = new ArrayList<>();
        int loopAmount = (numberOfHighScores >= list.size()) ? list.size() : numberOfHighScores;

        List<Player> sortedList = bucketSort(list);

        for (int i = 0; i < loopAmount; i++) {
            customizedList.add(sortedList.get(i));
        }
        return customizedList;
    }

    /**
     * Zoekt spelers op basis van firstName en lastName. Bij het zoeken wordt niet gekeken naar hoofdletter
     * gevoeligheid. Wanneer er een speler is gevonden met opgegeven firstName en lastName, wordt deze toegevoegd aan een
     * nieuwe lijst die uiteindelijk wordt geretourneerd door de methode.
     *
     * @param firstName the firstname of the players must start with or be equal to this value, can be null or empty if
     *                 lastName is not null or empty. De firstName die wordt meegegeven waarmee wordt gezocht naar spelers.
     * @param lastName the lastname of the playersmust start with or be equal to this value, can be null or empty if
     *                 firstName is not null or empty. De lastName die wordt meegegeven waarmee wordt gezocht naar spelers.
     * @return De lijst van gevonden spelers.
     * @throws IllegalArgumentException
     */
    @Override
    public List<Player> findPlayer(String firstName, String lastName) throws IllegalArgumentException {
        List<Player> foundPlayers = new ArrayList<>();
        for (Player p : list) {
            if (p.getFirstName().equalsIgnoreCase(firstName) && p.getLastName().equalsIgnoreCase(lastName))
                foundPlayers.add(p);
        }
        return foundPlayers;
    }


    /**
     * Bucket Sort. Werkt als volgt:
     * 1. Eerst wordt er gekeken wat de maximale en minimale waarde is van de highScore, en worden vervolgens opgeslagen
     * in variabelen.
     * 2. Vervolgens worden er buckets aangemaakt, het aantal buckets dat wordt aangemaakt ligt er aan wat het bereik is
     * van de highscores, en hoe groot de lijst is. In elke bucket zit een ArrayList van Player. Ook wordt de bereik van
     * elke bucket bepaald. Dit wordt gedaan door de maximale waarde van highscore te delen door het aantal buckets.
     * 3. Vervolgens wordt in elke bucket een Player geplaatst.
     * 4. Daarna wordt elke bucket gesorteerd met behulp van insertion sort.
     * 5. Tot slot worden alle gesorteerde buckets samengevoegd, en deze wordt geplaatst in een nieuwe lijst die wordt
     * geretourneerd.
     *
     * @param list (ongesorteerde) lijst van Players die gesorteerd moet worden.
     * @return De gesorteerde lijst.
     */
    public List<Player> bucketSort(List<Player> list) {
        List<Player> sortedList = new ArrayList<>();

        long maxScore = Integer.MIN_VALUE;
        long minScore = Integer.MAX_VALUE;
        for (Player p : list) {
            if (p.getHighScore() >= maxScore) {
                maxScore = p.getHighScore();
                minScore = maxScore;
            }

            if (p.getHighScore() < minScore) {
                minScore = p.getHighScore();
            }
        }

        int maxBuckets = (int) (maxScore - minScore) / list.size() + 1;
        long bucketRange = maxScore / maxBuckets;
        long tempBucketRange = 0;
        Bucket[] buckets = new Bucket[maxBuckets];
        for (int i = 0; i < maxBuckets; i++) {
            buckets[i] = new Bucket();
        }

        /*
        TODO
        - issue is dat de range niet klopt, max scores boven de range worden toegevoegd
         */

        for (int i = 0; i < maxBuckets; i++) {
            tempBucketRange = (tempBucketRange + bucketRange);
            for (Player p : list) {
                if (p.getHighScore() <= tempBucketRange && p.getHighScore() >= tempBucketRange - bucketRange) {
                    buckets[i].bucket.add(p);
                }
            }
        }

        for (Bucket b : buckets) {
            sort(b.bucket);
            sortedList.addAll(0, b.bucket);
        }
        return sortedList;
    }

    /**
     * Insertion Sort. Beginnend op index 1, wordt de highscore vergeleken met index-1, en wordt er gekeken of deze groter
     * is dan de highscore ervoor. Als dat zo is worden deze met elkaar omgewisseld. Normaal moet er worden gekeken of de
     * waarde ervoor kleiner is, maar omdat de hoogste highscore in dit geval vooraan moet staan wordt er gekeken of deze
     * groter is.
     *
     * @param list (ongesorteerde) lijst van Players die gesorteerd moet worden.
     */
    void sort(List<Player> list) {
        long key;
        Player temp;

        for (int i = 1; i < list.size(); i++) {
            key = list.get(i).getHighScore();
            int j = i - 1;
            while (j >= 0 && key > list.get(j).getHighScore()) {
                temp = list.get(j);
                list.set(j, list.get(j + 1));
                list.set(j + 1, temp);
                j--;
            }
        }
    }

    /**
     * Inner class Bucket, elke instantie maakt een nieuwe ArrayList aan van Player. Dus elke bucket heeft een lijst met
     * spelers.
     */
    private class Bucket {
        List<Player> bucket = new ArrayList<>();
    }
}



