import java.util.Scanner;

public class UVa_11687 {
	
	static int recursiveSolve(String s, int i) {
		String temp = "" + s.length();
		if (temp.length() == s.length()) {
			if (Integer.parseInt(temp) == Integer.parseInt(s))
				return i;
		}
		return recursiveSolve(temp, i+1);
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String s = input.next();
		
		while (!s.equals("END")) {
			System.out.println(recursiveSolve(s, 1));
			s = input.next();
		}
	}
	
}
