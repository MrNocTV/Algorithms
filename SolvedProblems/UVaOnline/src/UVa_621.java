import java.util.*;

public class UVa_621 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		String digitSeq;

		for (int i = 1; i <= n; ++i) {
			digitSeq = input.next();

			if (digitSeq.equals("1") || digitSeq.equals("4") || digitSeq.equals("78")) 
				System.out.println("+");
			else if (digitSeq.endsWith("35"))
				System.out.println("-");
			else if (digitSeq.startsWith("9") && digitSeq.endsWith("4"))
				System.out.println("*");
			else if (digitSeq.startsWith("190"))
				System.out.println("?");
		}
	}

}


