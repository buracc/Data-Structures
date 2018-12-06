package nl.hva.ict.ds.util;

import nl.hva.ict.ds.Player;

import java.util.ArrayList;
import java.util.List;

public class DoubleHashingMultiValueSymbolTable implements MultiValueSymbolTable<String, Player> {

    String[] keys;
    Player[] values;
    int primeNumber;
    int collisions;
    int maxSize;

    public DoubleHashingMultiValueSymbolTable(int arraySize) {
        collisions = 0;
        maxSize = arraySize;
        primeNumber = 2; // Er van uit gaande dat de arraySize groter is als 2. Dit priemgetal wordt gebruikt bij hash2
        keys = new String[arraySize];
        values = new Player[arraySize];
    }

    /**
     * Berekent de positie voor het in te voegen of op te halen key.
     * @param key
     * @return hash
     */
    private int hash(String key) {
        return (key.hashCode() & 0x7fffffff) % maxSize;
    }

    /**
     * Tweede hashfunctie die wordt aangeroepen wanneer er een collision optreedt.
     * Deze waarde wordt gebruikt om de stapgrootte per put actie te bepalen.
     * @param key
     * @return hash2
     */
    private int hash2(String key) {
        return (primeNumber - (key.hashCode() % primeNumber));
    }

    /**
     * Plaats een key met bijbehorende value in de HashTable.
     * @param key the key to use.
     * @param value the value to be stored.
     */
    @Override
    public void put(String key, Player value) {
        int i = hash(key); // Index

        while (values[i] != null) {
            this.collisions++;
            i += hash2(key); // Nieuwe index bij collision
            i %= maxSize;
        }
        keys[i] = key;
        values[i] = value;
    }

    /**
     * Geeft de lijst met values terug voor gegeven key.
     * @param key the key for which the values must be returned.
     * @return de gevonden values voor de gegeven key.
     */
    @Override
    public List<Player> get(String key) {
        List<Player> players = new ArrayList<>();
        int i = hash(key);

        while (keys[i] != null) {
            if (keys[i].equals(key)) {
                players.add(values[i]);
            }
            i += hash2(key);
            i %= maxSize;
        }
        return players;
    }

    /**
     * @return totale aantal collisions
     */
    @Override
    public int getCollisions() {
        return collisions;
    }
}
