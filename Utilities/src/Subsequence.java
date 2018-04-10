import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.IntStream;

// maximum sum increasing subsequence
public class Subsequence {

	// s1 <= s2 <= s3 ....
	// change <= to < to get distinct increasing subsequence
	static Map<Integer, Integer> maxSumIncreasingSubsequence(int[] arr) {
		// @Link: https://www.youtube.com/watch?v=99ssGWhLPUE
		int n = arr.length;
		int[] max = Arrays.copyOf(arr, n);
		int[] seq = IntStream.range(0, n).toArray();
		int maxSum = Integer.MIN_VALUE;
		int maxIdx = 0;
		int j = 0, i = 1;

		while (true) {
			if (arr[j] <= arr[i]) {
				int newSum = max[j] + arr[i];
				if (newSum > max[i]) {
					max[i] = newSum;
					seq[i] = j;
				}
				if (max[i] > maxSum) {
					maxSum = max[i];
					maxIdx = i;
				}
			}
			++j;

			if (j == i) {
				++i;
				j = 0;
			}

			if (i == n)
				break;
		}

		return Collections.singletonMap(maxIdx, maxSum);
	}

}
