package Week1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Week1Exercise4 {

	private static int solution(int[] tDays, int[] pDays) {
		int max = 0;
		Set<Integer> tIndexes = new HashSet<>(tDays.length);
		Set<Integer> pIndexes = new HashSet<>(pDays.length);

		for (int i = 0; i < tDays.length; ++i) {
			if (tDays[i] > pDays[i]) {
				max += tDays[i];
				tIndexes.add(i);
			} else {
				max += pDays[i];
				pIndexes.add(i);
			}
		}

		Set<Integer> resultsSet = new HashSet<Integer>();
		if (tIndexes.size() == 0) {
			for (int i : pIndexes) {
				int tempMax = max - pDays[i] + tDays[i];
				resultsSet.add(tempMax);
			}
		} else if (pIndexes.size() == 0) {
			for (int i : tIndexes) {
				int tempMax = max - tDays[i] + pDays[i];
				resultsSet.add(tempMax);
			}
		}

		return resultsSet.size() == 0 ? max : Collections.max(resultsSet);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
			BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
			int n = Integer.parseInt(br.readLine().trim());
			int[] tDays = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
			int[] pDays = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();

			bw.write(solution(tDays, pDays) + "\n");
			bw.close();
		}
	}

}
