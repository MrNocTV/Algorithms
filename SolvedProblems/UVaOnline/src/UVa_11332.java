import java.util.*;

public class UVa_11332 {

	static int g(int n) {
		String s = String.valueOf(n);
		if (s.length() == 1)
			return n;
		else {
			int sum = 0;
			for (char c : s.toCharArray()) {
				sum += Character.getNumericValue(c);
			}

			return g(sum);
		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();

		while (n != 0) {
			System.out.println(g(n));
			n = input.nextInt();
		}
	}

}