import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UVa_11172 {
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t;
		int a,b; 
		
		try {
			
			t = Integer.parseInt(br.readLine());
			for (int i = 1; i <= t; ++i) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				if (a == b) 
					System.out.println("=");
				else
					System.out.println((a > b) ? ">" : "<");
			}
			
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

	}
	
}
