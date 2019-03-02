import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author Loc Truong Van
 * @category Algorithm
 * @site HackerRank
 * @link https://www.hackerrank.com/challenges/game-of-two-stacks/problem
 * @idea: this is a kind of problem that you either get it or don't get it
 * 		to be honest, I didn't get this problem.
 * 		but after checking the editorial, the 'aha' moment gets in
 * 		the result have this kind of form:
 * 		+ 2 top of A, 3 top of B
 * 		+ 3 top of A, 4 top of B
 * 		+ etc
 * 		in general it is:
 * 		+ K1 top of A, K2 top of B
 * 		The remain question is, how do we generate these combinations? 
 * 		Keep in mind that when ever K1 increase, K2 will decrease and vice versa.
 */

public class GameOfTwoStacks {

	 static int twoStacks(int x, int[] a, int[] b) {
	       int maxStep = 0;
	       int sum = 0;
	       int i = 0; 
	       int j = 0;
	       while (i < a.length && sum + a[i] <= x) {
	           sum += a[i++];
	       }

	       maxStep = Math.max(maxStep, i);
	       while (j < b.length) {
	           sum += b[j++];
	            while (sum > x && i > 0) {
	                sum -= a[--i];
	            }
	            
	            if (sum <= x)
	                maxStep = Math.max(maxStep, i + j);
	       } 

	       return maxStep;
	    }

	    private static final Scanner scanner = new Scanner(System.in);

	    public static void main(String[] args) throws IOException {
	        int g = scanner.nextInt();
	        scanner.nextLine();
	        for(int test = 1; test <= g; ++test) {
	            String[] tokens = scanner.nextLine().trim().split(" ");
	            int x = Integer.valueOf(tokens[2]);
	            String[] stackA = scanner.nextLine().trim().split(" ");
	            String[] stackB = scanner.nextLine().trim().split(" ");
	            int[] a = new int[stackA.length];
	            int[] b = new int[stackB.length];
	            for(int i = 0; i < a.length; ++i) a[i] = Integer.valueOf(stackA[i]);
	            for(int i = 0; i < b.length; ++i) b[i] = Integer.valueOf(stackB[i]);
	            System.out.println(twoStacks(x, a, b));
	        }
	    }
}
