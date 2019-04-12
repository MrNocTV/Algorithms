package Week2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Week2Exercise8 {

	static class InputReader {

		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;

		public InputReader(InputStream stream) {
			this.stream = stream;
		}

		public int read() throws Exception {
			if (curChar >= numChars) {
				curChar = 0;
				numChars = stream.read(buf);
				if (numChars <= 0)
					return -1;
			}
			return buf[curChar++];
		}

		public int readInt() throws Exception {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			int res = 0;
			do {
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public String readString() throws Exception {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isSpaceChar(c));
			return res.toString();
		}

		public boolean isSpaceChar(int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
	}

	static class Node {
		int data;
		Node prev;
		Node next;

		public Node(int data) {
			this.data = data;
		}

	}

	static class Container {
		int size = 0;
		private Node first;
		private Node last;
		private Node mid;

		public void add(int x) {
			if (size == 0)
				first = new Node(x);
			else if (size == 1) {
				last = new Node(x);
				first.next = last;
				last.prev = first;
			} else if (size == 2) {
				Node n = new Node(x);
				last.next = n;
				n.prev = last;
				mid = last;
				last = n;
			} else {
				Node n = new Node(x);
				last.next = n;
				n.prev = last;
				last = n;
				if (size % 2 != 0)
					mid = mid.next;
			}

			++size;
		}

		public void take() {
			if (size > 3) {
				Node temp = last;
				last = last.prev;
				temp.prev = null;
				temp.next = null;
				last.next = null;

				if (size % 2 == 0)
					mid = mid.prev;
			} else if (size == 3) {
				last.prev = null;
				mid.next = null;
				last = mid;
				mid = null;
			} else if (size == 2) {
				last.prev = null;
				first.next = null;
				last = null;
			} else if (size == 1) {
				first = null;
			}

			--size;
		}

		public void mum() {
			if (size <= 1) {

			} else if (size == 2) {
				int temp = first.data;
				first.data = last.data;
				last.data = temp;
			} else {
				Node newLast = mid.prev;
				newLast.next = null;
				mid.prev = null;
				last.next = first;
				first.prev = last;

				first = mid;
				if (size % 2 == 0)
					mid = last.next;
				else
					mid = last;
				last = newLast;
			}
		}

		@Override
		public String toString() {
			String first = (this.first != null) ? this.first.data + "\n" : "null\n";
			String mid = (this.mid != null) ? this.mid.data + "\n" : "null\n";
			String last = (this.last != null) ? this.last.data + "\n" : "null\n";
			List<Integer> l = new ArrayList<>();
			Node temp = this.first;
			while (temp != null) {
				l.add(temp.data);
				temp = temp.next;
			}
			String s = l.toString();
			return "first: " + first + "mid: " + mid + "last: " + last + "\n" + s;
		}

		public String getItems() {
			StringBuilder sb = new StringBuilder();
			sb.append(size + "\n");
			Node temp = this.first;
			while (temp != null) {
				sb.append(temp.data + " ");
				temp = temp.next;
			}

			return sb.toString().trim();
		}
	}

	public static void main(String[] args) throws Exception {
		InputReader ir = new InputReader(new FileInputStream("input.txt"));
		FileWriter fw = new FileWriter(new File("output.txt"));
		Container c = new Container();
		int N = ir.readInt();
		int val;
		for (int i = 1; i <= N; ++i) {
			String op = ir.readString();
			if (op.charAt(0) == 't') {
				c.take();
			} else if (op.charAt(0) == 'm') {
				c.mum();
			} else {
				val = ir.readInt();
				c.add(val);
			}
		}

		fw.write(c.getItems() + "\n");
		fw.close();

	}
}
