import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UVa_12554 {
	static final String[] words = { "Happy", "birthday", "to", "you", "Happy", "birthday", "to", "you", "Happy",
			"birthday", "to", "Rujia", "Happy", "birthday", "to", "you" };

	static boolean end(Map<String, Integer> singMap) {
		for (Integer sangWord : singMap.values())
			if (sangWord == 0)
				return false;
		return true;
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		Map<String, Integer> singMap = new HashMap<>();
		String[] people = new String[n];

		for (int i = 0; i < n; ++i) {
			people[i] = input.next();
			singMap.put(people[i], 0);
		}

		int pIndex = 0;
		int wIndex;

		while (!end(singMap)) {
			for (wIndex = 0; wIndex < words.length; ++wIndex) {
				System.out.println(people[pIndex] + ": " + words[wIndex]);
				singMap.put(people[pIndex], singMap.get(people[pIndex]) + 1);
				pIndex = (pIndex + 1) % people.length;
			}
		}
	}

}
