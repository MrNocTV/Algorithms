import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class UVa_12015 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		SortedMap<Integer, List<String>> sortedSites;
		String[] line;
		Integer key;
		int highestRank;
		
		for (int i = 1; i <= t; ++i) {
			sortedSites = new TreeMap<Integer, List<String>>((k1, k2) -> k1 - k2);
			highestRank = Integer.MIN_VALUE;
			
			for (int j = 1; j <= 10; ++j) {
				line = br.readLine().trim().split(" ");
				key = Integer.parseInt(line[1]);
				highestRank = Math.max(highestRank, key);
				
				if (sortedSites.containsKey(key)) {
					sortedSites.get(key).add(line[0]);
				} else {
					sortedSites.put(key, new ArrayList<String>());
					sortedSites.get(key).add(line[0]);
				}
			}
			
			System.out.println("Case #" + i + ":");
			sortedSites.get(highestRank).forEach(System.out::println);
		}
		
	}
	
}
