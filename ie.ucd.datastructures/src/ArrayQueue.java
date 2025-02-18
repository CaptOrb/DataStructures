public class ArrayQueue<E> implements Queue<E> {

    private static final int CAPACITY = 1000;

    private E[] data;
    private int front = 0;
    private int numElements = 0;

    public ArrayQueue() {
        this(CAPACITY);
    }

    public ArrayQueue(int capacity) {
        data = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return numElements;
    }

    @Override
    public boolean isEmpty() {
        return numElements == 0;
    }

    @Override
    public void enqueue(E e) {
        if (numElements == data.length) {
            throw new IllegalStateException("Queue is full");
        }
        int index = (front + numElements) % data.length;
        data[index] = e;
        numElements++;
    }

    @Override
    public E first() {
        if (isEmpty()) {
            return null;
        }
        return data[front];
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }

        // we need to return the current element at the start of the queue
        E answer = data[front];
        data[front] = null;

        // use modular arithmetic to get the index of the new first element in the queue
        front = (front + 1) % data.length;

        // decrement the number of elements in the queue
        numElements--;

        // Return the element that was removed from the queue
        return answer;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append("[");
        for (E p : data) {
            if (p != null)
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

        System.out.println("At the first of the queue is " + ex.first());

        System.out.println("Deque 5" + ex.dequeue());

        System.out.println("At the first of the queue is " + ex.first());

    }
}
