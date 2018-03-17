import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UVa_11559 {
	
	@SuppressWarnings({ "resource", "unused" })
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n; // people
		int b; // budget
		int h; // hotels
		int w; // weeks
		String s;
		
		while ( true ) {
			int minBudget = Integer.MAX_VALUE;
			boolean mark;
			s = br.readLine();
			if (s == null || s.isEmpty()) break;
			String[] tmp = s.trim().split(" ");
			n = Integer.parseInt(tmp[0]);
			b = Integer.parseInt(tmp[1]);
			h = Integer.parseInt(tmp[2]);
			w = Integer.parseInt(tmp[3]);
			
			int price;
			List<Integer> beds;
			int cost;
			for (int i = 1; i <= h; ++i) {
				price = Integer.parseInt(br.readLine());
				mark = true;
				beds = Arrays.stream(br.readLine().trim().split(" ")).map(e -> Integer.parseInt(e)).collect(Collectors.toList());
				cost = price * n;
				for (int bed : beds) {
					if ( bed >= n && cost <= b) {
						minBudget = Math.min(cost, minBudget);
					}
				}
			}
			
			if (minBudget == Integer.MAX_VALUE)
				System.out.println("stay home");
			else
				System.out.println(minBudget);
			
		}
		
	}
	
}
