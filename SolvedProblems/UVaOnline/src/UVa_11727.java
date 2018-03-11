import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UVa_11727 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= t; ++i) {
			List<Integer> list = Arrays.stream(br.readLine().trim().split(" ")).map(e -> Integer.parseInt(e)).collect(Collectors.toList());
			Collections.sort(list);
			System.out.println("Case " + i + ": " + list.get(1));
		}
	}
	
}
