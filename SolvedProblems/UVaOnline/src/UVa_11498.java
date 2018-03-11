import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UVa_11498 {
	
	static String getRegion(int x, int y, int n, int m) {
		String region = "";
		
		if (x == n || y == m)
			region = "divisa";
		else if (x > n && y > m) 
			region = "NE";
		else if (x > n && y < m)
			region = "SE";
		else if (x < n && y > m)
			region = "NO";
		else if (x < n && y < m)
			region = "SO";
		
		return region;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String s;
		int q;
		int n, m;
		int x, y;
		
		while ((s = br.readLine()) != null) {
			q = Integer.parseInt(s);
			if (q == 0)
				break;
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			for(int i = 1; i <= q; ++i) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				System.out.println(getRegion(x, y, n, m));
			}
		}
		
	}
	
}
