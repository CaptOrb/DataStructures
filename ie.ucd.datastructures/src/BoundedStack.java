public class BoundedStack<E> implements Stack<E> {

	private int maxSize;

	private SinglyLinkedList<E> list = new SinglyLinkedList<>();
	public BoundedStack(int maxSize){
		this.maxSize = maxSize;
	}


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
		if(size() > maxSize ){
			throw new IllegalStateException("Stack is full");
		} else
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

		BoundedStack<String> sll = new BoundedStack<>(26);

		String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

		for (String s : alphabet) {
			sll.push(s);
		}

		System.out.println(sll.top());
		sll.pop();

		System.out.println(sll.top());

	}

}
