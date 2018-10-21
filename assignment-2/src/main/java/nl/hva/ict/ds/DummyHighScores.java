package nl.hva.ict.ds;

import java.util.ArrayList;
import java.util.List;

/**
 * Just a rough sketch that prevents the JUnit tests to case compilation errors.
 * By no means this is a implementation that will work properly!
 */
public class DummyHighScores implements HighScoreList {
    private List<Player> players = new ArrayList<>();

    @Override
    public void add(Player player) {
        players.add(player);
    }

    @Override
    public List<Player> getHighScores(int numberOfHighScores) {
        return players.subList(0, Math.min(numberOfHighScores, players.size()));
    }

    @Override
    public List<Player> findPlayer(String firstName, String lastName) {
        List<Player> matchedPlayers = new ArrayList<>();
        for (Player player : players) {
            if (player.getFirstName().equals(firstName)) {
                matchedPlayers.add(player);
            }
        }
        return matchedPlayers;
    }

    @Override
    public List<Player> findPlayerByFirstName(String firstName) throws IllegalArgumentException {
        List<Player> foundPlayers = new ArrayList<>();
        for (Player p : players) {
            if (p.getFirstName().equalsIgnoreCase(firstName))
                foundPlayers.add(p);
        }
        return foundPlayers;
    }

    @Override
    public List<Player> findPlayerByLastName(String lastName) throws IllegalArgumentException {
        List<Player> foundPlayers = new ArrayList<>();
        for (Player p : players) {
            if (p.getFirstName().equalsIgnoreCase(lastName))
                foundPlayers.add(p);
        }
        return foundPlayers;
    }
}
