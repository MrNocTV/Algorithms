package Week3;

/**
 * Joinson's algorithm
 * @link https://ieda.ust.hk/dfaculty/ajay/courses/ieem513/GT/johnson.html
 */
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;

public class Week3Exercise8 {

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

		public long scanLong() throws IOException {
			return scanInt();
		}

		private boolean isWhiteSpace(int n) {
			if (n == ' ' || n == '\n' || n == '\r' || n == '\t' || n == -1)
				return true;
			return false;
		}
	}

	static class Job implements Comparable<Job> {
		long a;
		long b;
		int index;

		public Job(long a, long b, int index) {
			this.a = a;
			this.b = b;
			this.index = index;
		}

		@Override
		public int compareTo(Job that) {
			long minThis = Math.min(this.a, this.b);
			long minThat = Math.min(that.a, that.b);
			
			if (minThis != minThat)
				return Long.compare(minThis, minThat);
			else {
				if (this.a == that.a && this.b == that.b) {
					return 0;
				} else if (this.a == that.a) {
					return Long.compare(that.b, this.b);
				} else {
					return Long.compare(that.a, this.a);
				}
			}
		}

		@Override
		public String toString() {
			return "(" + a + "," + b + ") - " + index;
		}
	}

	public static void solve(long[] a, long[] b, int[] order) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
		LinkedList<Integer> l1 = new LinkedList<>();
		LinkedList<Integer> l2 = new LinkedList<>();
		for (int i = 0; i < order.length; ++i) {
			int o = order[i];
			if (a[o] < b[o] || a[o] == b[o])
				l1.addLast(o);
			else
				l2.addFirst(o);
		}

		order = new int[l1.size() + l2.size()];
		int k = 0;
		for (int o : l1)
			order[k++] = o;
		for (int o : l2)
			order[k++] = o;

		long[][] start = new long[2][a.length];
		int prev = -1;
		for (int i : order) {
			if (prev == -1) {
				start[0][i] = 0;
				start[1][i] = a[i];
			} else {
				start[0][i] = start[0][prev] + a[prev];
				start[1][i] = Math.max(start[1][prev] + b[prev], start[0][i] + a[i]);
			}

			prev = i;
		}

		long time = start[1][prev] + b[prev];
		bw.write(time + "\n");
		System.out.println(time);
		for (int i = 0; i < start.length; ++i) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < start[0].length; ++j)
				sb.append(start[i][j] + " ");
			bw.write(sb.toString().trim() + "\n");
		}
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		Scan scan = new Scan(new FileInputStream("input.txt"));
		int n = scan.scanInt();
		Job[] jobs = new Job[n];
		long[] a = new long[n];
		long[] b = new long[n];
		int[] order = new int[n];
		for (int i = 0; i < n; ++i) {
			a[i] = scan.scanLong();
		}
		for (int i = 0; i < n; ++i) {
			b[i] = scan.scanLong();
		}
		for (int i = 0; i < n; ++i) {
			jobs[i] = new Job(a[i], b[i], i);
		}
		Arrays.sort(jobs);
		for (int i = 0; i < n; ++i) {
			order[i] = jobs[i].index;
		}
		solve(a, b, order);
	}

}