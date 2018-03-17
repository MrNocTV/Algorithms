import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UVa_00573 {
	
	static void simulate(int h, double climb, double down, double fatigue) {
		int day = 1;
		double cur = 0;
		
		while (true) {
			if (climb >= 0) {
				cur = cur + climb;
			}
			if (cur > h) {
				System.out.println("success on day " + day);
				break;
			}
			cur -= down;
			if (cur < 0) {
				System.out.println("failure on day " + day);
				break;
			}
			climb = climb - fatigue;
			++day;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int h, u, d, f;
		String[] temp;
		
		while (true) {
			temp = br.readLine().trim().split(" ");
			h = Integer.parseInt(temp[0]);
			u = Integer.parseInt(temp[1]);
			d = Integer.parseInt(temp[2]);
			f = Integer.parseInt(temp[3]);
			
			if (h == 0 && u == 0 && d == 0 && f == 0)
				break;
			
			double climb = u;
			double down = d;
			double fatigue = climb * ((double) f / 100);
			simulate(h, climb, down, fatigue);
		}
	}
	
}
