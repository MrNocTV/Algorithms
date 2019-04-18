package Week3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class Week3Exercise2 {
	static long mergeSort(int[] A) {
		int[] W = new int[A.length];
		return _mergeSort(A, W, 0, A.length - 1);
	}

	static long _mergeSort(int[] A, int[] W, int left, int right) {
		int mid = 0;
		long invCount = 0;
		if (right > left) {
			mid = (right + left) / 2;
			invCount = _mergeSort(A, W, left, mid);
			invCount += _mergeSort(A, W, mid + 1, right);
			invCount += merge(A, W, left, mid + 1, right);
		}
		return invCount;
	}

	static long merge(int[] A, int[] W, int left, int mid, int right) {
		int i, j, k;
		long invCount = 0;

		i = left; 
		j = mid;
		k = left; 
		while ((i <= mid - 1) && (j <= right)) {
			if (A[i] <= A[j]) {
				W[k++] = A[i++];
			} else {
				W[k++] = A[j++];
				invCount = invCount + (mid - i);
			}
		}

		while (i <= mid - 1)
			W[k++] = A[i++];

		while (j <= right)
			W[k++] = A[j++];

		for (i = left; i <= right; i++)
			A[i] = W[i];

		return invCount;
	}

	static class Scan {
		private byte[] buf = new byte[1024];
		private int index;
		private InputStream in;
		private int total;

		public Scan(InputStream in) {
			this.in = in;
		}

		public int scan() throws IOException {
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

		public int scanInt() throws IOException {
			int integer = 0;
			int n = scan();
			while (isWhiteSpace(n)) // Removing starting whitespaces
				n = scan();
			int neg = 1;
			if (n == '-') // If Negative Sign encounters
			{
				neg = -1;
				n = scan();
			}
			while (!isWhiteSpace(n)) {
				if (n >= '0' && n <= '9') {
					integer *= 10;
					integer += n - '0';
					n = scan();
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

	static BufferedWriter bw;

	public static void main(String[] args) throws IOException {
		Scan scan = new Scan(new FileInputStream(new File("input.txt")));
		bw = new BufferedWriter(new FileWriter("output.txt"));
		int N = scan.scanInt();
		int[] A = new int[N];
		for (int i = 0; i < N; ++i) {
			A[i] = scan.scanInt();
		}

		long invCount = mergeSort(A);
		bw.write(invCount + "\n");
		bw.close();
	}

}
