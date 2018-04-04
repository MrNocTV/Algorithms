
// @REF: https://www.geeksforgeeks.org/count-balanced-binary-trees-height-h/

public class CountBalancedBinaryTreeOfHeightH {
	static final long MOD = 1000000007;

	public static long countBalancedTreeBinaryOfHeight(int h) {
		long[] arr = new long[h + 1];
		arr[0] = arr[1] = 1l;
		for (int i = 2; i <= h; ++i) {
			arr[i] = arr[i - 1] * (2 * arr[i - 2] + arr[i - 1]);
			arr[i] %= MOD;
		}
		return arr[h];
	}
}
