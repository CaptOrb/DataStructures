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

	public static void main(String[] args) {

		LinkedStack<String> sll = new LinkedStack<>();

		String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

		for (String s : alphabet) {
			sll.push(s);
		}

		System.out.println(sll.top());
		sll.pop();

		System.out.println(sll.top());

	}

}
