package nl.hva.ict.ds.util;

import nl.hva.ict.ds.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuadraticProbingMultiValueSymbolTable implements MultiValueSymbolTable<String, Player> {

    String[] keys;
    Player[] values;
    int collisions;
    int maxSize;


    public QuadraticProbingMultiValueSymbolTable(int arraySize) {
        keys = new String[arraySize];
        values = new Player[arraySize];
        maxSize = arraySize;
        collisions = 0;
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
     * kwadratisch verhoogd. Net zolang totdat er een beschikbare positie gevonden is.
     *
     * @param key   the key to use.
     * @param value the value to be stored.
     */
    @Override
    public void put(String key, Player value) {
        int i = hash(key);
        int power = 1;
        while (keys[i] != null) {
            collisions++;
            i = (power * (power++)) % maxSize; // Nieuwe index
            if (i < 0 || i > maxSize) {
                i = hash(key); // Nieuwe index als we buiten de lijst zitten
            }
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
        List<Player> foundPlayers = new ArrayList<>();
        int i = hash(key);
        int power = 1;
        while (i > 0 && i < maxSize) {
            if (keys[i].equals(key)) {
                if (!foundPlayers.contains(values[i])) {
                    foundPlayers.add(values[i]);
                }
            }
            i = (power * (power++)) % maxSize;
        }
        return foundPlayers;
    }

    /**
     * @return totale aantall collisions.
     */
    @Override
    public int getCollisions() {
        return collisions;
    }

}