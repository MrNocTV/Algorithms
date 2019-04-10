package Linear;

public class DoubleLinkedList<T> {

	class DNode<T> {
		DNode<T> prev;
		DNode<T> next;
		T val;

		public DNode(T val) {
			this.val = val;
			this.prev = this.next = null;
		}

	}

	private DNode<T> head;
	private DNode<T> tail;
	private int size;

	public DoubleLinkedList() {
		head = tail = null;
		size = 0;
	}

	public int length() {
		return size;
	}

	public DNode<T> getFirst() {
		return head;
	}

	public DNode<T> getLast() {
		return tail;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void insertFirst(T val) {
		if (head == null) {
			head = tail = new DNode<T>(val);
		} else {
			DNode<T> node = new DNode<T>(val);
			node.next = head;
			head.prev = node;
			head = node;
		}

		++size;
	}

	public void insertLast(T val) {
		if (head == null) {
			head = tail = new DNode<T>(val);
		} else {
			DNode<T> node = new DNode<T>(val);
			node.prev = tail;
			tail.next = node;
			tail = node;
		}
		++size;
	}

	public DNode<T> removeLast() {
		if (isEmpty())
			return null;
		if (head == tail) {
			DNode<T> temp = head;
			head = tail = null;
			return temp;
		}
		DNode<T> temp = tail;
		DNode<T> nextToLast = tail.prev;
		nextToLast.next = null;
		tail.prev = null;
		tail = nextToLast;

		return temp;
	}

	@Override
	public String toString() {
		DNode<T> temp = head;
		StringBuilder sb = new StringBuilder();
		while (temp != null) {
			sb.append(temp.val + (temp.next == null ? "" : " "));
			temp = temp.next;
		}

		return sb.toString();
	}
}
