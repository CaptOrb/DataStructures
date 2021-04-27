public class LinkedStack<E> implements Stack<E> {

	private SinglyLinkedList<E> list = new SinglyLinkedList<>();
	public LinkedStack(){}


	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}

	@Override
	public void push(E e) {
		list.addFirst(e);
	}

	@Override
	public E top() {
		return list.first();
	}

	@Override
	public E pop() {
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

		LinkedStack<String> sll = new LinkedStack<>();

		String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

		for (String s : alphabet) {
			sll.push(s);
		}

		System.out.println(sll);

		System.out.println("The top element of the stack is: " + sll.top());
		System.out.println(sll.pop() + " has been removed from the stack.");
		System.out.println("The new top element of the stack is:" + sll.top());

	}

}
