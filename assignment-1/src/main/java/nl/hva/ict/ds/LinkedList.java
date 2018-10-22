package nl.hva.ict.ds;

import java.util.NoSuchElementException;

/**
 * A linked list that adds elements to the end of the list and that retrieves elements from the end of the list as fast
 * as from the head of the list.
 * For example if a list contains 1000 elements the time needed to retrieve element at index 999 should be (almost) the
 * same as is needed for retrieving element at index 0. Retrieving element at index 800 should take (almost) the same
 * time as retrieving element at index 199.
 * When deleting an element all elements with the same value are deleted. So when deleting "don't" from a list that
 * contains<br/>
 * {"I", "don't", "like", "Datastructures", "as", "much", "as", "I", "don't", "like", "Sorting", "and", "Searching"}
 * this should result in a list containing:<br/>
 * {"I", "like", "Datastructures", "as", "much", "as", "I", "like", "Sorting", "and", "Searching"}
 *
 * @param <T> defines the type (class) that is stored in this list.
 */
public class LinkedList<T> {
    private class Node {
        private T value;
        private Node next;

        private Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node head; // De eerste Node
    private Node tail; // De laatste Node
    private int size = 0; // De grootte van de lijst, word geïncrement bij elke add/remove

    /**
     * @return head, die null is wanneer de lijst leeg is.
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Adds a new element to the end of this list. The performance of this method is guaranteed to be constant, in other
     * words, the number of elements already stored in the list should have no influence on the time needed to add a new
     * element.
     *
     * @param element the element that is added to the list.
     */
    public void add(T element) {
        Node node = new Node(element, null);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    /**
     * Returns an element from the list. If the index is negative or the element does not exists
     * an IllegalArgumentException is throw containing the reason in the message.
     *
     * @param index the index, counted from the first element, of the element that must be returned.
     */
    public T get(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Negative index is not allowed.");
        }

        if (head == null) throw new NoSuchElementException("List is empty.");

        Node current = head;
        for (int i = 0; i < index; i++) {
            if (current.next == null) {
                throw new IndexOutOfBoundsException("Reached end of list.");
            }
            current = current.next;
        }
        return current.value;
    }

    /**
     * @return tail, het laatste element in de lijst.
     */
    public T getLast() {
        if (head == null) throw new NoSuchElementException("List is empty.");
        return tail.value;
    }

    /**
     * Deletes the element (if it exists) from the list. In case of multiple occurrences all the occurrences are
     * deleted.
     *
     * @param element the element to delete.
     */
    public void delete(T element) {
        recursiveDelete(element, head);
    }

    /**
     * Recursieve delete, we gaan net zo lang door totdat we aan het einde van de lijst
     * aan zijn gekomen. Dit moet zo gedaan worden omdat een element vaker voor kan komen in de lijst.
     *
     * @param target,  het element dat wordt verwijderd.
     * @param current, Node die wordt meegegeven om door de lijst heen te stappen.
     */
    private void recursiveDelete(T target, Node current) {
        Node temp = current;
        tail = current;

        if (head == null) {
            return;
        }

        if (current.value == target) {
            if (head.next == null) {
                head = null;
            } else {
                head = current.next;
                current = current.next;
            }
            size--;
            recursiveDelete(target, current);
        }

        if (current.next == null) {
            return;
        }

        if (current.next.value == target) {
            current = current.next;
            temp.next = current.next;
            size--;
        }

        if (current.next != null) {
            recursiveDelete(target, current.next);
        }
    }

    /**
     * Visualiseert hoe de LinkedList er uit komt te zien.
     * Voorbeeld: Node1 --> Node2 --> null
     */
    public void print() {
        Node node = head;

        if (head == null) {
            System.out.println("null");
        } else {
            while (null != node) {
                System.out.print(node.value + " --> ");
                node = node.next;
            }
            System.out.println("null");
        }
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in the list.
     */
    public int size() {
        return size;
    }

}
