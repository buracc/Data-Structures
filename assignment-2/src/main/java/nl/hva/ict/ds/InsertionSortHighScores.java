package nl.hva.ict.ds;

import java.util.ArrayList;
import java.util.List;

public class InsertionSortHighScores implements HighScoreList {
    private List<Player> list = new ArrayList<>();

    @Override
    public void add(Player player) {
        list.add(player);
    }

    @Override
    public List<Player> getHighScores(int numberOfHighScores) {
        List<Player> customizedList = new ArrayList<>();
        int loopAmount = (numberOfHighScores > list.size()) ? list.size() : numberOfHighScores;

        if (loopAmount > list.size() || list.size() == 0)
            throw new IndexOutOfBoundsException("The list is empty.");

        List<Player> sortedList = sort();

        for (int i = 0; i < loopAmount; i++) {
            customizedList.add(sortedList.get(i));
        }
        return customizedList;
    }

    public List<Player> sort() {
        long key;
        Player temp;

        for (int i = 1; i < list.size(); i++) {
            key = list.get(i).getHighScore();
            int j = i - 1;
            while (j >= 0 && key < list.get(j).getHighScore()) {
                temp = list.get(j);
                list.set(j, list.get(j + 1));
                list.set(j + 1, temp);
                j--;
            }
        }
        return list;
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

    @Override
    public List<Player> findPlayerByFirstName(String firstName) throws IllegalArgumentException {
        List<Player> foundPlayers = new ArrayList<>();
        for (Player p : list) {
            if (p.getFirstName().equalsIgnoreCase(firstName))
                foundPlayers.add(p);
        }
        return foundPlayers;
    }

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
