import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class UVa_353 {

	public static Set<String> allSubStrings(String s) {
		Set<String> result = new HashSet<>();
		for (int i = 1; i <= s.length(); ++i) {
			for (int j = 0; (j + i) <= s.length(); ++j) {
				result.add(s.substring(j, j + i));
			}
		}

		return result;
	}

	static boolean isPalindrome(String s) {
		// fast return
		if(s.length() == 1)
			return true;
		
		char[] arr = s.toCharArray();
		int mid = s.length() / 2;
		int n = arr.length;
		for (int i = 0; i <= mid; ++i)
			if (arr[i] != arr[n - 1 - i])
				return false;
		return true;
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String s;
		
		while (input.hasNext()) {
			s = input.next();
			Set<String> subStrings = allSubStrings(s);
			long palindrome = subStrings.stream().filter(e -> isPalindrome(e)).count();
			System.out.println("The string '" + s + "' contains " + palindrome + " palindromes.");
		}
	}

}
