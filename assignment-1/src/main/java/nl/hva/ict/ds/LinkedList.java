package nl.hva.ict.ds;

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

    private Node head;
    private Node tail;
    private int size = 0;

    /**
     * Adds a new element to the end of this list. The performance of this method is guaranteed to be constant, in other
     * words, the number of elements already stored in the list should have no influence on the time needed to add a new
     * element.
     *
     * @param element the element that is added to the list.
     */
    public void add(T element) {

        Node n = new Node(element, null);

        if (head == null) {
            head = n;
        } else {
            Node n2 = head;

            while (n2.next != null) {
                n2 = n2.next;
            }
            n2.next = n;
        }
        size++;
    }

    /**
     * Returns an element from the list. If the index is negative or the element does not exists
     * an IllegalArgumentException is throw containing the reason in the message.
     * @param index the index, counted from the first element, of the element that must be returned.
     */
    public T get(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Negative index is not allowed.");
        }

        Node current = head;
        for (int i = 0; i < index;i++) {
            if (current.next == null) {
                throw new IndexOutOfBoundsException("Reached end of list.");
            }
            current = current.next;
        }

        return current.value;
    }

    public T getLast() {
        Node current = head;
        for (int i = 0; i < size(); i++) {
            if (current.next == null) {
                tail = current;
            } else {
                current = current.next;
            }
        }
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

    private void recursiveDelete(T target, Node current) {
        Node prev = current;
        if (current == null) {
            size--;
            return;
        }
        if (current.value == target) {
            prev = current.next;
            size--;
        }
        if (current.next != null) {
            recursiveDelete(target, prev.next);
        } else {
            return;
        }
    }

    public void print() {
        Node node = head;

        while (node.next != null) {
            System.out.println(node.value);
            node = node.next;
        }
        System.out.println(node.value);
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
