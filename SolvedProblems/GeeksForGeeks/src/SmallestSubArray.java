import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author Loc Truong Van
 * @category Algorithm
 * @site GeeksForGeeks
 * @link https://practice.geeksforgeeks.org/problems/smallest-sub-array/0
 * @idea Simple brute force, no need segment tree because data's size is small
 * 
 */


public class SmallestSubArray {

	static int gcd(int a, int b) {
		if (b == 0)
			return a;
		else
			return gcd(b, a % b);
	}

	static int gcdOfList(List<Integer> list) {
		int result = list.get(0);
		for (int i = 1; i < list.size(); ++i)
			result = gcd(list.get(i), result);
		return result;
	}

	static int minSizeSubListHasGCDEqualsG(List<Integer> list, int G) {
		if (list.size() == 1)
			return -1;
		int size = 2;
		int i = 0;
		while (size <= list.size()) {
			if (i + size > list.size()) {
				i = 0;
				size += 1;
			}
			if(i+size <= list.size()) {
				int subListGCD = gcdOfList(list.subList(i, i + size));
				if (subListGCD == G)
					return size;
			}
			++i;
		}

		return -1;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int G;
		int N;
		List<Integer> list;
		
		for (int t = 1; t <= T; ++t) {
			G = Integer.parseInt(br.readLine());
			N = Integer.parseInt(br.readLine());
			list = Arrays.stream(br.readLine().trim().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
			System.out.println(minSizeSubListHasGCDEqualsG(list, G));
		}
	}

}
