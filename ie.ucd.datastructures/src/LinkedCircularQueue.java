/**
 * Realization of a circular FIFO queue as an adaptation of a
 * CircularlyLinkedList. This provides one additional method not part of the
 * general Queue interface. A call to rotate() is a more efficient simulation of
 * the combination enqueue(dequeue()). All operations are performed in constant
 * time.
 */

public class LinkedCircularQueue<E> implements Queue<E> {

    private final CircularlyLinkedList<E> cll = new CircularlyLinkedList<>();

    @Override
    public int size() {
        return cll.size();
    }

    @Override
    public boolean isEmpty() {
        return cll.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        cll.addLast(e);
    }

    @Override
    public E first() {
        return cll.first();
    }

    @Override
    public E dequeue() {
        return cll.removeFirst();
    }

    public void rotate() {
        cll.rotate();
    }

    public String toString() {
        return cll.toString();
    }

    public void outputIsEmpty() {
        if (isEmpty()) {
            System.out.println("The queue is empty");
        } else {
            System.out.println("The queue is not empty");
        }
    }

    public static void main(String[] args) {

        LinkedCircularQueue<Integer> s = new LinkedCircularQueue<>();

        s.outputIsEmpty();

        System.out.println("Adding elements to queue");

        for (int i = 0; i < 10; ++i)
            s.enqueue(i);

        s.outputIsEmpty();

        System.out.println();

        System.out.println("Content of Queue : " + s);
        System.out.println("Queue Size : " + s.size());
        System.out.println("The top element of the queue is : " + s.first());
        System.out.println();

        s.dequeue();
        System.out.println("After deque, top element is now : " + s.first());
        System.out.println("Content of Queue is now : " + s);

        System.out.println("Now the queue has a size of : " + s.size());
        System.out.println();

        s.rotate();
        System.out.println("Top of queue after rotation : " + s.first());
        System.out.println("Size after rotation : " + s.size());
        System.out.println("Content of Queue is now : " + s);

    }
}
