package Week2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Week2Exercise7 {
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
		SortedMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		int cup;
		int smallestStack;
		int size;
		int newStack;

		for (int i = 1; i <= N; ++i) {
			cup = scan.scanInt();
			if (cup != 0) {
				map.put(1, map.getOrDefault(1, 0) + 1);
			} else {
				if (map.size() == 0)
					map.put(1, 1);
				else {
					smallestStack = map.firstKey();
					size = map.get(smallestStack);
					newStack = smallestStack + 1;
					map.put(newStack, map.getOrDefault(newStack, 0) + 1);
					if (size == 1)
						map.remove(smallestStack);
					else
						map.put(smallestStack, size - 1);
				}
			}
		}

		int height;
		int count;
		StringBuilder sb = new StringBuilder();
		sb.append(map.size() + "\n");

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			height = entry.getKey();
			count = entry.getValue();
			sb.append(height + " " + count + "\n");
		}

		fw.write(sb.toString());
		fw.close();
	}
}
