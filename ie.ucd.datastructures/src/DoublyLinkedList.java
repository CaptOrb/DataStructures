import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> {

    //---------------- nested Node class ----------------

    /**
     * Node of a doubly linked list, which stores a reference to its
     * element and to both the previous and next node in the list.
     */
    private static class Node<E> {
        private E data;

        private Node<E> prev;
        private Node<E> next;

        public Node(E data, Node<E> prev, Node<E> next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setNext(Node<E> n) {
            next = n;
        }

        public void setPrev(Node<E> p) {
            prev = p;
        }
    } //----------- end of nested Node class -----------

    // instance variables of the DoublyLinkedList
    /**
     * Sentinel node at the beginning of the list
     */
    private final Node<E> header;                    // header sentinel

    /**
     * Sentinel node at the end of the list
     */
    private final Node<E> trailer;                   // trailer sentinel

    /**
     * Number of elements in the list (not including sentinels)
     */
    private int size = 0;                      // number of elements in the list

    /**
     * Constructs a new empty list.
     */
    public DoublyLinkedList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }

    // public accessor methods

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
            Node<E> curr = getNode(i);

            // return the data in index i
            return curr.getData();
        }
    }


    public Node<E> getNode(int i) throws IndexOutOfBoundsException {

        if (header == null) {
            return null;
        }

        Node<E> curr = header;

        // transverse list until we reach i
        for (int j = 0; j <= i; j++) {
            curr = curr.getNext();
        }

        return curr;

    }

    @Override
    public E set(int i, E e) throws IndexOutOfBoundsException {
        if (i < 0 || i > size - 1) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        } else {

            Node<E> oldNode = getNode(i);

            E ret = oldNode.getData();

            System.out.println(ret);

            // replace data in old node
            oldNode.setData(e);

            return ret;
        }
    }

    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException {
        if (i == 0 && isEmpty()) {
            addFirst(e);
        } else if (size == i) {
            addLast(e);
        } else {

            Node<E> newNode = new Node<E>(e, null, null);

            Node<E> curr = getNode(i);
            Node<E> prevNode = curr.getPrev();

            // next to the prev node is the node we are inserting
            prevNode.setNext(newNode);

            // after the new node is the node that used to be at index i
            newNode.setNext(curr);

            // the node before the new one
            newNode.setPrev(prevNode);
        }
        size++;
    }

    @Override
    public E remove(int i) throws IndexOutOfBoundsException {

        if (i < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (i == 0) {
            removeFirst();
            size--;
        } else if (size == i) {
            removeLast();
            size--;
        } else {

            Node<E> curr = getNode(i);

            E removed = curr.getData();

            // We need to find the node before and after the node we are removing
            Node<E> previous = curr.getPrev();
            Node<E> next = curr.getNext();

            // the previous node should point to the node after the node that was removed
            previous.setNext(next);

            // the next node should point to the node before the node that was removed
            next.setPrev(previous);

            size--;
            return removed;
        }
        return null;
    }

    private class DoublyLinkedListIterator<E> implements Iterator<E> {

        Node<E> current = (Node<E>) header.getNext();

        @Override
        public boolean hasNext() {
            return current.getData() != null;
        }

        @Override
        public E next() {
            E data = current.getData();
            current = current.next;
            return data;
        }
    }

    public Iterator<E> iterator() {
        return new DoublyLinkedListIterator<>();
    }

    /**
     * Returns (but does not remove) the first element of the list.
     *
     * @return element at the front of the list (or null if empty)
     */
    public E first() {
        return header.getNext().getData();
    }

    /**
     * Returns (but does not remove) the last element of the list.
     *
     * @return element at the end of the list (or null if empty)
     */
    public E last() {
        return trailer.getPrev().getData();
    }

    // public update methods

    /**
     * Adds an element to the front of the list.
     *
     * @param e the new element to add
     */
    public void addFirst(E e) {
        addBetween(e, header, header.getNext());
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param e the new element to add
     */
    public void addLast(E e) {
        addBetween(e, trailer.getPrev(), trailer);
    }

    /**
     * Removes and returns the first element of the list.
     *
     * @return the removed element (or null if empty)
     */
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        return remove(header.getNext());
    }

    /**
     * Removes and returns the last element of the list.
     *
     * @return the removed element (or null if empty)
     */
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        return remove(trailer.getPrev());
    }

    // private update methods

    /**
     * Adds an element to the linked list in between the given nodes.
     * The given predecessor and successor should be neighboring each
     * other prior to the call.
     *
     * @param predecessor node just before the location where the new element is inserted
     * @param successor   node just after the location where the new element is inserted
     */
    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        // Create and link a new node
        Node<E> newest = new Node<>(e, predecessor, successor);

        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }

    /**
     * Removes the given node from the list and returns its element.
     *
     * @param node the node to be removed (must not be a sentinel)
     */
    private E remove(Node<E> node) {

        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return node.getData();
    }


    /**
     * Produces a string representation of the contents of the list.
     * This exists for debugging purposes only.
     */
    public String toString() {
        Node<E> curr = header.getNext();

        StringBuilder s = new StringBuilder();
        s.append("[");

        while (curr.getData() != null) {
            s.append(curr.getData());
            if (curr.getNext().getData() != null) {
                s.append(", ");
            }
            curr = curr.getNext();
        }
        s.append("]");
        return s.toString();
    }

    public static void main(String[] args) {

        DoublyLinkedList<String> ll = new DoublyLinkedList<>();

        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

        for (String s : alphabet) {
            ll.addFirst(s);
            ll.addLast(s);
        }

        System.out.println(ll.toString());

        for (String s : ll) {
            System.out.print(s + ", ");
        }
    }
} //----------- end of DoublyLinkedList class -----------
