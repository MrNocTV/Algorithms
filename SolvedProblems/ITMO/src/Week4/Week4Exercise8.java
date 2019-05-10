package Week4;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.time.Instant;

public class Week4Exercise8 {

	static class Scan {
		final private int BUFFER_SIZE = 1 << 16;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;

		public Scan(InputStream in) {
			din = new DataInputStream(in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public int nextInt() throws Exception {
			int ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			do {
				ret = ret * 10 + c - '0';
				c = read();
			} while (c > ' ');
			return ret;
		}

		private void fillBuffer() throws Exception {
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() throws Exception {
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}
	}

	public static void sort(byte[] a, int[] order, int k, int n, int m) {
		int R = 26;
		int[] auxOrder = new int[n];
		int last = m*n;
		int o;
		for (int d = 1; d <= k; ++d) {
			int[] count = new int[R + 1];
			for (int i = last-n; i < last; ++i) {
				count[a[i]+1]++;
			}
			
			for (int r = 0; r < R; ++r) {
				count[r + 1] += count[r];
			}
			int j= 0;
			int temp = last - n-1;
			for (int i = 0; i < n; ++i) {
//				System.out.println(temp + order[i]);
				auxOrder[count[a[temp+order[i]]]++] = order[j++];
			}
			for (int i = 0; i < n; ++i)
				order[i] = auxOrder[i];
//			System.out.println("aux order " + Arrays.toString(auxOrder));
//			System.out.println("order " + Arrays.toString(order));
			last -= n;
		}

	}

	public static void main(String[] args) throws Exception {
		Scan scan = new Scan(new FileInputStream(new File("input.txt")));
		BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
		int n = scan.nextInt();
		int m = scan.nextInt();
		int k = scan.nextInt();
		byte[] input = new byte[n*m];
		byte c;
		byte base = 97;
		int offset = 0;
		int row = 0;

		int[] order = new int[n];
		Instant start = Instant.now();
		while ((c = scan.read()) != -1) {
			if (c >= 97 && c <= 122)
				input[offset++] = (byte)(c - 97);
		}

		for (int i = 1; i <= n; ++i) {
			order[i - 1] = i;
		}
//		System.out.println("read took " + Duration.between(start, Instant.now()).toMillis() + "ms");
//		System.out.println(Arrays.toString(input));
		start = Instant.now();
//		System.out.println(Arrays.toString(order));
		sort(input, order, k, n, m);
//		System.out.println("sort took " + Duration.between(start, Instant.now()).toMillis() + "ms");
//		System.out.println(Arrays.toString(order));
		StringBuilder sb = new StringBuilder();
		for (int i : order)
			sb.append(i + " ");
		bw.write(sb.toString().trim() + "\n");
		bw.close();
	}
}
