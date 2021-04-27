public class ArrayStack<E> implements Stack<E> {
    private static final int CAPACITY = 1000;
    private E[] data;
    private int t = -1; // index of top element

    public ArrayStack() {
        this(CAPACITY);
    }

    public ArrayStack(int capacity) {
        data = (E[]) new Object[capacity];
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

    // return element at top of stack
    @Override
    public E top() {
        if (isEmpty()) return null;
        return data[t];
    }

    // pop the top element
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

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append("[");
        for (int i = data.length - 1; i >= 0; i--) {

            if (data[i] != null)
                s.append(data[i]).append(", ");
        }
        if (s.length() > 2)
            s.delete(s.length() - 2, s.length());
        s.append("]");
        return s.toString();
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

        ArrayStack<Integer> s = new ArrayStack<>();
        for (int i = 0; i < 10; ++i)
            s.push(i);
        System.out.println(s.toString());

        sll.printStack();

    }

}
