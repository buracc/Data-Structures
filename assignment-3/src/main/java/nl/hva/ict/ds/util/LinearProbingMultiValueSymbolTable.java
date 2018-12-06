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
        keys = new String[arraySize];
        values = new Player[arraySize];
        collisions = 0;
        maxSize = arraySize;
    }

    /**
     * Berekent de positie voor het in te voegen of op te halen key.
     *
     * @param key
     * @return hash
     */
    private int hash(String key) {
        return (key.hashCode() & 0x7fffffff) % maxSize;
    }

    /**
     * Plaatst een key met een bijbehoorende value in de tabel. Wanneer er een collision optreedt, wordt de positie
     * vergroot met 1. Net zolang totdat er een beschikbare positie gevonden is.
     *
     * @param key   the key to use.
     * @param value the value to be stored.
     */
    @Override
    public void put(String key, Player value) {
        int i = hash(key); // Index
        while (keys[i] != null) {
            collisions++;
            i = (i + 1) % maxSize; // Nieuwe index bij collision
        }
        keys[i] = key;
        values[i] = value;
    }

    /**
     * Geeft de lijst met values terug voor gegeven key.
     *
     * @param key the key for which the values must be returned.
     * @return de gevonden values voor de gegeven key.
     */
    @Override
    public List<Player> get(String key) {
        List<Player> foundPlayers = new ArrayList<>();
        int i = hash(key);
        while (keys[i] != null) {
            if (keys[i] != null && keys[i].equals(key)) {
                if (!foundPlayers.contains(values[i])) {
                    foundPlayers.add(values[i]);
                }
            }
            i = (i + 1) % maxSize;
        }
        return foundPlayers;
    }

    /**
     * @return totale aantal collisions.
     */
    @Override
    public int getCollisions() {
        return collisions;
    }
}
