import java.util.Scanner;

public class UVa_12372 {
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int t = input.nextInt();
		int l, w, h;
		
		for (int i = 1; i <= t; ++i) {
			l = input.nextInt();
			w = input.nextInt();
			h = input.nextInt();
			System.out.print("Case " + i + ": ");
			System.out.println((l <= 20 && w <= 20 && h <= 20) ? "good" : "bad");
		}
	}
	
}
