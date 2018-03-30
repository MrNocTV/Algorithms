import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * @author Loc Truong Van
 * @category Algorithm
 * @site HackerRank
 * @link https://www.hackerrank.com/challenges/array-splitting/problem
 * @idea like binary search, use same array when recursively solve
 * 
 */

public class NikitaAndTheGame {

	static int recursiveSolve(Long[] arr, int score, int left, int right) {
		int mid = canDivide(arr, left, right);

		if (mid == -1 || mid >= right) {
			return score;
		} else {
			return Math.max(recursiveSolve(arr, score + 1, left, mid), recursiveSolve(arr, score + 1, mid + 1, right));
		}
	}

	static int canDivide(Long[] arr, int left, int right) {
		if (left >= right) // cannot divide
			return -1;

		Long total = 0l;
		int i;

		for (i = left; i <= right; ++i)
			total += arr[i];

		if (total % 2 != 0) // cannot divide
			return -1;

		Long halfTotal = total / 2;
		Long sumSoFar = 0l;

		for (i = left; i <= right; ++i) {
			sumSoFar += arr[i];
			if (total - sumSoFar == halfTotal) {
				break;
			}
		}

		if (sumSoFar == total) { // cannot divide
			return -1;
		}

		return i;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int n;
		int left;
		int right;
		int score;
		Long[] arr;
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= T; ++t) {
			n = Integer.parseInt(br.readLine());
			arr = (Long[]) Arrays.stream(br.readLine().trim().split(" ")).map(Long::parseLong).toArray(Long[]::new);
			Long total = Arrays.stream(arr).reduce(0l, (a, b) -> a+b);
			if(total == 0) { // corner case
				sb.append((n-1) + "\n");
			} else {
				score = 0;
				left = 0;
				right = arr.length - 1;
				sb.append(recursiveSolve(arr, score, left, right) + "\n");
			}
		}
		System.out.println(sb.toString().trim());
	}

}
