import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UVa_11044 {
	
	static final int SONAR_SIZE = 3;
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		StringTokenizer st;
		int t;
		int n, m;
		int sonarOnColumn;
		int numOfColumn;
		try {
			t = Integer.parseInt(br.readLine());
			for (int i = 1; i <= t; ++i) {
				st = new StringTokenizer(br.readLine());
				n = Integer.parseInt(st.nextToken());
				m = Integer.parseInt(st.nextToken());
				n -= 2;
				m -= 2;
				sonarOnColumn = (int) Math.ceil((double) n / SONAR_SIZE);
				numOfColumn = (int) Math.ceil((double) m / SONAR_SIZE);
				System.out.println(sonarOnColumn * numOfColumn);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
