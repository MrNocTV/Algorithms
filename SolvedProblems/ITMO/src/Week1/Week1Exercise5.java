package Week1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Week1Exercise5 {

	private static double solution(int[][] table) {
		double max = -1;
		int[] coder = table[0];
		int[] mathematician = table[1];
		int[] tester = table[2];

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				for (int k = 0; k < 3; ++k) {
					if (i != j && j != k && i != k) {
						max = Math.max(max, Math.sqrt(Math.pow((double) coder[i], 2)
								+ Math.pow((double) mathematician[j], 2) + Math.pow((double) tester[k], 2)));
					}
				}
			}
		}

		return max;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
			BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
			int[][] table = new int[3][3];
			table[0] = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
			table[1] = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
			table[2] = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
			bw.write(solution(table) + "\n");
			bw.close();
		}
	}

}
