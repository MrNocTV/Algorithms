import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UVa_12157 {

	static final int MILE_RATE = 10;
	static final int MILE_DURATION = 30;
	static final int JUICE_RATE = 15;
	static final int JUICE_DURATION = 60;

	static int getMileCost(int duration) {
		return (int) Math.ceil((double) duration / MILE_DURATION) * MILE_RATE;
	}

	static int getJuiceCost(int duration) {
		return (int) Math.ceil((double) duration / JUICE_DURATION) * JUICE_RATE;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		int n;
		List<Integer> list;
		int totalMileCost;
		int totalJuiceCost;

		for (int i = 1; i <= t; ++i) {
			n = Integer.parseInt(br.readLine());
			list = Arrays.stream(br.readLine().trim().split(" ")).map(e -> Integer.parseInt(e))
					.collect(Collectors.toList());

			totalMileCost = list.stream().map(e -> getMileCost(e + 1)).mapToInt(Integer::intValue).sum();
			totalJuiceCost = list.stream().map(e -> getJuiceCost(e + 1)).mapToInt(Integer::intValue).sum();
			System.out.print("Case " + i + ": ");
			if (totalMileCost < totalJuiceCost)
				System.out.println("Mile " + totalMileCost);
			else if (totalMileCost > totalJuiceCost)
				System.out.println("Juice " + totalJuiceCost);
			else
				System.out.println("Mile Juice " + totalJuiceCost);
		}

	}

}
