import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UVa_11799 {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		List<Integer> list;
		int minSpeed;

		for (int i = 1; i <= t; ++i) {
			list = Arrays.stream(br.readLine().trim().split(" ")).map(e -> Integer.parseInt(e))
					.collect(Collectors.toList());
			Collections.sort(list);
			minSpeed = list.get(list.size() - 1);
			System.out.println("Case " + i + ": " + minSpeed);
		}

	}

}
