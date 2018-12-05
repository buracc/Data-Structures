package nl.hva.ict.ds.util;

import nl.hva.ict.ds.Player;

import java.util.ArrayList;
import java.util.List;

public class DoubleHashingMultiValueSymbolTable implements MultiValueSymbolTable<String, Player> {

    String[] keys;
    Player[] values;
    int collisions;
    int maxSize;

    public DoubleHashingMultiValueSymbolTable(int arraySize) {
        maxSize = arraySize;
        keys = new String[arraySize];
        values = new Player[arraySize];
    }

    private int hash(String key) {
        return Math.abs(key.hashCode() & 0x7fffffff) % maxSize;
    }

    @Override
    public void put(String key, Player value) {
        int i = hash(key);
        while (keys[i] != null) {
            collisions++;
            i = 4 - (i % 4);
        }
        keys[i] = key;
        values[i] = value;
    }

    @Override
    public List<Player> get(String key) {
        List<Player> foundPlayers = new ArrayList<>();
        for (int i = 0; i < maxSize; i++) {
            if (keys[i] != null && keys[i].equals(key)) {
                foundPlayers.add(values[i]);
            }
        }
        return foundPlayers;
    }
}
