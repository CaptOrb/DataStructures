public class LinkedDeque<E> implements Deque<E> {

	private DoublyLinkedList<E> list = new DoublyLinkedList<>();

	public LinkedDeque(){}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public E first() {
		return list.first();
	}

	@Override
	public E last() {
		return list.last();
	}

	@Override
	public void addFirst(E e) {
		list.addFirst(e);
	}

	@Override
	public void addLast(E e) {
		list.addLast(e);

	}

	@Override
	public E removeFirst() {
		return list.removeFirst();
	}

	@Override
	public E removeLast() {
		return list.removeLast();
	}

	public static void main(String[] args) {
		LinkedDeque<Integer> ex = new LinkedDeque<>();

		ex.addFirst(5);

		ex.addLast(20);

		System.out.println("At the start of the queue is " + ex.first());

		ex.addFirst(10);

		System.out.println("At the start of the queue is  " + ex.first());

		System.out.println("At the end of the queue is  " + ex.last());

	}

}
