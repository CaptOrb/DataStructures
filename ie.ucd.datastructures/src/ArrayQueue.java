public class ArrayQueue<E> implements Queue<E> {

    private static final int CAPACITY = 1000;

    private E[] data;
    private int front = 0;
    private int numElements = 0;

    public ArrayQueue() {
        this(CAPACITY);
    }

    public ArrayQueue(int capacity) {
        data = (E[]) new Object[CAPACITY];
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

		// decrement the number of elements in thr queue
		numElements--;

		// Return the element that was removed from the queue
		return answer;
    }



    public static void main(String[] args) {
        ArrayQueue<Integer> ex = new ArrayQueue<Integer>(1000);

        ex.enqueue(5);

		ex.enqueue(10);

        System.out.println("At the first is " + ex.first());

        ex.dequeue();

		System.out.println("At the first is " + ex.first());

    }


}
