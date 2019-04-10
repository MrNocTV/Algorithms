package Week1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Week1Exercise3 {

	private static long nthFibo(long a0, long a1, long a2, int n) {
		if (n == 0)
			return a0;
		if (n == 1)
			return a1;
		if (n == 2)
			return a2;

		for (int i = 1; i <= n - 2; ++i) {
			long temp = a2 + a1 - a0;
			a0 = a1;
			a1 = a2;
			a2 = temp;
		}

		return a2;
	}

	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
			BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
			String[] inputs = br.readLine().trim().split(" ");
			long a0 = Long.parseLong(inputs[0]);
			long a1 = Long.parseLong(inputs[1]);
			long a2 = Long.parseLong(inputs[2]);
			int n = Integer.parseInt(inputs[3]);
			bw.write(nthFibo(a0, a1, a2, n) + "\n");
			bw.close();
		}
	}

}
