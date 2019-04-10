package Week1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Week1Exercise9 {
	private static int maxSolvedProblems(int[] times) {
		int solved = 0;
		int time = 300 * 60;
		int i = 0;
		while (true) {
			if (i >= times.length || time < times[i] || time <= 0)
				break;
			time -= times[i++];
			++solved;
		}

		return solved;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
			BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
			int n = Integer.parseInt(br.readLine().trim());
			int[] times = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
			Arrays.sort(times);
			System.out.println(Arrays.toString(times));
			bw.write(maxSolvedProblems(times) + "\n");
			bw.close();
		}
	}
}
