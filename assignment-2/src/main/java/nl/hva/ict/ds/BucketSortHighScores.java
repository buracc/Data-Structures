package nl.hva.ict.ds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BucketSortHighScores implements HighScoreList {
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

        List<Player> sortedList = bucketSort();
        for (int i = 0; i < loopAmount; i++) {
            customizedList.add(sortedList.get(i));
        }
        return customizedList;
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
            if (p.getFirstName().equalsIgnoreCase(lastName))
                foundPlayers.add(p);
        }
        return foundPlayers;
    }

    public List<Player> bucketSort() {

        long minVal = list.get(0).getHighScore();
        long maxVal = list.get(0).getHighScore();

        // get max and min value
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getHighScore() < minVal) {
                minVal = list.get(i).getHighScore();
            } else if (list.get(i).getHighScore() > maxVal) {
                maxVal = list.get(i).getHighScore();
            }
        }

        if (list == null || list.size() == 0 || minVal == maxVal) return list;

        final int N = list.size();
        final int M = (int) (maxVal - minVal);
        final int MAX_BUCKETS = M/N + 1;

        List<List<Player>> buckets = new ArrayList<>(MAX_BUCKETS);
        for(int i = 0; i < MAX_BUCKETS; i++) buckets.add(new ArrayList<>());

        for (int i = 0; i < N; i++) {
            int bi = (int) (list.get(i).getHighScore() - minVal) / M;
            List <Player> bucket = buckets.get(bi);
            bucket.add(list.get(i));
        }

        for (int bi = 0, j = 0; bi < MAX_BUCKETS; bi++) {
            List<Player> bucket = buckets.get(bi);
            if (bucket != null) {
                sort(bucket);
                for (int k = 0; k < bucket.size(); k++) {
                    list.set(j++, bucket.get(k));
                }
            }
        }
        return list;
    }

    public List<Player> sort(List<Player> list) {
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
}
