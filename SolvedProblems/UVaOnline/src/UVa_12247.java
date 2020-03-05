import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UVa_12247 {

	/*
		There are 3 main cases
		1> princess has 2 cards > 2 cards currently in prince's hand: -1
		2> princess has no cards > one of 2 cards currently in prince's hand: 1 or smallest card in princess's hand + 1
		3> princess has 1 card > one of 2 cards currently in prince's hand. This one is tricky.
	 */

	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String line;
			while ((line = br.readLine()) != null && !line.startsWith("0 ")) {
				List<Integer> cards = Arrays.asList(line.split(" "))
						.stream()
						.map(Integer::new)
						.collect(Collectors.toList());
				List<Integer> princessHand = new ArrayList<>(cards.subList(0, 3));
				List<Integer> princeHand = new ArrayList<>(cards.subList(3, 5));
				Collections.sort(princessHand);

				int largerCard = countLargerCards(princessHand, princeHand);
				if (largerCard == 2) {
					System.out.println(-1);
				} else if (largerCard == 1) {
					Integer largestPrinceCard = princeHand.stream().max(Integer::compareTo).get();
					Integer smallestPrincessCard = princessHand.stream().min(Integer::compareTo).get();
					List<Integer> copyPrincessHand = new ArrayList<>(princessHand);
					princeHand.remove(largestPrinceCard);
					copyPrincessHand.remove(smallestPrincessCard);

					Integer smallestLarger = smallestLargerThan(copyPrincessHand, princeHand.get(0));
					if (smallestLarger == -1) {
						int minWin = copyPrincessHand.stream().max(Integer::compareTo).get() + 1;
						getMinWinCard(minWin, cards);
					} else {
						copyPrincessHand.remove(smallestLarger);
						int minWin = copyPrincessHand.get(0) + 1;
						getMinWinCard(minWin, cards);
					}

				} else if (largerCard == 0) {
					int i = 1;
					while (i <= 52) {
						if (!cards.contains(i)) {
							System.out.println(i);
							break;
						}
						i++;
					}
				}
			}
		}
	}

	private static int countLargerCards(List<Integer> princessHand, List<Integer> princeHand) {
		int countLarger = 0;
		List<Integer> copyPrinceHand = new ArrayList<>(princeHand);

		for (Integer card : princessHand) {
			Integer min = copyPrinceHand.stream().min(Integer::compareTo).get();
			if (card > min) {
				copyPrinceHand.remove(min);
				countLarger++;
			}

			if (copyPrinceHand.isEmpty())
				break;
		}

		return countLarger;
	}

	private static Integer smallestLargerThan(List<Integer> cards, Integer target) {
		Integer smallest = Integer.MAX_VALUE;
		for (Integer card : cards) {
			if (card > target && card < smallest)
				smallest = card;
		}
		return smallest == Integer.MAX_VALUE ? -1 : smallest;
	}

	private static void getMinWinCard(int minWin, List<Integer> cards) {
		boolean found = false;
		while (minWin <= 52) {
			if (!cards.contains(minWin)) {
				found = true;
				break;
			}
			minWin++;
		}

		if (found) {
			System.out.println(minWin);
		} else {
			System.out.println(-1);
		}
	}
}
