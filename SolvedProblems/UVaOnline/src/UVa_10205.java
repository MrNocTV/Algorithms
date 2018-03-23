import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UVa_10205 {

	static final int NUM_CARDS = 52;
	static final String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };
	static final String[] faces = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };

	static final class Card {
		final String face;
		final String suit;

		Card(String face, String suit) {
			this.face = face;
			this.suit = suit;
		}

		@Override
		public String toString() {
			return face + " of " + suit;
		}
	}

	static Card[] init() {
		int count = 0;
		Card[] cards = new Card[NUM_CARDS];
		while (count < NUM_CARDS) {
			cards[count] = new Card(faces[count % 13], suits[count / 13]);
			++count;
		}

		return cards;
	}

	static Card[] shuffle(Card[] cards, int k, Map<Integer, List<Integer>> shuffle) {
		List<Integer> list = shuffle.get(k);
		List<Card> result = new ArrayList<>();
		int n = list.size();
		int i, j;
		for (int idx = 0; idx < n - 1; idx += 2) {
			i = list.get(idx) - 1;
			j = list.get(idx + 1) - 1;
			result.add(cards[i]);
			result.add(cards[j]);
		}

		return result.toArray(new Card[52]);
	}

	static void printCards(Card[] cards) {
		Arrays.stream(cards).forEach(System.out::println);
	}

	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner input = new Scanner(System.in);
		int t = Integer.parseInt(input.next());
		Map<Integer, List<Integer>> shuffle;
		List<Integer> observed;
		Card[] cards;
		int k;

		for (int i = 1; i <= t; ++i) {
			shuffle = new HashMap<>();
			cards = init();
			k = input.nextInt();
			observed = new ArrayList<>();

			for (int j = 1; j <= k; ++j) {
				List<Integer> l = new ArrayList<>();
				for (int x = 1; x <= NUM_CARDS; ++x)
					l.add(input.nextInt());
				shuffle.put(j, l);
			}

			input.nextLine();
			boolean next = true;
			int shuff = 0;
			try {
				shuff = Integer.parseInt(input.nextLine());
			} catch (Exception e) {
				next = false;
			}

			while (next) {
				cards = shuffle(cards, shuff, shuffle);
				try {
					shuff = Integer.parseInt(input.nextLine());
				} catch (Exception e) {
					next = false;
				}
			}

			printCards(cards);
			if (i < t)
				System.out.println();

		}
	}
}
