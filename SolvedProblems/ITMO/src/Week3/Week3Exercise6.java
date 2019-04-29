package Week3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;

public class Week3Exercise6 {

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

	private static boolean canSort(int[] arr, int k) {
		if (k == 1)
			return true;
		int n = arr.length;
		int i = 0;
		int x;
		int visited = 0;
		while (i + k < n && visited < n) {
			List<Integer> l = new ArrayList<>();
			for(int j = i; j < n; j += k) {
				l.add(arr[j]);
				++visited;
			}
			if (l.size() > 0) {
				Collections.sort(l);
				x = 0;
				for(int j = i; j < n; j += k)
					arr[j] = l.get(x++);
			}
			++i;
		}
		return nonDecreasing(arr);
	}

	private static boolean nonDecreasing(int[] arr) {
		for (int i = 1; i < arr.length - 1; ++i) {
			if (arr[i] < arr[i - 1])
				return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		Scan scan = new Scan(new FileInputStream(new File("input.txt")));
		BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
		int n = scan.scanInt();
		int k = scan.scanInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; ++i)
			arr[i] = scan.scanInt();
		boolean can = canSort(arr, k);
		bw.write((can ? "YES" : "NO") + "\n");
		bw.close();
	}

}