package nl.hva.ict.ds;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class LinkedListTest {
    protected LinkedList<String> testList;

    @Before
    public void setup() {
        testList = new LinkedList<>();
    }

    @Test
    public void describeSettingJVMArguments() {

    }

    @Test
    public void addToEmptyList() {
        testList.add("one");

        assertTrue(testList.size() != 0);
        assertEquals("one", testList.get(0));
    }

    @Test
    public void addToListWithOneElement() {
        testList.add("one");
        testList.add("two");

        assertTrue(testList.size() != 0);
        assertEquals("two", testList.get(1));

    }

    @Test
    public void getFirstElement() {
        testList.add("one");

        assertEquals("one", testList.get(0));

    }

//    @Test
//    public void getLastElement() {
//        testList.add("one");
//
//        assertEquals("one", testList.get(1));
//    }

    /**
     * Deze testmethode heb ik aangepast zodat deze de getLast() methode gebruikt die ik heb geimplementeerd.
     */
    @Test
    public void getLastElement() {
        testList.add("one");

        assertEquals("one", testList.getLast());
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeIndexIsNotAllowed() {
        testList.get(-1);
    }

    @Test
    public void deleteFromListWithSingleElement() {
        testList.add("one");
        testList.delete("one");

        assertEquals(0, testList.size());
    }

    @Test
    public void deleteFromListWithMultipleOccurences() {
        testList.add("one");
        testList.add("one");
        testList.delete("one");

        assertEquals(0, testList.size());
    }

    @Test
    public void deleteFromListWithMultipleOccurrencesAndMoreElements() {
        testList.add("one");
        testList.add("two");
        testList.add("one");
        testList.delete("one");

        assertEquals(1, testList.size());
    }

    @Test
    public void sizeOfEmptyList() {
        assertEquals(0, testList.size());
    }

    @Test
    public void sizeOfListWithOneElement() {
        testList.add("one");
        assertEquals(1, testList.size());
    }


    /* EXTRA UNIT TESTS */

    /**
     * Deze methode checkt na het verwijderen van twee verschillende elementen, de grootte van de lijst.
     */
    @Test
    public void threeElementsInList() {
        testList.add("one");
        testList.add("one");
        testList.add("two");
        testList.add("three");
        testList.add("four");
        testList.add("five");
        testList.delete("one");
        testList.delete("three");

        assertEquals(3, testList.size());
    }

    /**
     * Deze methode test de isEmpty() methode, die true of false returnt wanneer de lijst leeg is.
     */
    @Test
    public void isEmpty() {
        testList.add("one");
        testList.add("one");
        testList.add("one");
        testList.add("one");
        testList.delete("one");
        assertEquals(true, testList.isEmpty());
    }

    /**
     * Deze methode checkt het laatste element na het verwijderen van elementen.
     */
    @Test
    public void getLastAfterDelete() {
        testList.add("one");
        testList.add("two");
        testList.add("one");
        testList.add("three");
        testList.delete("three");
        assertEquals("one", testList.getLast());
    }

    /**
     * Deze methode test of er een exception wordt meegegeven wanneer je zoekt naar een element in een lege lijst.
     */
    @Test(expected = NoSuchElementException.class)
    public void getFromEmptyList() {
        testList.get(2);
    }

    /**
     * Deze methode test het zoeken van bepaalde elementen op een index, na het verwijderen van andere elementen.
     */
    @Test
    public void getAt() {
        testList.add("one");
        testList.add("two");
        testList.add("one");
        testList.add("three");
        testList.delete("one");
        assertEquals("two", testList.get(0));
    }

}
