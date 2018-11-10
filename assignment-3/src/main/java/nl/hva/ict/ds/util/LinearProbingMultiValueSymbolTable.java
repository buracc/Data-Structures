package nl.hva.ict.ds.util;

import nl.hva.ict.ds.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinearProbingMultiValueSymbolTable implements MultiValueSymbolTable<String, Player> {

    String[] keys;
    Player[] values;
    int collisions;
    int maxSize;

    public LinearProbingMultiValueSymbolTable(int arraySize) {
        maxSize = arraySize;
        keys = new String[arraySize];
        values = new Player[arraySize];
    }

    private int hash(String key) {
        return Math.abs(key.hashCode()) % maxSize;
    }

    @Override
    public void put(String key, Player value) {
        int i = hash(key);
        while (keys[i] != null) {
            collisions++;
            i = (i + 1) % maxSize;
        }
        keys[i] = key;
        values[i] = value;
    }

    @Override
    public List<Player> get(String key) {
        List<Player> foundPlayers = new ArrayList<>();
        int i = hash(key);
        while (keys[i] != null) {
            if (keys[i] != null && keys[i].equals(key)) {
                foundPlayers.add(values[i]);
            }
            i = (i + 1) % maxSize;
        }
//        for (int i = 0; i < maxSize; i++) {
//            if (keys[i] != null && keys[i].equals(key)) {
//                foundPlayers.add(values[i]);
//            }
//        }
        return foundPlayers;
    }
}
