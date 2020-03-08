import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class Main {

	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int round = Integer.parseInt(br.readLine());

			while (round != -1) {
				String word = br.readLine().trim();
				String guess = br.readLine().trim();
				Map<Character, Integer> charFreqMap = new HashMap<>();
				for (char c : word.toCharArray()) {
					charFreqMap.put(c, charFreqMap.getOrDefault(c, 0) + 1);
				}

				int matches = 0;
				int stroke = 7;

				for (char c : guess.toCharArray()) {
					if (charFreqMap.containsKey(c)) {
						matches += charFreqMap.get(c);
						charFreqMap.put(c, 0);
					} else {
						stroke--;
					}

					if (stroke <= 0)
						break;
				}

				System.out.println("Round " + round);
				if (matches != word.length()) {
					if (stroke <= 0) {
						System.out.println("You lose.");
					} else {
						System.out.println("You chickened out.");
					}
				} else {
					System.out.println("You win.");
				}

				round = Integer.parseInt(br.readLine());
			}
		}
	}
}
