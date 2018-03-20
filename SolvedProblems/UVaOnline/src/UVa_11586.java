import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UVa_11586 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int countM, countF;
		String s;
		
		for(int i = 1; i <= n; ++i) {
			s = br.readLine().trim();
			countM = countF = 0;
			for (char c : s.toCharArray()) {
				if (c == 'M') ++countM;
				else if (c == 'F') ++countF;
			}
			
			if (countM == countF) {
				if (countM == 0 && countF == 2 || countM == 2 && countF == 0 || countM == 1 && countF == 1)
					System.out.println("NO LOOP");
				else
					System.out.println("LOOP");
			} else {
				System.out.println("NO LOOP");
			}
		}
		
	}
	
}
