package nl.hva.ict.ds;

import java.util.ArrayList;
import java.util.List;

public class PriorityQueueHighScores implements HighScoreList {
    List<Player> list = new ArrayList<>();

    @Override
    public void add(Player player) {

    }

    @Override
    public List<Player> getHighScores(int numberOfHighScores) {
        return null;
    }

    @Override
    public List<Player> findPlayer(String firstName, String lastName) throws IllegalArgumentException {
        List<Player> foundPlayers = new ArrayList<>();
        for (Player p : list) {
            if (p.getFirstName().equalsIgnoreCase(firstName) && p.getLastName().equalsIgnoreCase(lastName))
                foundPlayers.add(p);
        }
        return foundPlayers;
    }

    public List<Player> findPlayerByFirstName(String firstName) throws IllegalArgumentException {
        List<Player> foundPlayers = new ArrayList<>();
        for (Player p : list) {
            if (p.getFirstName().equalsIgnoreCase(firstName))
                foundPlayers.add(p);
        }
        return foundPlayers;
    }

    public List<Player> findPlayerByLastName(String lastName) throws IllegalArgumentException {
        List<Player> foundPlayers = new ArrayList<>();
        for (Player p : list) {
            if (p.getFirstName().equalsIgnoreCase(lastName))
                foundPlayers.add(p);
        }
        return foundPlayers;
    }
}
