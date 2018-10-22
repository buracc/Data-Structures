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


    static class Bucket {
        List<Player> bucket = new ArrayList<>();
    }

    public List<Player> bucketSort(List<Player> list) {
        List<Player> sortedList = new ArrayList<>();

        long maxScore = 0;
        for (Player p : list) {
            if (p.getHighScore() >= maxScore) {
                maxScore = p.getHighScore();
            }
        }

        int maxBuckets = 4;
        long bucketRange = maxScore / maxBuckets;
        long tempBucketRange = 0;
        Bucket[] buckets = new Bucket[maxBuckets];
        Arrays.fill(buckets, new Bucket());

        for (int i = 0; i < maxBuckets; i++) {
            tempBucketRange = (tempBucketRange + bucketRange) + 1;
            for (Player p : list) {
                if (p.getHighScore() <= tempBucketRange && p.getHighScore() > tempBucketRange - bucketRange) {
                    buckets[i].bucket.add(p);
                }
            }
        }

        for (Bucket b : buckets) {
            sort(b.bucket);
        }

        for (Bucket b : buckets) {
            sortedList.addAll(b.bucket);

        }

        Collections.reverse(sortedList);
        return sortedList;
    }

    void sort(List<Player> list) {
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
    }
}
