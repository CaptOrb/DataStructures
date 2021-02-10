public class ArrayStack<E> implements Stack<E> {
    private static final int CAPACITY = 1000;
    private E[] data;
    private int t = -1; // index of top element

    public ArrayStack() {
        this(CAPACITY);
    }

    public ArrayStack(int capacity) {
        data = (E[]) new Object[CAPACITY];
    }

    @Override
    public int size() {
        return (t + 1);
    }

    @Override
    public boolean isEmpty() {
        return t == -1;
    }

    @Override
    public void push(E e) {
        if (size() == data.length) throw new IllegalStateException("Stack is full");

        // we increment t before storing new element
        data[++t] = e;
    }

    @Override
    public E top() {
        if (isEmpty()) return null;
        return data[t];
    }

    @Override
    public E pop() {
        if (isEmpty()) return null;
        E removed = data[t];
        data[t] = null;
        t--;
        return removed;
    }

    public void printStack() {
        for (int i = 0; i < size(); i++) {
            System.out.print(data[i] + ", ");
        }
    }

    public static void main(String[] args) {

        ArrayStack<String> sll = new ArrayStack<>();

        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

        for (String s : alphabet) {
            sll.push(s);
        }

        System.out.println(sll.top());
        sll.pop();

        System.out.println(sll.top());

        sll.printStack();

    }

}
