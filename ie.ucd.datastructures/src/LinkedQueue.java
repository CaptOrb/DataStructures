public class LinkedQueue<E> implements Queue<E> {

    private SinglyLinkedList<E> list = new SinglyLinkedList<>();

    public LinkedQueue() {}

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
            s.delete(s.length() - 2 , s.length());
        s.append("]");
        return s.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> ex = new ArrayQueue<Integer>(1000);

        ex.enqueue(5);

        ex.enqueue(10);

        System.out.println("At the start of the queue is " + ex.first());

        ex.dequeue();

        System.out.println("t the start of the queue is  " + ex.first());
    }
}
