import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class UVa_12503 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		int n;
		List<String> instructions;
		String instruct;
		int botPos;
		int sameAs;
		
		for (int i = 1; i <= t; ++i) {
			n = Integer.parseInt(br.readLine());
			botPos = 0;
			instructions = new ArrayList<String>();
			
			for (int j = 1; j <= n; ++j) {
				instruct = br.readLine().trim();
				if (instruct.equals("LEFT")) {
					--botPos;
					instructions.add(instruct);
				} else if (instruct.equals("RIGHT")) {
					++botPos;
					instructions.add(instruct);
				} else {
					sameAs = Integer.parseInt(instruct.split(" ")[2]) - 1;
					instruct = instructions.get(sameAs);
					if (instruct.equals("LEFT"))
						--botPos;
					else if (instruct.equals("RIGHT"))
						++botPos;
					instructions.add(instruct);
				}
			}
			
			System.out.println(botPos);
			
		}
	}
	
}
