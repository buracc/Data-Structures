package nl.hva.ict.ds.sorts;

import nl.hva.ict.ds.interfaces.HighScoreList;
import nl.hva.ict.ds.objects.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InsertionSortHighScores implements HighScoreList {
    private List<Player> list = new ArrayList<>();

    @Override
    public void add(Player player) {
        list.add(player);
    }

    /**
     * Werkt op de zelfde manier als in BucketSort. Zie de methode in BucketSortHighScores.
     *
     * @param numberOfHighScores the maximum number of high-scores you want.
     * @return
     */
    @Override
    public List<Player> getHighScores(int numberOfHighScores) {
        List<Player> customizedList = new ArrayList<>();
        int loopAmount = (numberOfHighScores >= list.size()) ? list.size() : numberOfHighScores;

        List<Player> sortedList = sort();

        for (int i = 0; i < loopAmount; i++) {
            customizedList.add(sortedList.get(i));
        }
        return customizedList;
    }


    /**
     * Insertion Sort. Beginnend op index 1, wordt de highscore vergeleken met index-1, en wordt er gekeken of deze groter
     * is dan de highscore ervoor. Als dat zo is worden deze met elkaar omgewisseld. Normaal moet er worden gekeken of de
     * waarde ervoor kleiner is, maar omdat de hoogste highscore in dit geval vooraan moet staan wordt er gekeken of deze
     * groter is.
     *
     */
    public List<Player> sort() {
        long highScore;
        Player temp;

        for (int i = 1; i < list.size(); i++) {
            highScore = list.get(i).getHighScore();
            int j = i - 1;
            while (j >= 0 && highScore > list.get(j).getHighScore()) {
                temp = list.get(j);
                list.set(j, list.get(j + 1));
                list.set(j + 1, temp);
                j--;
            }
        }
        return list;
    }

    /**
     * De volgende methodes werken allemaal op de zelfde manier in alle klassen. Zie de methode in BucketSortHighScores
     * voor documentatie.
     */

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
        for (Player p : list) {
            if (p.getFirstName().equalsIgnoreCase(firstName) && p.getLastName().equalsIgnoreCase(lastName))
                foundPlayers.add(p);
        }
        return foundPlayers;
    }

    /**
     * @param firstName
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public List<Player> findPlayerByFirstName(String firstName) throws IllegalArgumentException {
        List<Player> foundPlayers = new ArrayList<>();
        for (Player p : list) {
            if (p.getFirstName().equalsIgnoreCase(firstName))
                foundPlayers.add(p);
        }
        return foundPlayers;
    }

    /**
     * @param lastName
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public List<Player> findPlayerByLastName(String lastName) throws IllegalArgumentException {
        List<Player> foundPlayers = new ArrayList<>();
        for (Player p : list) {
            if (p.getLastName().equalsIgnoreCase(lastName))
                foundPlayers.add(p);
        }
        return foundPlayers;
    }
}
