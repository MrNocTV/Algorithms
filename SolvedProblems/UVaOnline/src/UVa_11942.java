import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class UVa_11942 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Integer[] beardLengths;
		boolean isAscending;
		boolean ordered;

		System.out.println("Lumberjacks:");
		for (int i = 1; i <= n; ++i) {
			beardLengths = Arrays.stream(br.readLine().trim().split(" ")).map(e -> Integer.parseInt(e))
					.toArray(size -> new Integer[size]);
			isAscending = beardLengths[0] < beardLengths[1];
			ordered = true;

			for (int j = 1; j < beardLengths.length; ++j) {
				if (isAscending) {
					if (beardLengths[j] < beardLengths[j - 1])
						ordered = false;
				} else {
					if (beardLengths[j] > beardLengths[j - 1])
						ordered = false;
				}
			}
			System.out.println(ordered ? "Ordered" : "Unordered");
		}

	}

}
