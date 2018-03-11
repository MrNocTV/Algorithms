import java.io.IOException;
import java.util.Scanner;

public class UVa_12279 {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws NumberFormatException, IOException {

		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int test = 1;
		int cur;
		int sum;
		
		while (n != 0) {
			sum = 0;
			for(int i = 1; i <= n; ++i) {
				cur = input.nextInt();
				if (cur != 0) ++sum;
				else --sum;
			}
			
			System.out.println("Case " + test + ": " + sum);
			n = input.nextInt();
			++test;
		}
	}

}
