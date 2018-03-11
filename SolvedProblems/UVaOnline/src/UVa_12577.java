import java.util.Scanner;

public class UVa_12577 {
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String s = input.next();
		int test = 1;
		
		while (!s.equals("*")) {
			System.out.print("Case " + test + ": ");
			System.out.println(s.equals("Hajj") ? "Hajj-e-Akbar" : "Hajj-e-Asghar");
			s = input.next();
			++test;
		}
	}
	
}
