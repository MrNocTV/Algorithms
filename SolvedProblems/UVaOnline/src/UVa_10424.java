import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UVa_10424 {
	
	static int sumUntilOneDigit(int n) {
		String s = String.valueOf(n);
		int total = 0;
		for (int i = 0; i < s.length(); ++i) {
			total += Character.getNumericValue(s.charAt(i));
		}
		if (s.length() == 1)
			return total;
		else
			return sumUntilOneDigit(total);
	}
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			while (true) {
				String s1 = br.readLine().trim();
				String s2 = br.readLine().trim();
				if (s1.equals("") || s2.equals("")) break;
				int sum1 = 0;
				int sum2 = 0;
				for(int i = 0; i < s1.length(); ++i) {
					char c = s1.charAt(i);
					if (Character.isAlphabetic(c)) {
						if (Character.isUpperCase(c))
							c = Character.toLowerCase(c);
						sum1 += c - '0' - 48;
					}
				}
				for(int i = 0; i < s2.length(); ++i) {
					char c = s2.charAt(i);
					if (Character.isAlphabetic(c)) {
						if (Character.isUpperCase(c))
							c = Character.toLowerCase(c);
						sum2 += c - '0' - 48;
					}
				}
				
				double t1 = (double)sumUntilOneDigit(sum2);
				double t2 = sumUntilOneDigit(sum1);
				double ratio = Math.min(t1, t2) / Math.max(t1, t2) * 100;
				System.out.printf("%.2f %%\n", ratio);
			}
		} catch (Exception ex) {
			return;
		}
	}
	
}
