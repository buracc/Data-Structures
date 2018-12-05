package nl.hva.ict.ds.sorts;

import nl.hva.ict.ds.interfaces.HighScoreList;
import nl.hva.ict.ds.objects.Player;

import java.util.ArrayList;
import java.util.List;

public class InsertionSortHighScores implements HighScoreList {
    private List<Player> playerList = new ArrayList<>();

    @Override
    public void add(Player player) {
        playerList.add(player);
    }

    /**
     * Werkt op de zelfde manier als in BucketSort. Zie de methode in BucketSortHighScores.
     *
     * @param numberOfHighScores the maximum number of high-scores you want.
     * @return
     */
    @Override
    public List<Player> getHighScores(int numberOfHighScores) {
        sort();
        return playerList.subList(0, Math.min(numberOfHighScores, playerList.size()));
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

        for (int i = 1; i < playerList.size(); i++) {
            highScore = playerList.get(i).getHighScore();
            int j = i - 1;
            while (j >= 0 && highScore > playerList.get(j).getHighScore()) {
                temp = playerList.get(j);
                playerList.set(j, playerList.get(j + 1));
                playerList.set(j + 1, temp);
                j--;
            }
        }
        return playerList;
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

}
