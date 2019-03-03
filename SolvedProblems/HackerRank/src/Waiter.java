import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * 
 * @author Loc Truong Van
 * @category Algorithm
 * @site HackerRank
 * @link https://www.hackerrank.com/challenges/waiter/problem
 * @idea: this is an easy problem but is tagged with the "Medium" label.
 * 		You just need to do exactly what in instruction
 * 		When output the result, go through the list of StackB
 * 		Then get the last stackA
 */

public class Waiter {

	private static final int[] primes = new int[1201];
	// get first 1200 primes
	static {
		final boolean[] primes = new boolean[9800];
		Arrays.fill(primes, true);
		int bound = (int) Math.sqrt(primes.length);
		for (int i = 2; i < bound; ++i) {
			if (primes[i])
				for (int j = i + i; j < primes.length; j += i)
					primes[j] = false;
		}

		int j = 0;
		for (int i = 2; i < primes.length; ++i) {
			if (primes[i]) {
				Waiter.primes[j++] = i;
			}
			if (j >= Waiter.primes.length)
				break;
		}
	}

	private static final Scanner input = new Scanner(System.in);

	private static void solution(int Q, Integer[] A) {
		Stack<Integer> A0 = new Stack<>();
		for (int e : A)
			A0.push(e);
		List<Stack<Integer>> stackAList = new ArrayList<>(Q);
		stackAList.add(A0);
		List<Stack<Integer>> stackBList = new ArrayList<>();

		for (int i = 1; i <= Q; ++i) {
			Stack<Integer> stack = stackAList.get(i - 1);
			Stack<Integer> nextAStack = new Stack<Integer>();
			Stack<Integer> nextBStack = new Stack<Integer>();
			int ithPrime = primes[i - 1];
			while (!stack.isEmpty()) {
				int e = stack.pop();
				if (e % ithPrime == 0)
					nextBStack.push(e);
				else
					nextAStack.push(e);
			}

			stackBList.add(nextBStack);
			if (nextAStack.size() == 0)
				break;
			stackAList.add(nextAStack);
		}

		for (int i = 0; i < stackBList.size(); ++i) {
			Stack<Integer> stackB = stackBList.get(i);
			while (!stackB.isEmpty())
				System.out.println(stackB.pop());
		}
		Stack<Integer> stackA = stackAList.get(stackAList.size() - 1);
		while(!stackA.isEmpty())
			System.out.println(stackA.pop());
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		String[] tokens = input.nextLine().trim().split(" ");
		int N = Integer.parseInt(tokens[0]);
		int Q = Integer.parseInt(tokens[1]);
		Integer[] A = Arrays.stream(input.nextLine().trim().split(" ")).map(Integer::valueOf).toArray(Integer[]::new);
		solution(Q, A);
	}
}
