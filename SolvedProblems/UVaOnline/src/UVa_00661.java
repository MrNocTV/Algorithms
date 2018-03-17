import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class UVa_00661 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n, m, c;
		String[] temp;
		int[] dc; //devices capacity
		boolean[] on;
		int currentCap;
		boolean blown;
		int maxCap;
		int sequence = 1;
		
		while (true) {
			temp = br.readLine().trim().split(" ");
			n = Integer.parseInt(temp[0]);
			m = Integer.parseInt(temp[1]);
			c = Integer.parseInt(temp[2]);
			
			if (n == 0 && m == 0 && c == 0) break;
			dc = new int[n];
			on = new boolean[n];
			Arrays.fill(on, false);
			
			currentCap = 0;
			blown = false;
			maxCap = Integer.MIN_VALUE;
			for (int i = 0; i < n; ++i)
				dc[i] = Integer.parseInt(br.readLine());
			
			for (int i = 0; i < m; ++i) {
				int dI = Integer.parseInt(br.readLine()) - 1;
				if (on[dI]) {
					currentCap -= dc[dI];
				} else {
					currentCap += dc[dI];
					if (currentCap > c)
						blown = true;
					else 
						maxCap = Math.max(maxCap, currentCap);
				}
				
				on[dI] = !on[dI];
			}
			
			System.out.println("Sequence " + sequence++);
			if (blown)
				System.out.println("Fuse was blown.");
			else {
				System.out.println("Fuse was not blown.");
				System.out.println("Maximal power consumption was " + maxCap + " amperes.");
			}
			
			System.out.println();
		}
	}
	
}
