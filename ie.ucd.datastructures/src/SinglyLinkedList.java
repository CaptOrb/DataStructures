
import java.util.Iterator;

/**
 * A basic singly linked list implementation.
 */
public class SinglyLinkedList<E> implements Cloneable, Iterable<E>, List<E> {
    //---------------- nested Node class ----------------

    /**
     * Node of a singly linked list, which stores a reference to its
     * element and to the subsequent node in the list (or null if this
     * is the last node).
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

        public void setData(E data) {
            this.data = data;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    } //----------- end of nested Node class -----------

    // instance variables of the SinglyLinkedList
    private Node<E> head = null; // head node of the list (or null if empty)

    private int size = 0; // number of nodes in the list

    public SinglyLinkedList() {
    }              // constructs an initially empty list

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

        if (head == null) {
            return null;
        }

        Node<E> curr = head;

        // transverse list until we reach i
        for (int j = 0; j < i ; j++) {
            curr = curr.getNext();
        }

        // return the data in index i
        return curr.getData();

    }

    // Replaces the element at index i with e, and returns the old element that was
    // replaced; an error occurs if i is not in range [0, size()-1]
    @Override
    public E set(int i, E e) throws IndexOutOfBoundsException {
        if (i < 0 || i > size - 1) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        } else {

            Node<E> temp = new Node<E>(e, head);
            Node<E> curr = head;

            // transverse list until we reach i
            for (int j = 0; j < i - 1; j++) {
                curr = curr.getNext();
            }

            temp.setNext(curr.getNext().getNext());
            curr.setNext(temp);

            // return the data in index i
            return curr.getNext().getData();
        }
    }

    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException {

        if(i < 0){
            throw new IndexOutOfBoundsException("Cannot add an element to a negative index");
        }
        if (size == 0 || i == 0) {
            addFirst(e);
        } else if (size == i) {
            addLast(e);
        } else {

            Node<E> newNode = new Node<E>(e, null);

            Node<E> prev = head;

            for (int j = 0; j < i - 1; j++) {
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
        if (size == 1) {
            removeFirst();
        } else {
            Node<E> curr = head;

            // start at j = 1 because removing first element is handled above
            for (int j = 1; j <= i; j++) {

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
        }
        return null;
    }

    /**
     * Returns (but does not remove) the first element of the list
     *
     * @return element at the front of the list (or null if empty)
     */
    public E first() {
        return get(0);
    }

    /**
     * Returns the last node of the list
     *
     * @return last node of the list (or null if empty)
     */
    public Node<E> getLast() {
        Node<E> last = head;
        Node<E> next = head.next;

        while (next != null) {
            last = next;
            next = next.getNext();
        }

        return last;
    }

    /**
     * Returns (but does not remove) the last element of the list.
     *
     * @return element at the end of the list (or null if empty)
     */
    public E last() {
        return get(size - 1);
    }

    // update methods

    /**
     * Adds an element to the front of the list.
     *
     * @param e the new element to add
     */
    public void addFirst(E e) {
        head = new Node<E>(e, head);
        size++;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param e the new element to add
     */
    public void addLast(E e) {

        // new node to insert
        Node<E> newest = new Node<E>(e, null);
        Node<E> last = head;

        if (last == null) {
            head = newest;
        } else {
            // iterate until we reach last element in list
            while (last.getNext() != null) {
                last = last.getNext();
            }
            last.setNext(newest);
        }
        size++;
    }

    /**
     * Removes and returns the first element of the list.
     *
     * @return the removed element (or null if empty)
     */
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        } else {

            // the element to be returned
            E answer = head.getData();

            // set new head of list
            head = head.getNext();

            // decrement the size
            size--;
            return answer;
        }
    }

    /**
     * Removes and returns the last element of the list.
     *
     * @return the removed element (or null if empty)
     */
    public E removeLast() {
        if (isEmpty()) {
            return null;
        } else {

            Node<E> last = head;

            while (last.getNext() != null) {
                last = last.getNext();
            }

            // the element to be returned
            E answer = last.getData();

            last.setData(null);

            // decrement the size
            size--;
            return answer;
        }
    }

    public void reverse() {

        // Create a new array stack
        ArrayStack<E> stack = new ArrayStack<E>(size);

        Node<E> curr = head;

        int i = 0;

        // iterate through the linked list
        while (curr != null) {

            // push the element to the stack
            stack.push(curr.getData());

            // Remove the element from the linked list
            remove(i);

            i++;
            curr = curr.getNext();
        }

        i = 0;

        // Pop the element from the stack and add it to the linked list
        while (!stack.isEmpty()) {

            add(i, stack.pop());
            i++;
        }
    }

    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;

        SinglyLinkedList other = (SinglyLinkedList) o;

        // if the linked lists have different sizes
        // they cannot possibly be equal
        if (size != other.size)
            return false;

        Node currA = head;

        Node currB = other.head;

        while (currA != null) {

            // go through every element in both linked lists
            // if we find a value that is not the same in the relevant postition in both LL's
            // we know they aren't equal
            if (!currA.getData().equals(currB.getData()))
                return false;

            currA = currA.getNext();
            currB = currB.getNext();
        }
        return true;
    }

    @SuppressWarnings({"unchecked"})
    public SinglyLinkedList<E> clone() throws CloneNotSupportedException {

        SinglyLinkedList<E> twin = new SinglyLinkedList<E>();
        Node<E> temp = head;
        while (temp != null) {
            twin.addLast(temp.getData());
            temp = temp.getNext();
        }
        return twin;
    }

    /**
     * Produces a string representation of the contents of the list.
     * This exists for debugging purposes only.
     */
    @Override
    public String toString() {
        Node<E> curr = head;

        StringBuilder s = new StringBuilder();
        s.append("[");

        while (curr != null) {
            s.append(curr.getData());
            if (curr.getNext() != null) {
                s.append(", ");
            }
            curr = curr.getNext();
        }
        s.append("]");
        return s.toString();
    }

    private class SinglyLinkedListIterator<E> implements Iterator<E> {

        Node<E> current = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E data = (E) current.getData();
            current = current.next;
            return data;
        }
    }

    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    public static void main(String[] args) throws CloneNotSupportedException {

        SinglyLinkedList<String> sll = new SinglyLinkedList<String>();

        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

        for (String s : alphabet) {
            sll.addFirst(s);
            sll.addLast(s);
        }
        System.out.println(sll.toString());

        for (String s : sll) {
            System.out.print(s + ", ");
        }

        System.out.println();

        sll.reverse();

    }
}

