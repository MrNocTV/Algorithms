package Week2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class Week2Exercise6_Array {
	static class Scan {
		private byte[] buf = new byte[1024]; // Buffer of Bytes
		private int index;
		private InputStream in;
		private int total;

		public Scan(InputStream in) {
			this.in = in;
		}

		public int scan() throws IOException // Scan method used to scan buf
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

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Scan scan = new Scan(new FileInputStream(new File("input.txt")));
		FileWriter fw = new FileWriter(new File("output.txt"));
		int N = scan.scanInt();
		int[] sumArr = new int[N + 1];
		int[] parentArr = new int[N + 1];
		sumArr[0] = 0;
		parentArr[0] = -1;
		int i = 1;
		int ith;
		int x;
		long sum = 0;

		for (int j = 1; j <= N; ++j) {
			ith = scan.scanInt();
			x = scan.scanInt();
			if (x == 0) {
				int parent = parentArr[ith];
				if (parent == -1) {
					sumArr[i] = 0;
					parentArr[i] = parent;
				} else {
					sumArr[i] = sumArr[parent];
					parentArr[i] = parentArr[parent];
				}
			} else {
				sumArr[i] = sumArr[ith] + x;
				parentArr[i] = ith;
			}
			sum += sumArr[i];
			++i;
		}

		fw.write(sum + "\n");
		fw.close();
	}
}
