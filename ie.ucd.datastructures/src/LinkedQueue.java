public class LinkedQueue<E> implements Queue<E> {

    private SinglyLinkedList<E> list = new SinglyLinkedList<>();

    public LinkedQueue() {
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        list.addLast(e);
    }

    @Override
    public E first() {
        return list.first();
    }

    @Override
    public E dequeue() {
        return list.removeFirst();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append("[");
        for (E p : list) {
            s.append(p).append(", ");
        }
        if (s.length() > 2)
            s.delete(s.length() - 2, s.length());
        s.append("]");
        return s.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> ex = new ArrayQueue<Integer>(1000);

        System.out.println("Enqueuing 5 ");
        ex.enqueue(5);

        System.out.println("Enqueuing 10 ");
        ex.enqueue(10);

        System.out.println("\n\nAt the start of the queue is: " + ex.first());

        System.out.println(ex.dequeue() + " has been removed from the queue");

        System.out.println("At the start of the queue is: " + ex.first());

        System.out.println("\nEnqueuing 50 ");
        ex.enqueue(50);

        System.out.println("The size of the queue is " + ex.size());
    }
}
