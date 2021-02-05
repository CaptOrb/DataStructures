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
            for (int j = 0; j < i; j++) {
                curr = curr.getNext();
            }

            // return the data in index i
            return curr.getData();
        }
    }

    @Override
    public E set(int i, E e) throws IndexOutOfBoundsException {
        return null;
    }

    @Override
    public void add(int i, E e) {
        if (size == 0 || i == 0) {
            addFirst(e);
        } else if (size == i) {
            addLast(e);
        } else {
            Node<E> curr = tail;
            Node<E> prev = null;

            for (int j = 0; j < i; j++)
            {
                prev = curr;
                curr = curr.getNext();
            }

            //insert newest element (node)
            Node<E> newest = new Node<E>(e, curr);

            if(prev != null) {
                prev.setNext(newest);
            }
        }
        size++;
    }


    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
        return null;
    }

    private class CircularlyLinkedListIterator<E> implements Iterator<E> {

        Node<E> current = (Node<E>) tail;

        int count;

        @Override
        public boolean hasNext() {
            System.out.println(count);
            return count < size;
        }

        @Override
        public E next() {
            E data = (E) current.getData();
            current = current.next;
            size++;
            return data;
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
        // TODO
        return get(0);
    }

    /**
     * Returns (but does not remove) the last element of the list
     *
     * @return element at the back of the list (or null if empty)
     */
    public E last() {              // returns (but does not remove) the last element
        // TODO
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

        if(isEmpty()){
            tail = new Node<E>(e, null);
            tail.setNext(tail); // link to itself circularly
        }else{
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
        for (E item : this)
            s.append(item + " ");
        return s.toString();
    }


    public static void main(String[] args) {
        //ArrayList<String> all;
        //LinkedList<String> ll;
        CircularlyLinkedList<String> ll = new CircularlyLinkedList<>();


        //ll.add(1, "leo");
        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");



        for (String s : alphabet) {
            ll.addFirst(s);
          //  ll.addLast(s);
        }
       // ll.add(2, "hi");
       // ll.add(1, "leo");
        //System.out.println(ll.toString());

        //System.out.println(ll.first());

       // ll.rotate();
    //    ll.rotate();

        for (String s : ll) {
            System.out.print(s + ", ");
        }

    }
}
