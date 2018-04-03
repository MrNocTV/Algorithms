import java.math.BigInteger;
import java.util.Scanner;

/**
 * 
 * @author Loc Truong Van
 * @category Algorithm
 * @site GeeksForGeeks
 * @link https://practice.geeksforgeeks.org/problems/geek-and-strings/0
 * @idea Just use JAVA's BigInteger
 * 
 */

public class KAryTree {

	static final BigInteger MOD = BigInteger.valueOf((int) Math.pow(10, 9) + 7);

	static int numLeafNodes(int k, int m) {
		BigInteger a = BigInteger.valueOf(k);
		a = a.pow(m).mod(MOD);
		return a.intValue();
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int T = input.nextInt();
		int k, m;

		for (int t = 1; t <= T; ++t) {
			k = input.nextInt();
			m = input.nextInt();
			System.out.println(numLeafNodes(k, m));
		}

	}

}
