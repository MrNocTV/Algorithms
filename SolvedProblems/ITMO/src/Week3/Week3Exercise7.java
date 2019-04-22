package Week3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Week3Exercise7 {

	static class Scan {
		private byte[] buf = new byte[1024];
		private int index;
		private InputStream in;
		private int total;

		public Scan(InputStream in) {
			this.in = in;
		}

		private int next() throws IOException {
			if (total < 0)
				throw new InputMismatchException();
			if (index >= total) {
				index = 0;
				total = in.read(buf);
				if (total <= 0)
					return -1;
			}
			return buf[index++];
		}

		public int nextInt() throws IOException {
			int integer = 0;
			int n = next();
			while (isWhiteSpace(n))
				n = next();
			int neg = 1;
			if (n == '-') {
				neg = -1;
				n = next();
			}
			while (!isWhiteSpace(n)) {
				if (n >= '0' && n <= '9') {
					integer *= 10;
					integer += n - '0';
					n = next();
				} else
					throw new InputMismatchException();
			}
			return neg * integer;
		}

		private boolean isWhiteSpace(int n) {
			if (n == ' ' || n == '\n' || n == '\r' || n == '\t' || n == -1)
				return true;
			return false;
		}
	}

	static int[] compress(int[] arr, int max, int[] compress2Real) {
		Arrays.sort(arr);
		int[] real2Compress = new int[max + 1];

		for (int i = 0; i < arr.length; ++i) {
			real2Compress[arr[i]] = i;
			compress2Real[i] = arr[i];
		}

		return real2Compress;
	}

	static class Query {
		int L;
		int R;
		int D;

		public Query(int D, int L, int R) {
			this.D = D;
			this.L = L;
			this.R = R;
		}
	}

	static class Node {
		BigInteger value, lazy;
		Node left;
		Node right;
		long from;
		long to;

		Node(int B) {
			from = 1;
			to = B + 1;
			value = BigInteger.ZERO;
			lazy = BigInteger.ZERO;
			left = null;
			right = null;
		}

		void extend() {
			if (left == null) {
				left = new Node(1);
				right = new Node(1);
				left.from = from;
				left.to = (from + to) >> 1;
				right.from = left.to + 1;
				right.to = to;
			}
		}
	}

	static Node root;

	static void update(Node node, long left, long right, long value) {
		if (node.lazy.compareTo(BigInteger.ZERO) != 0) {
			node.value = node.value
					.add((BigInteger.valueOf(node.to).subtract(BigInteger.valueOf(node.from)).add(BigInteger.ONE))
							.multiply(node.lazy));
			if (node.from != node.to) {
				node.extend();
				node.left.lazy = node.left.lazy.add(node.lazy);
				node.right.lazy = node.right.lazy.add(node.lazy);
			}
			node.lazy = BigInteger.ZERO;
		}

		if (node.from > node.to || node.from > right || node.to < left)
			return;
		if (node.from >= left && node.to <= right) {
			node.value = node.value
					.add((BigInteger.valueOf(node.to).subtract(BigInteger.valueOf(node.from)).add(BigInteger.ONE))
							.multiply(BigInteger.valueOf(value)));
			System.out.println(node.value + " " + value);
			if (node.from != node.to) {
				node.extend();
				node.left.lazy = node.left.lazy.add(BigInteger.valueOf(value));
				node.right.lazy = node.right.lazy.add(BigInteger.valueOf(value));
			}
			return;
		}
		node.extend();
		update(node.left, left, right, value);
		update(node.right, left, right, value);
		node.value = node.left.value.add(node.right.value);
	}

	static BigInteger query(Node node, long left, long right) {
		if (node.from > node.to || node.from > right || node.to < left)
			return BigInteger.ZERO;
		if (node.lazy.compareTo(BigInteger.ZERO) != 0) {
			node.value = node.value
					.add((BigInteger.valueOf(node.to).subtract(BigInteger.valueOf(node.from)).add(BigInteger.ONE))
							.multiply(node.lazy));
			node.extend();
			node.left.lazy = node.left.lazy.add(node.lazy);
			node.right.lazy = node.right.lazy.add(node.lazy);
			node.lazy = BigInteger.ZERO;
		}

		if (node.from >= left && node.to <= right)
			return node.value;
		BigInteger q1, q2;
		node.extend();
		q1 = query(node.left, left, right);
		q2 = query(node.right, left, right);
		return q1.add(q2);
	}

	public static void main(String[] args) throws IOException {
		Scan scan = new Scan(new FileInputStream(new File("input.txt")));
		BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
		int B = scan.nextInt();
		int N = scan.nextInt();
		root = new Node(B);
		long D, L, R;

		for (int i = 0; i < N; ++i) {
			D = scan.nextInt();
			L = scan.nextInt();
			R = scan.nextInt();
			update(root, L, R, D);
		}

		int M = scan.nextInt();
		StringBuilder sb = new StringBuilder();
		long X, Y;
		for (int i = 0; i < M; ++i) {
			X = scan.nextInt();
			Y = scan.nextInt();
			sb.append(query(root, X, Y) + "\n");
		}

		bw.write(sb.toString());
		bw.close();
	}

}
