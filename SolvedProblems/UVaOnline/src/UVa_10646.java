import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UVa_10646 {

	static final int NUM_CARDS = 52;

	static int getPoint(String card) {
		char face = card.charAt(0);
		int point = 10;
		try {
			point = Integer.parseInt("" + face);
		} catch (NumberFormatException ex) {
			// pass
		}
		return point;
	}

	static String shuffle(List<String> cards) {
		List<String> hand = cards.subList(0, NUM_CARDS - 25);
		List<String> pile = new ArrayList<>(cards.subList(NUM_CARDS - 25, NUM_CARDS));

		int Y = 0;
		int X;
		int remove;
		String topCard;
		for (int i = 1; i <= 3; ++i) {
			topCard = hand.get(hand.size() - 1);
			X = getPoint(topCard);
			Y += X;
			hand.remove(hand.size() - 1);
			remove = 10 - X;
			hand = hand.subList(0, hand.size() - remove);
		}

		hand.addAll(pile);
		return hand.get(Y - 1);
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int t = input.nextInt();
		List<String> cards;

		for (int i = 1; i <= t; ++i) {
			cards = new ArrayList<>();
			for (int j = 1; j <= NUM_CARDS; ++j)
				cards.add(input.next());
			System.out.println("Case " + i + ": " + shuffle(cards));
		}
	}

}
