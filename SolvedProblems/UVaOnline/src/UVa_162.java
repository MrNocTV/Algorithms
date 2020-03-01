import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Card {
	private static final List<String> FACE_CARDS = Arrays.asList("A", "J", "Q", "K");
	private String face;
	private String suit;

	Card(String token) {
		this.suit = token.charAt(0) + "";
		this.face = token.charAt(1) + "";
	}

	boolean isFaceCard() {
		return FACE_CARDS.contains(this.face);
	}

	int getFaceCardsNeed() {
		switch (face) {
			case "A":
				return 4;
			case "J":
				return 1;
			case "Q":
				return 2;
			case "K":
				return 3;
			default:
				return 0;
		}
	}
}

class Player {
	private Stack<Card> hand;

	Player() {
		this.hand = new Stack<>();
	}

	Card getNextCard() {
		if (this.hand.isEmpty())
			return null;
		return this.hand.pop();
	}

	void addCards(Stack<Card> cards) {
		Stack<Card> tempStack = new Stack<>();
		while (!this.hand.isEmpty())
			tempStack.push(this.hand.pop());

		while (!cards.isEmpty())
			this.hand.push(cards.pop());

		while (!tempStack.isEmpty())
			this.hand.push(tempStack.pop());
	}

	void addCard(Card card) {
		this.hand.push(card);
	}

	int getNumberOfCards() {
		return this.hand.size();
	}
}

public class UVa_162 {

	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String line;

			while ((line = br.readLine()) != null && !line.equals("#")) {
				List<String> tokens = new ArrayList<>();
				Player p1 = new Player();
				Player p2 = new Player();
				boolean dealerTurns = false;

				tokens.addAll(Arrays.asList(line.split(" ")));
				tokens.addAll(Arrays.asList(br.readLine().split(" ")));
				tokens.addAll(Arrays.asList(br.readLine().split(" ")));
				tokens.addAll(Arrays.asList(br.readLine().split(" ")));

				for (String token : tokens) {
					Card card = new Card(token);
					if (dealerTurns)
						p1.addCard(card);
					else
						p2.addCard(card);
					dealerTurns = !dealerTurns;
				}

				play(p1, p2);
			}
		}
	}

	static void play(Player p1, Player p2) {
		boolean dealerTurns = false;
		Stack<Card> pipe = new Stack<>();
		int sequenceCount = 0;
		while (true) {
			Card card;
			if (dealerTurns)
				card = p1.getNextCard();
			else
				card = p2.getNextCard();
			if (card == null) {
				break;
			}
			pipe.push(card);
			sequenceCount--;

			if (card.isFaceCard()) {
				sequenceCount = card.getFaceCardsNeed();
				dealerTurns = !dealerTurns;
			} else if (sequenceCount == 0) {
				if (dealerTurns) {
					p2.addCards(pipe);
					dealerTurns = false;
				} else {
					p1.addCards(pipe);
					dealerTurns = true;
				}
			} else if (sequenceCount < 0) {
				dealerTurns = !dealerTurns;
			}
		}

		if (!dealerTurns) {
			System.out.printf("%d%3d%n", 1, p1.getNumberOfCards());
		} else {
			System.out.printf("%d%3d%n", 2, p2.getNumberOfCards());
		}
	}
}
