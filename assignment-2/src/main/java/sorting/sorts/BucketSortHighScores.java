package sorting.sorts;

import sorting.interfaces.HighScoreList;
import sorting.objects.Player;

import java.util.*;

public class BucketSortHighScores implements HighScoreList {
    private List<Player> playerList = new ArrayList<>();

    @Override
    public void add(Player player) {
        playerList.add(player);
    }

    /**
     * @param numberOfHighScores the maximum number of high-scores you want.
     * @return De lijst van gevonden spelers.
     */
    @Override
    public List<Player> getHighScores(int numberOfHighScores) {
        List<Player> sortedList = bucketSort(playerList);

        return sortedList.subList(0, Math.min(numberOfHighScores, sortedList.size()));
    }

    /**
     * @param firstName the firstname of the players must start with or be equal to this value, can be null or empty if
     *                 lastName is not null or empty.
     * @param lastName the lastname of the playersmust start with or be equal to this value, can be null or empty if
     *                 firstName is not null or empty
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public List<Player> findPlayer(String firstName, String lastName) throws IllegalArgumentException {
        List<Player> foundPlayers = new ArrayList<>();
        for (Player p : playerList) {
            if (firstName.equalsIgnoreCase(p.getFirstName()) && lastName.equals("")) {
                foundPlayers.add(p);
            } else if (firstName.equals("") && lastName.equalsIgnoreCase(p.getLastName())) {
                foundPlayers.add(p);
            } else if (firstName.equalsIgnoreCase(p.getFirstName()) && lastName.equalsIgnoreCase(p.getLastName())){
                foundPlayers.add(p);
            }
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

        if (list.isEmpty()) {
            return list;
        }

        long maxScore = Integer.MIN_VALUE;
        long minScore = Integer.MAX_VALUE;

        for (Player p : list) {
            if (p.getHighScore() >= maxScore) {
                maxScore = p.getHighScore();
            }

            if (p.getHighScore() <= minScore) {
                minScore = p.getHighScore();
            }
        }

        int maxBuckets = (int) Math.sqrt(list.size());
        long bucketRange = maxScore / maxBuckets + 1;
        long minBucketRange = minScore;
        long maxBucketRange = bucketRange;
        Bucket[] buckets = new Bucket[maxBuckets];

        for (int i = 0; i < maxBuckets; i++) {
            buckets[i] = new Bucket();
        }

        for (int i = 0; i < maxBuckets; i++) {
            for (Player p : list) {
                if (p.getHighScore() >= minBucketRange && p.getHighScore() <= bucketRange) {
                    buckets[i].bucket.add(p);
                }
            }
            minBucketRange = bucketRange + 1;
            bucketRange += maxBucketRange;
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



