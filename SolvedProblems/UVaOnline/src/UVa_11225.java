import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class UVa_11225 {

	static boolean isOudler(String card) {
		if (card.equals("one of trumps") || card.equals("twenty-one of trumps"))
			return true;

		if (card.equals("fool"))
			return true;

		return false;
	}

	static double getPoint(String card) {
		double point = 0.5;

		if (card.startsWith("king") || isOudler(card))
			point = 4.5;
		else if (card.startsWith("queen"))
			point = 3.5;
		else if (card.startsWith("knight"))
			point = 2.5;
		else if (card.startsWith("jack"))
			point = 1.5;
		return point;
	}

	static double totalPoint(List<String> cardsWon) {
		double total = 0;
		for (String card : cardsWon) {
			total += getPoint(card);
		}
		return total;
	}

	static double pointNeedToWin(List<String> cards) {
		double points = 0;
		int countOudlers = 0;
		for (String card : cards) {
			countOudlers += (isOudler(card) ? 1 : 0);
		}
		if (countOudlers == 0)
			points = 56;
		else if (countOudlers == 1)
			points = 51;
		else if (countOudlers == 2)
			points = 41;
		else if (countOudlers == 3)
			points = 36;
		return points;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder result = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		List<String> cards;
		int n;

		for (int i = 1; i <= t; ++i) {
			cards = new ArrayList<>();
			n = Integer.parseInt(br.readLine());
			for (int j = 1; j <= n; ++j)
				cards.add(br.readLine().trim());
			double requiredPoints = pointNeedToWin(cards);
			double totalPoints = totalPoint(cards);
			result.append("Hand #" + i + "\n");
			if (totalPoints >= requiredPoints) {
				result.append(String.format("Game won by %d point(s).%n%n", (int) (totalPoints - requiredPoints)));
			} else {
				result.append(String.format("Game lost by %d point(s).%n%n", (int) (requiredPoints - totalPoints)));
			}
		}
		
		System.out.println(result.toString().trim());
	}

}
