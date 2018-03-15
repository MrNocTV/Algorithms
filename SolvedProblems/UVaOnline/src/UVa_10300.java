import java.util.Scanner;

public class UVa_10300 {
	
	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int f;
		int size;
		int animal;
		int friendliness;
		int sum;
		
		for (int i = 1; i <= n; ++i) {
			sum = 0;
			f = input.nextInt();
			for (int j = 1; j <= f; ++j) {
				size = input.nextInt();
				animal = input.nextInt();
				friendliness = input.nextInt();
				sum += size * friendliness;
			}
			System.out.println(sum);
		}
		
	}
	
}
