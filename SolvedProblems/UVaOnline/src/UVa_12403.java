import java.util.Scanner;

public class UVa_12403 {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int t = input.nextInt();
		String op;
		int total = 0;
		
		for (int i = 1; i <= t; ++i) {
			op = input.next();
			if (op.equals("donate")) 
				total += input.nextInt();
			else
				System.out.println(total);
		}
	}
	
}
