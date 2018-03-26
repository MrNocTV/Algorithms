import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UVa_11678 {

	/*
	 * Complement: Set of members that belong to set B "and not" set A.
	 */
	static Set<Integer> complement(Set<Integer> setB, Set<Integer> setA) {
		Set<Integer> result = new HashSet<>();
		result.addAll(setB);
		result.removeAll(setA);
		return result;
	}

	static int maximumNumCards(Set<Integer> setA, Set<Integer> setB) {
		return Math.min(complement(setB, setA).size(), complement(setA, setB).size());
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] arr;
		int A;
		int B;
		Set<Integer> setA;
		Set<Integer> setB;

		while (true) {
			arr = br.readLine().trim().split(" ");
			A = Integer.parseInt(arr[0]);
			B = Integer.parseInt(arr[1]);
			if (A == 0 && B == 0)
				break;
			setA = Arrays.stream(br.readLine().trim().split(" ")).map(e -> Integer.parseInt(e))
					.collect(Collectors.toSet());
			setB = Arrays.stream(br.readLine().trim().split(" ")).map(e -> Integer.parseInt(e))
					.collect(Collectors.toSet());
			System.out.println(maximumNumCards(setA, setB));
		}
	}
}
