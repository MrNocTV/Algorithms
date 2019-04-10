package Week1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Week1Exercise8 {

	private static final int[] HIGHLY_COMPOSIT_NUMBER = { 1, 2, 4, 6, 12, 24, 36, 48, 60, 120, 180, 240, 360, 720, 840,
			1260, 1680, 2520, 5040, 7560, 10080, 15120, 20160, 25200, 27720, 45360, 50400, 55440, 83160, 110880, 166320,
			221760, 277200, 332640, 498960, 554400, 665280, 720720, 1081080, 1441440, 2162160, 2882880, 3603600,
			4324320, 6486480, 7207200, 8648640 };

	private static int solution(int k) {
		int maxIndex = 0;
		
		for (int i : HIGHLY_COMPOSIT_NUMBER) {
			if (i <= k)
				maxIndex = i;
		}

		return k - maxIndex + 1;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
			BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
			int n = Integer.parseInt(br.readLine().trim());
			bw.write(solution(n) + "\n");
			bw.close();
		}
	}
}
