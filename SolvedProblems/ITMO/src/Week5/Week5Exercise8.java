package Week5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Week5Exercise8 {

	private static int min(int a, int b, int c) {
		return Math.min(Math.min(a, b), c);
	}

	private static int editDist(char[] a, char[] b, int m, int n, int[][] cache) {
		if (m == 0) {
			return n;
		}
		if (n == 0) {
			return m;
		}

		if (a[m - 1] == b[n - 1]) {
			if (cache[m - 1][n - 1] == -1)
				cache[m - 1][n - 1] = editDist(a, b, m - 1, n - 1, cache);
			return cache[m - 1][n - 1];
		} else {
			// If last characters are not same, consider all three
			// operations on last character of first string, recursively
			// compute minimum cost for all three operations and take
			// minimum of three values.
			if (cache[m][n - 1] == -1)
				cache[m][n - 1] = editDist(a, b, m, n - 1, cache);
			if (cache[m - 1][n] == -1)
				cache[m - 1][n] = editDist(a, b, m - 1, n, cache);
			if (cache[m - 1][n - 1] == -1)
				cache[m - 1][n - 1] = editDist(a, b, m - 1, n - 1, cache);
			return 1 + min(cache[m][n - 1], cache[m - 1][n], cache[m - 1][n - 1]);
		}
	}

	public static void main(String[] arsg) throws Exception {
		BufferedReader br = new BufferedReader(
				new FileReader("input.txt"));
		BufferedWriter bw = new BufferedWriter(
				new FileWriter("output.txt"));

		String a = br.readLine().trim();
		String b = br.readLine().trim();
		int m = a.length();
		int n = b.length();
		int[][] cache = new int[m + 1][n + 1];
		for (int i = 0; i <= m; ++i)
			for (int j = 0; j <= n; ++j)
				cache[i][j] = -1;
		bw.write(editDist(a.toCharArray(), b.toCharArray(), m, n, cache) + "\n");
		br.close();
		bw.close();
	}

}
