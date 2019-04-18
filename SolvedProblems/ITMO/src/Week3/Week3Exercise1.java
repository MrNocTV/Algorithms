package Week3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class Week3Exercise1 {

	static class Scan {
		private byte[] buf = new byte[1024];
		private int index;
		private InputStream in;
		private int total;

		public Scan(InputStream in) {
			this.in = in;
		}

		public int scan() throws IOException
		{
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

	public static void mergeSort(int[] A, int[] W, int s, int t) throws IOException {
		if (s + 1 == t)
			return;
		int mid = (s + t) / 2;
		mergeSort(A, W, s, mid);
		mergeSort(A, W, mid, t);

		int i = s;
		int j = mid;
		int k = s;

		while (i < mid || j < t) {
			if (j == t || (i < mid && A[i] <= A[j]))
				W[k++] = A[i++];
			else
				W[k++] = A[j++];
		}

		for (i = s; i < t; ++i)
			A[i] = W[i];

		bw.write((s + 1) + " " + t + " " + A[s] + " " + A[t - 1] + "\n");
	}

	static BufferedWriter bw;

	public static void main(String[] args) throws IOException {
		Scan scan = new Scan(new FileInputStream(new File("input.txt")));
		bw = new BufferedWriter(new FileWriter("output.txt"));
		int N = scan.scanInt();
		int[] A = new int[N];
		int[] W = new int[N];
		for (int i = 0; i < N; ++i) {
			A[i] = scan.scanInt();
		}

		mergeSort(A, W, 0, N);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < A.length; ++i) {
			sb.append(A[i] + " ");
		}
		bw.write(sb.toString().trim() + "\n");
		bw.close();
	}

}
