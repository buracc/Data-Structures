package nl.hva.ict.ds;

import org.junit.Before;
import org.junit.Test;

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
}
