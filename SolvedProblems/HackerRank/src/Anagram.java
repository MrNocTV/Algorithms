/**
 * 
 * @author Loc Truong Van
 * @category Algorithm
 * @site HackerRank
 * @link https://www.hackerrank.com/challenges/anagram/problem
 * @idea: another trivial problem.
 * 		- hint: build frequency map
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Anagram {

	private static int anagram(String s) {
		if (s.length() % 2 != 0)
			return -1;
		String s1 = s.substring(0, s.length() / 2);
		String s2 = s.substring(s.length() / 2);

		Map<Character, Integer> charFrequencyMap1 = new HashMap<>();
		Map<Character, Integer> charFrequencyMap2 = new HashMap<>();

		for (char c : s1.toCharArray()) {
			charFrequencyMap1.put(c, charFrequencyMap1.getOrDefault(c, 0) + 1);
		}

		for (char c : s2.toCharArray()) {
			charFrequencyMap2.put(c, charFrequencyMap2.getOrDefault(c, 0) + 1);
		}

		/* if c is in both s1 and s2, update their frequency */
		for (char c : charFrequencyMap1.keySet()) {
			if (charFrequencyMap2.containsKey(c)) {
				int freq1 = charFrequencyMap1.get(c);
				int freq2 = charFrequencyMap2.get(c);
				if (freq1 < freq2) {
					charFrequencyMap1.put(c, freq1 - freq1);
					charFrequencyMap2.put(c, freq2 - freq1);
				} else {
					charFrequencyMap1.put(c, freq1 - freq2);
					charFrequencyMap2.put(c, freq2 - freq2);
				}
			}
		}

		/* it doesn't matter which frequency map we choose */
		int count = 0;
		for (char c : charFrequencyMap1.keySet())
			count += charFrequencyMap1.get(c);

		return count;
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		for (int i = 1; i <= N; ++i) {
			System.out.println(anagram(input.next()));
		}
	}

}
