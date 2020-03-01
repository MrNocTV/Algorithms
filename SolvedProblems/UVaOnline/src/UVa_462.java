import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Main {

	interface Rule {
		int apply(List<Card> cards);
	}

	static class Card {
		String face;
		String suit;

		Card(String face, String suit) {
			this.face = face;
			this.suit = suit;
		}

		int getPoint() {
			switch (face) {
				case "A":
					return 4;
				case "K":
					return 3;
				case "Q":
					return 2;
				case "J":
					return 1;
				default:
					return 0;
			}
		}

		@Override
		public String toString() {
			return face + suit;
		}
	}

	static class Rule1 implements Rule {
		@Override
		public int apply(List<Card> cards) {
			int points = 0;
			for (Card card : cards)
				points += card.getPoint();
			return points;
		}
	}

	static class Rule2 implements Rule {
		Map<String, List<Card>> suitToCardMap;

		public Rule2(Map<String, List<Card>> suitToCardMap) {
			this.suitToCardMap = suitToCardMap;
		}

		@Override
		public int apply(List<Card> cards) {
			int points = 0;
			for (Map.Entry<String, List<Card>> entry : suitToCardMap.entrySet()) {
				List<Card> cardsInSuit = entry.getValue();
				if (cardsInSuit.size() == 1 && cardsInSuit.get(0).face.equals("K")) {
					points += -1;
				}
			}
			return points;
		}
	}

	static class Rule3 extends Rule2 {

		public Rule3(Map<String, List<Card>> suitToCardMap) {
			super(suitToCardMap);
		}

		@Override
		public int apply(List<Card> cards) {
			int points = 0;
			for (Map.Entry<String, List<Card>> entry : suitToCardMap.entrySet()) {
				List<Card> cardsInSuit = entry.getValue();
				if (cardsInSuit.size() <= 2) {
					if (cardsInSuit.stream().filter(card -> card.face.equals("Q")).findFirst().isPresent())
						points += -1;
				}
			}
			return points;
		}
	}

	static class Rule4 extends Rule2 {

		public Rule4(Map<String, List<Card>> suitToCardMap) {
			super(suitToCardMap);
		}

		@Override
		public int apply(List<Card> cards) {
			int points = 0;
			for (Map.Entry<String, List<Card>> entry : suitToCardMap.entrySet()) {
				List<Card> cardsInSuit = entry.getValue();
				if (cardsInSuit.size() <= 3) {
					if (cardsInSuit.stream().filter(card -> card.face.equals("J")).findFirst().isPresent())
						points += -1;
				}
			}
			return points;
		}
	}

	static class Rule5 extends Rule2 {
		public Rule5(Map<String, List<Card>> suitToCardMap) {
			super(suitToCardMap);
		}

		@Override
		public int apply(List<Card> cards) {
			int points = 0;
			for (Map.Entry<String, List<Card>> entry : suitToCardMap.entrySet()) {
				List<Card> cardsInSuit = entry.getValue();
				if (cardsInSuit.size() == 2)
					points += 1;
			}
			return points;
		}
	}

	static class Rule6 extends Rule2 {
		public Rule6(Map<String, List<Card>> suitToCardMap) {
			super(suitToCardMap);
		}

		@Override
		public int apply(List<Card> cards) {
			int points = 0;
			for (Map.Entry<String, List<Card>> entry : suitToCardMap.entrySet()) {
				List<Card> cardsInSuit = entry.getValue();
				if (cardsInSuit.size() == 1)
					points += 2;
			}
			return points;
		}
	}

	static class Rule7 extends Rule2 {
		public Rule7(Map<String, List<Card>> suitToCardMap) {
			super(suitToCardMap);
		}
		@Override
		public int apply(List<Card> cards) {
			int points = 0;
			for (Map.Entry<String, List<Card>> entry : suitToCardMap.entrySet()) {
				List<Card> cardsInSuit = entry.getValue();
				if (cardsInSuit.size() == 0)
					points += 2;
			}
			return points;
		}
	}

	static class NoTrump implements Rule {
		Map<String, List<Card>> suitToCardMap;

		NoTrump(Map<String, List<Card>> suitToCardMap) {
			this.suitToCardMap = suitToCardMap;
		}

		/***
		 *
		 * @param cards
		 * @return 4 -> no-trump, otherwise smaller than 4
		 */
		@Override
		public int apply(List<Card> cards) {
			int stoppedCount = 0;
			for (Map.Entry<String, List<Card>> entry : suitToCardMap.entrySet()) {
				if (entry.getValue().stream().filter(card -> card.face.equals("A")).findFirst().isPresent()) {
					stoppedCount += 1;
					continue;
				}
				if (entry.getValue().size() >= 2) {
					if (entry.getValue().stream().filter(card -> card.face.equals("K")).findFirst().isPresent()) {
						stoppedCount += 1;
						continue;
					}
				}

				if (entry.getValue().size() >= 3) {
					if (entry.getValue().stream().filter(card -> card.face.equals("Q")).findFirst().isPresent()) {
						stoppedCount += 1;
						continue;
					}
				}
			}
			return stoppedCount;
		}
	}

	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String line;
			while ((line = br.readLine()) != null && !line.isEmpty()) {
				List<Card> cards = new ArrayList<>();
				Map<String, List<Card>> suitToCardMap = new HashMap<>();
				suitToCardMap.put("H", new ArrayList<>());
				suitToCardMap.put("D", new ArrayList<>());
				suitToCardMap.put("C", new ArrayList<>());
				suitToCardMap.put("S", new ArrayList<>());

				for (String token : line.split(" ")) {
					String face = token.substring(0, 1);
					String suit = token.substring(1);
					Card card = new Card(face, suit);
					cards.add(card);
					suitToCardMap.get(suit).add(card);
				}

				bid(cards, suitToCardMap);
			}

		}
	}

	public static void bid(List<Card> cards, Map<String, List<Card>> suitToCardMap) {
		List<Rule> rule1To4 = Arrays.asList(
				new Rule1(), new Rule2(suitToCardMap), new Rule3(suitToCardMap), new Rule4(suitToCardMap)
		);

		int points = 0;
		for (Rule rule : rule1To4) {
			points += rule.apply(cards);
		}

		if (points >= 16) {
			NoTrump noTrump = new NoTrump(suitToCardMap);
			int countStopped = noTrump.apply(cards);
			if (countStopped == 4) {
				System.out.println("BID NO-TRUMP");
				return;
			}
		}

		List<Rule> rule5To7 = Arrays.asList(
				new Rule5(suitToCardMap), new Rule6(suitToCardMap), new Rule7(suitToCardMap)
		);

		for (Rule rule : rule5To7)
			points += rule.apply(cards);

		if (points < 14) {
			System.out.println("PASS");
		} else {
			// get bid suit
			int maxLength = 0;
			for (Map.Entry<String, List<Card>> entry : suitToCardMap.entrySet()) {
				maxLength = Math.max(maxLength, entry.getValue().size());
			}

			for (String suit :  Arrays.asList("S", "H", "D", "C")) {
				if (suitToCardMap.get(suit).size() == maxLength) {
					System.out.println("BID " + suit);
					break;
				}
			}
		}
	}
}
