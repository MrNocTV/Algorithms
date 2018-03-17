import java.util.Scanner;

public class UVa_11764 {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int t = input.nextInt();
		
		int highJumps;
		int lowJumps;
		int n;
		int prevHeight;
		int curHeight;
		
		for (int i = 1; i <= t; ++i) {
			n = input.nextInt();
			prevHeight = -1;
			highJumps = 0;
			lowJumps = 0;
			
			for (int j = 1; j <= n; ++j) {
				curHeight = input.nextInt();
				
				if (prevHeight != -1) {
					if (curHeight > prevHeight) ++highJumps;
					else if (curHeight < prevHeight) ++lowJumps;
				}
				
				prevHeight = curHeight;	
			}
			
			System.out.println("Case " + i + ": " + highJumps + " " + lowJumps);
	
		}
		
		
	}
	
}
