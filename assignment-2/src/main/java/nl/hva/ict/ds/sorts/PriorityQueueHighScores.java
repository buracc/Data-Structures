package nl.hva.ict.ds.sorts;

import nl.hva.ict.ds.interfaces.HighScoreList;
import nl.hva.ict.ds.objects.Player;

import java.util.*;

public class PriorityQueueHighScores implements HighScoreList {
    List<Player> playerList = new ArrayList<>(); // ArrayList van Players die worden toegevoegd.
    PriorityQueue<Player> priorityQueue = new PriorityQueue<>((p1, p2) -> {
        if (p1.getHighScore() > p2.getHighScore())
            return 1;
        if (p1.getHighScore() < p2.getHighScore())
            return -1;
        else
            return 0;
    });

    /**
     * Werkt op dezelfde manier als in BucketSortHighScores, alleen wordt hier de lijst niet gesorteerd, omdat deze bij
     * elke toevoeging al gesorteerd wordt.
     * @param numberOfHighScores the maximum number of high-scores you want.
     * @return een List van het aantal opgevraagde Players.
     */
    @Override
    public List<Player> getHighScores(int numberOfHighScores) {
        return priorityQueueToList().subList(0, Math.min(numberOfHighScores, priorityQueueToList().size()));
    }

    /**
     * Deze methode roept de recursieve methode aan wanneer de queue niet leeg is. Als dat wel het geval is,
     * voegt deze methode de toe te voegen Player aan de queue.
     *
     * @param player Player object die wordt toegevoegd aan de queue.
     */
    @Override
    public void add(Player player) {
        priorityQueue.add(player);
    }

    private List<Player> priorityQueueToList() {
        playerList.clear();
        PriorityQueue<Player> priorityQueue = new PriorityQueue<>(this.priorityQueue);
        while (!priorityQueue.isEmpty()) {
            playerList.add(priorityQueue.poll());
        }
        Collections.reverse(playerList);
        return playerList;
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
        for (Player p : priorityQueueToList()) {
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
