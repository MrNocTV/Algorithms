package Week3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class Week3Exercise4 {

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

	public static int[] solve(int N) {
		if (N == 1)
			return new int[] {1};
		else if (N == 2)
			return new int[] {1, 2};
		int[] arr = new int[N];
		if (N % 2 == 1) {
			int mid = N / 2;
			int start = N - 2;
			int i = 0;
			while (start >= 1) {
				arr[i++] = start;
				start -= 2;
			}
			arr[mid] = N;
			start = N - 1;
			i = mid + 1;
			while (start >= 2) {
				arr[i++] = start;
				start -= 2;
			}
		} else {
			int mid = N / 2 - 1;
			int start = N - 2;
			int i = 0;
			while (start >= 2) {
				arr[i++] = start;
				start -= 2;
			}
			arr[mid] = N;
			i = mid + 1;
			start = N - 1;
			while (start >= 1) {
				arr[i++] = start;
				start -= 2;
			}
		}
		return arr;
	}

	public static void main(String[] args) throws IOException {
		Scan scan = new Scan(new FileInputStream(new File("input.txt")));
		BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
		int N = scan.scanInt();
		int[] arr = solve(N);
		StringBuilder sb = new StringBuilder();
		for (int e : arr)
			sb.append(e + " ");
		bw.write(sb.toString().trim() + "\n");
		bw.close();
	}

}
