import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UVa_11547 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		int n;
		String s;
		
		for (int i = 1; i <= t; ++i) {
			n = Integer.parseInt(br.readLine());
			n = ((n*567)/9 + 7492) * 235 / 47 - 498;  
			s = String.valueOf(n);
			System.out.println(s.charAt(s.length() - 2));
		}
		
	}
	
}
