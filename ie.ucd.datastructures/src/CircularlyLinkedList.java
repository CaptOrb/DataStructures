import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {
    //---------------- nested Node class ----------------

    /**
     * Singly linked node, which stores a reference to its element and
     * to the subsequent node in the list.
     */
    private static class Node<E> {

        private E data;

        private Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }

        public void setData(E e) {
            this.data = e;
        }
    } //----------- end of nested Node class -----------

    // instance variables of the CircularlyLinkedList
    /**
     * The designated cursor of the list
     */
    private Node<E> tail = null;                  // we store tail (but not head)

    /**
     * Number of nodes in the list
     */
    private int size = 0;                         // number of nodes in the list

    /**
     * Constructs an initially empty list.
     */
    public CircularlyLinkedList() {
    }             // constructs an initially empty list

    // access methods

    /**
     * Returns the number of elements in the linked list.
     *
     * @return number of elements in the linked list
     */
    public int size() {
        return size;
    }

    /**
     * Tests whether the linked list is empty.
     *
     * @return true if the linked list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i > size - 1) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        } else {

            Node<E> curr = tail;

            // transverse list until we reach i
            for (int j = 0; j <= i; j++) {
                curr = curr.getNext();
            }

            // return the data in index i
            return curr.getData();
        }
    }

    Node<E> getNode(int i) throws IndexOutOfBoundsException {

        if (tail == null) {
            return null;
        }

        Node<E> curr = tail;

        // transverse list until we reach i
        for (int j = 0; j <= i; j++) {
            curr = curr.getNext();
        }

        // return the data in index i
        return curr;

    }

    // Replaces the element at index i with e, and returns the old element that was
    // replaced; an error occurs if i is not in range [0, size()-1]
    @Override
    public E set(int i, E e) throws IndexOutOfBoundsException {
        if (i < 0 || i > size - 1) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        Node<E> oldNode = getNode(i);

        E ret = oldNode.getData();

        oldNode.setData(e);

        // return the data in index i
        return ret;
    }

    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException {

        if (i < 0 || i > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (size == 0 || i == 0) {
            addFirst(e);
        } else if (size == i) {
            addLast(e);
        } else {

            Node<E> newNode = new Node<E>(e, null);

            Node<E> prev = tail;

            for (int j = 0; j <= i; j++) {
                prev = prev.next;
            }
            // in old index i - replace with new val
            newNode.next = prev.next;
            prev.next = newNode;
        }
        size++;
    }


    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> curr = tail;

        for (int j = 0; j <= i; j++) {

            // have we found the element we want to remove?
            if (i == j) {

                // store the element to be deleted
                Node<E> temp = curr.getNext();

                // shift all subsequent elements in the list one position closer to the front.
                curr.setNext(curr.getNext().getNext());
                size--;
                // return the deleted element
                return temp.getData();
            } else {
                // we have not found the element we wish to remove
                // so check if the next element in the SLL is the element we wish to remove
                curr = curr.getNext();
            }
        }

        return null;
    }

    private class CircularlyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr;
        int count;

        private CircularlyLinkedListIterator() {
            curr = (Node<E>) tail.getNext();
            count = 0;
        }

        public boolean hasNext() {
            return count < size;
        }

        @Override
        public E next() {
            E result = curr.getData();
            curr = curr.getNext();
            count++;
            return result;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator<>();
    }

    /**
     * Returns (but does not remove) the first element of the list
     *
     * @return element at the front of the list (or null if empty)
     */
    public E first() {             // returns (but does not remove) the first element
        return get(0);
    }

    /**
     * Returns (but does not remove) the last element of the list
     *
     * @return element at the back of the list (or null if empty)
     */
    public E last() {              // returns (but does not remove) the last element
        return get(size - 1);
    }

    // update methods

    /**
     * Rotate the first element to the back of the list.
     */
    public void rotate() {         // rotate the first element to the back of the list
        tail = tail.getNext();
    }

    /**
     * Adds an element to the front of the list.
     *
     * @param e the new element to add
     */
    public void addFirst(E e) {
        // adds element e to the front of the list

        if (isEmpty()) {
            tail = new Node<E>(e, null);
            tail.setNext(tail); // link to itself circularly
        } else {
            Node<E> newest = new Node<>(e, tail.getNext());
            tail.setNext(newest);
        }
        size++;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param e the new element to add
     */
    public void addLast(E e) {                 // adds element e to the end of the list
        addFirst(e);                           // insert new element at front of list
        tail = tail.getNext();                 // the new element becomes the tail
    }

    /**
     * Removes and returns the first element of the list.
     *
     * @return the removed element (or null if empty)
     */
    public E removeFirst() {                   // removes and returns the first element
        return remove(0);
    }

    /**
     * Produces a string representation of the contents of the list.
     * This exists for debugging purposes only.
     */

    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append("[");
        for (E item : this) {
            s.append(item).append(", ");
        }
        if (s.length() > 2)
            s.delete(s.length() - 2, s.length());

        s.append("]");
        return s.toString();
    }

    public static void main(String[] args) {
        CircularlyLinkedList<String> ll = new CircularlyLinkedList<>();

        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

        for (String s : alphabet) {
            ll.addFirst(s);
            ll.addLast(s);
        }

        System.out.println(ll.first());

        System.out.println(ll.toString());

        System.out.println("Removed: " + ll.removeFirst());

        System.out.println(ll.toString() + "\n\n");

        ll.rotate();
        ll.rotate();

        for (String s : ll) {
            System.out.print(s + ", ");
        }
    }
}
