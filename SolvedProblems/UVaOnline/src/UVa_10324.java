import java.io.IOException;
import java.util.Scanner;

public class UVa_10324 {

	static boolean isTheSame(String s, int i, int j) {
		int start = Math.min(i, j);
		int end = Math.max(i, j);
		if (end - start == 0) {
			return true;
		} else {
			for (int k = start + 1; k <= end; ++k) {
				if (s.charAt(k) != s.charAt(k - 1))
					return false;
			}
		}
		return true;
	}

	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		
		int test = 1;
		
		while (input.hasNext()) {
			String s = input.next();
			int n = input.nextInt();
			System.out.println("Case " + test + ":");
			++test;
			
			for (int i = 1; i <= n; ++i) {
				int l = input.nextInt();
				int r = input.nextInt();
				System.out.println(isTheSame(s, l, r) ? "Yes" : "No");
			}
		}
	}

}
