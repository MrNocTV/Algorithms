/**
 * 
 * @author Loc Truong Van
 * @category Algorithm
 * @site HackerRank
 * @link https://www.hackerrank.com/challenges/halloween-sale/problem
 */

import java.util.Scanner;

public class HalloweenSale {

	private static int howManyGames(int p, int d, int m, int s) {
		int value = p;
		int count = 0;
		while (s > 0 && value <= s) {
			s -= value;
			++count;

			if (value - d >= m)
				value -= d;
			else
				value = m;
		}

		return count;
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int p = input.nextInt();
		int d = input.nextInt();
		int m = input.nextInt();
		int s = input.nextInt();
		System.out.println(howManyGames(p, d, m, s));
	}

}
