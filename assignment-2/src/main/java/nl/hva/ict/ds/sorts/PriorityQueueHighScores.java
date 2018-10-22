package nl.hva.ict.ds.sorts;

import nl.hva.ict.ds.interfaces.HighScoreList;
import nl.hva.ict.ds.objects.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PriorityQueueHighScores implements HighScoreList, Comparator<Player> {
    List<Player> list = new ArrayList<>(); // ArrayList van Players die worden toegevoegd.
    int i = 0; // Index van de queue.

    /**
     * Deze methode roept de recursieve methode aan wanneer de queue niet leeg is. Als dat wel het geval is,
     * voegt deze methode de toe te voegen Player aan de queue.
     *
     * @param player Player object die wordt toegevoegd aan de queue.
     */
    @Override
    public void add(Player player) {
        if (list.isEmpty()) {
            list.add(player);
        } else {
            recursiveAdd(i, player);
        }
    }

    /**
     * Deze methode word aangeroepen zodra de queue niet leeg is. De methode word recursief aangeroepen
     * en gaat net zo lang door totdat de compare methode een getal hoger of gelijk aan 0 returnt.
     * Dit wil zeggen dat de highScore van de speler groter of gelijk is dan het element op de huidige index,
     * en dus het object voor dat element word toegevoegd, omdat de 'prioriteit' hoger is.
     *
     * @param index index van de Player.
     * @param player Player object die wordt toegevoegd aan de queue.
     *
     */
    void recursiveAdd(int index, Player player) {
        if (index >= list.size()) {
            list.add(player);
        } else {
            if (compare(player, list.get(index)) >= 0) {
                list.add(index, player);
            } else {
                recursiveAdd(++index, player);
            }
        }
    }

    /**
     *
     * @param numberOfHighScores the maximum number of high-scores you want.
     * @return een List van het aantal opgevraagde Players.
     */
    @Override
    public List<Player> getHighScores(int numberOfHighScores) {
        List<Player> customizedList = new ArrayList<>();
        int loopAmount = (numberOfHighScores >= list.size()) ? list.size() : numberOfHighScores;

        for (int i = 0; i < loopAmount; i++) {
            customizedList.add(list.get(i));
        }
        return customizedList;
    }

    /**
     *
     * @param firstName the firstname of the players must start with or be equal to this value, can be null or empty if
     *                 lastName is not null or empty.
     * @param lastName the lastname of the playersmust start with or be equal to this value, can be null or empty if
     *                 firstName is not null or empty
     * @return List van gevonden spelers op basis van ingevoerde firstName en lastName.
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
     *
     * @param firstName
     * @return List van gevonden spelers op basis van ingevoerde firstName.
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
     *
     * @param lastName
     * @return List van gevonden spelers op basis van ingevoerde lastName.
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

    /**
     *
     * @param p1
     * @param p2
     * @return een getal lager als 0 wanneer de highScore kleiner is dan het vergeleken object,
     * of een getal hoger als 0 wanneer de highScore groter dan het vergeleken object, en anders 0
     * wanneer de highScores gelijk zijn aan elkaar.
     */
    @Override
    public int compare(Player p1, Player p2) {
        if (p1.getHighScore() > p2.getHighScore())
            return 1;

        if (p1.getHighScore() < p2.getHighScore())
            return -1;

        else
            return 0;
    }
}
