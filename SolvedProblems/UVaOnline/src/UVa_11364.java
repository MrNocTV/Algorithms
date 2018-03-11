import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UVa_11364 {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		int n;
		int firstStore;
		int lastStore;
		int store;
		StringTokenizer st;
		
		for(int i = 1; i <= t; ++i) {
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			firstStore = 100;
			lastStore = 0;
			
			while (st.hasMoreTokens()) {
				store = Integer.parseInt(st.nextToken());
				if (store < firstStore)
					firstStore = store;
				if (store > lastStore)
					lastStore = store;
			}
			System.out.println(2*(lastStore - firstStore));
		}
		
	}
	
}
