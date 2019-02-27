
/**
 * 
 * @author Loc Truong Van
 * @category Algorithm
 * @site HackerRank
 * @link https://www.hackerrank.com/challenges/maximum-element/problem
 */

import java.util.*;

public class MaximumElement1 {

	private static final SortedSet<Integer> biggestElement = new TreeSet<>();
	private static final Map<Integer, Integer> freqMap = new HashMap<>();
	private static final Stack<Integer> stack = new Stack<>();

	public static void doCommand(int code, int x) {
		switch (code) {
		case 1:
			stack.push(x);
			biggestElement.add(x);
			freqMap.put(x, freqMap.getOrDefault(x, 0) + 1);
			break;
		case 2:
			if (stack.size() > 0) {
				int removedItem = stack.pop();
				int freq = freqMap.get(removedItem);
				if (freq == 1) {
					biggestElement.remove(removedItem);
					freqMap.remove(removedItem);
				} else {
					freqMap.put(removedItem, freq - 1);
				}
			}
			break;
		case 3:
			if (stack.size() > 0)
				System.out.println(biggestElement.last());
			break;
		}
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		input.nextLine();
		for (int i = 1; i <= n; ++i) {
			String[] tokens = input.nextLine().trim().split(" ");
			int command = Integer.valueOf(tokens[0]);
			int x = tokens.length == 2 ? Integer.valueOf(tokens[1]) : -1;
			doCommand(command, x);
		}
	}
}
