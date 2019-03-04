/**
 * 
 * @author Loc Truong Van
 * @category Algorithm
 * @site HackerRank
 * @link https://www.hackerrank.com/challenges/sherlock-and-anagrams/problem
 * @idea: this is a straightforward 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SherlockAndAnagrams {

	private static int getAllAnagramPairs(String s) {

		Map<Integer, List<String>> visited = buildLengthToSubStrsMap(s);
		int count = 0;

		for (int key : visited.keySet()) {
			List<String> subStrList = visited.get(key);
			for (int i = 0; i < subStrList.size() - 1; ++i) {
				for (int j = i + 1; j < subStrList.size(); ++j)
					if (subStrList.get(i).equals(subStrList.get(j)))
						++count;
			}
		}

		return count;
	}

	private static Map<Integer, List<String>> buildLengthToSubStrsMap(String s) {
		Map<Integer, List<String>> visited = new HashMap<>();
		int length = 1;

		while (length < s.length()) {
			for (int i = 0; i < s.length(); ++i) {
				if (i + length <= s.length()) {
					String subStr = s.substring(i, i + length);
					subStr = sortString(subStr);
					if (visited.containsKey(length))
						visited.get(length).add(subStr);
					else {
						List<String> list = new ArrayList<>();
						list.add(subStr);
						visited.put(length, list);
					}
				}
			}
			++length;
		}

		return visited;
	}

	private static String sortString(String s) {
		char[] arr = s.toCharArray();
		Arrays.sort(arr);
		return new String(arr);
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		for (int i = 1; i <= N; ++i)
			System.out.println(getAllAnagramPairs(input.next()));
	}

}
