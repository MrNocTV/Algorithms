import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UVa_00555 {
	static Map<Character, Integer> suitMap = new HashMap<>();
	static Map<Character, Integer> faceMap = new HashMap<>();
	static Map<String, String> nextToMap = new HashMap<>();

	static {
		suitMap.put('C', 1);
		suitMap.put('D', 2);
		suitMap.put('S', 3);
		suitMap.put('H', 4);
	}

	static {
		faceMap.put('2', 1);
		faceMap.put('3', 2);
		faceMap.put('4', 3);
		faceMap.put('5', 4);
		faceMap.put('6', 5);
		faceMap.put('7', 6);
		faceMap.put('8', 7);
		faceMap.put('9', 8);
		faceMap.put('T', 9);
		faceMap.put('J', 10);
		faceMap.put('Q', 11);
		faceMap.put('K', 12);
		faceMap.put('A', 13);
	}

	static {
		nextToMap.put("N", "E");
		nextToMap.put("E", "S");
		nextToMap.put("S", "W");
		nextToMap.put("W", "N");
	}

	static Comparator<String> cardComparator = (c1, c2) -> {
		int suitCompare = suitMap.get(c1.charAt(0)) - suitMap.get(c2.charAt(0));
		if (suitCompare != 0)
			return suitCompare;
		return faceMap.get(c1.charAt(1)) - faceMap.get(c2.charAt(1));
	};
	
	static void outputResult(Map<String, List<String>> hands) {
		int count = 1;
		String curHand = "S";
		while (count <= 4) {
			System.out.println(curHand + ": " + String.join(" ", hands.get(curHand)));
			curHand = nextToMap.get(curHand);
			++count;
		}
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String dealer = input.next();
		Map<String, List<String>> hands;
		String dealTo;

		while (!dealer.equals("#")) {
			String temp1 = input.next();
			String temp2 = input.next();
			hands = new HashMap<>();
			hands.put("N", new ArrayList<>());
			hands.put("E", new ArrayList<>());
			hands.put("S", new ArrayList<>());
			hands.put("W", new ArrayList<>());

			dealTo = nextToMap.get(dealer);
			for (int i = 1; i < temp1.length(); i += 2) {
				hands.get(dealTo).add(temp1.substring(i - 1, i + 1));
				dealTo = nextToMap.get(dealTo);
			}

			for (int i = 1; i < temp2.length(); i += 2) {
				hands.get(dealTo).add(temp2.substring(i - 1, i + 1));
				dealTo = nextToMap.get(dealTo);
			}
			
			for(String s : hands.keySet()) {
				List<String> cards = hands.get(s);
				cards.sort(cardComparator);
			}
			
			outputResult(hands);
			
			dealer = input.next();
		}
	}
}
