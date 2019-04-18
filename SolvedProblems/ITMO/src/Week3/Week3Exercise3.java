package Week3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Week3Exercise3 {

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

	static class Medicine implements Comparable<Medicine> {
		int lives;
		int units;

		public Medicine(int lives, int units) {
			this.lives = lives;
			this.units = units;
		}

		@Override
		public int compareTo(Medicine other) {
			return Integer.compare(other.lives, this.lives);
		}

		@Override
		public String toString() {
			return lives + " " + units;
		}
	}

	static long saveLives(Medicine[] medicines, int W) {
		long saved = 0;
		int i = 0;
		while (W > 0 && i < medicines.length) {
			if (W >= medicines[i].units) {
				W -= medicines[i].units;
				saved += medicines[i].lives * medicines[i].units;
			} else {
				saved += medicines[i].lives * W;
				W = 0;
			}
			++i;
		}

		return saved;
	}

	public static void main(String[] args) throws IOException {
		Scan scan = new Scan(new FileInputStream(new File("input.txt")));
		BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
		int N = scan.scanInt();
		Medicine[] medicines = new Medicine[N];
		int lives, units;
		for (int i = 0; i < N; ++i) {
			lives = scan.scanInt();
			units = scan.scanInt();

			medicines[i] = new Medicine(lives, units);
		}
		int W = scan.scanInt();
		Arrays.sort(medicines);
		bw.write(saveLives(medicines, W) + "\n");
		bw.close();
	}
}
