/*
 * Note: handA is BLACK, handB is WHITE
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UVa_10315 {

	static final Map<Character, Integer> facePoint = new HashMap<>();
	static {
		facePoint.put('2', 1);
		facePoint.put('3', 2);
		facePoint.put('4', 3);
		facePoint.put('5', 4);
		facePoint.put('6', 5);
		facePoint.put('7', 6);
		facePoint.put('8', 7);
		facePoint.put('9', 8);
		facePoint.put('T', 9);
		facePoint.put('J', 10);
		facePoint.put('Q', 11);
		facePoint.put('K', 12);
		facePoint.put('A', 13);
	}

	static final int STRAIGHT_FLUSH = 10;
	static final int FOUR_OF_A_KIND = 9;
	static final int FULL_HOUSE = 8;
	static final int FLUSH = 7;
	static final int STRAIGHT = 6;
	static final int THREE_OF_A_KIND = 5;
	static final int TWO_PAIRS = 4;
	static final int PAIR = 3;
	static final int HIGH_CARD = 2;

	public static enum WINNER {
		BLACK, WHITE, TIE
	};
	
	static boolean[] cases = new boolean[11];

	static boolean isStraightFlush(String[] hand) {
		return isStraight(hand) && isFlush(hand);
	}

	private static boolean isStraight(String[] hand) {
		for (int i = 1; i < hand.length; ++i) {
			char face2 = hand[i].charAt(0);
			char face1 = hand[i - 1].charAt(0);
			if (facePoint.get(face2) != (facePoint.get(face1) + 1))
				return false;
		}
		return true;
	}

	static boolean isFlush(String[] hand) {
		char suit = hand[0].charAt(1);
		for (int i = 1; i < hand.length; ++i) {
			if (hand[i].charAt(1) != suit)
				return false;
		}
		return true;
	}

	static int getHandPoint(String[] hand) {

		// straight flush
		if (isStraightFlush(hand))
			return STRAIGHT_FLUSH;

		Map<Character, Integer> freq = new HashMap<>();
		char face;
		for (int i = 0; i < hand.length; ++i) {
			face = hand[i].charAt(0);
			if (freq.containsKey(face))
				freq.put(face, freq.get(face) + 1);
			else
				freq.put(face, 1);
		}

		// four of a kind
		if (freq.size() == 2) {
			for (Integer f : freq.values())
				if (f == 4)
					return FOUR_OF_A_KIND;
		}

		// full house
		if (freq.size() == 2) {
			for (Integer f : freq.values())
				if (f == 2 || f == 3)
					return FULL_HOUSE;
		}

		// flush
		if (isFlush(hand))
			return FLUSH;

		// straight
		if (isStraight(hand))
			return STRAIGHT;

		// three of a kind
		if (freq.size() == 3) {
			for (Integer f : freq.values())
				if (f == 3)
					return THREE_OF_A_KIND;
		}

		// two pairs
		if (freq.size() == 3) {
			int count2 = 0;
			for (Integer f : freq.values())
				count2 += f == 2 ? 1 : 0;
			if (count2 == 2)
				return TWO_PAIRS;
		}

		if (freq.size() == 4) {
			int count1 = 0;
			for (Integer f : freq.values())
				count1 += f == 1 ? 1 : 0;
			if (count1 == 3)
				return PAIR;
		}

		return HIGH_CARD;
	}

	static WINNER whoWin(String[] handA, String[] handB) {
		WINNER winner = WINNER.TIE; // assume black wins

		int blackPoint = getHandPoint(handA);
		int whitePoint = getHandPoint(handB);
		cases[blackPoint] = true;
		cases[whitePoint] = true;

		if (blackPoint > whitePoint)
			winner = WINNER.BLACK;
		else if (blackPoint < whitePoint)
			winner = WINNER.WHITE;
		else {
			switch (blackPoint) {
			case STRAIGHT_FLUSH:
			case FLUSH:
			case STRAIGHT:
			case HIGH_CARD:
				winner = highCardRank(handA, handB);
				break;
			case FOUR_OF_A_KIND:
				winner = ThreeAndFourRank(handA, handB, 4);
				break;
			case FULL_HOUSE:
				winner = ThreeAndFourRank(handA, handB, 3);
				break;
			case THREE_OF_A_KIND:
				winner = ThreeAndFourRank(handA, handB, 3);
				break;
			case TWO_PAIRS:
				winner = pairRank(handA, handB, 2);
				break;
			case PAIR:
				winner = pairRank(handA, handB, 1);
				break;
			}
		}

		return winner;
	}
	
	// assume that both hands are sorted
	static WINNER highCardRank(String[] handA, String[] handB) {
		WINNER winner = WINNER.TIE;
		int comp;
		for(int i = handA.length - 1; i >= 0; --i) {
			comp = facePoint.get(handA[i].charAt(0)) - facePoint.get(handB[i].charAt(0));
			if (comp != 0) {
				winner = (comp > 0) ? WINNER.BLACK : WINNER.WHITE;
				break;
			}
		}
		
		return winner;
	}
	
	static WINNER ThreeAndFourRank(String[] handA, String[] handB, int k) {
		WINNER winner = WINNER.TIE;
		Character a = null;
		Character b = null;
		Map<Character, Integer> aMap = new HashMap<>();
		char face;
		for (int i = 0; i < handA.length; ++i) {
			face = handA[i].charAt(0);
			if (aMap.containsKey(face)) {
				int f = aMap.get(face);
				++f;
				aMap.put(face, f);
				if (f == k) {
					a = face;
					break;
				}
			}
			else
				aMap.put(face, 1);
		}

		Map<Character, Integer> bMap = new HashMap<>();
		for (int i = 0; i < handB.length; ++i) {
			face = handB[i].charAt(0);
			if (bMap.containsKey(face)) {
				int f = bMap.get(face);
				++f;
				bMap.put(face, f);
				if (f == k) {
					b = face;
					break;
				}
				
			}
			else
				bMap.put(face, 1);
		}
		
		int comp = facePoint.get(a) - facePoint.get(b);
		if (comp != 0) {
			if (comp > 0)
				winner = WINNER.BLACK;
			else
				winner = WINNER.WHITE;
		}
		
		return winner;
	}
	
	static WINNER pairRank(String[] handA, String[] handB, int pair) {
		WINNER winner = WINNER.TIE;
		List<String> aList = new ArrayList<>();
		List<String> bList = new ArrayList<>();
		Map<Character, Integer> aMap = new HashMap<>();
		Map<Character, Integer> bMap = new HashMap<>();
		
		char face;
		for (int i = 0; i < handA.length; ++i) {
			face = handA[i].charAt(0);
			if (aMap.containsKey(face))
				aMap.put(face, aMap.get(face)+1);
			else
				aMap.put(face, 1);
		}

		for (int i = 0; i < handB.length; ++i) {
			face = handB[i].charAt(0);
			if (bMap.containsKey(face))
				bMap.put(face, bMap.get(face)+1);
			else
				bMap.put(face, 1);
		}
		
		for(Character s : aMap.keySet()) {
			int f = aMap.get(s);
			if (f == 2) {
				aList.add(String.format("%c ", s));
			}
		}
		
		for(Character s : bMap.keySet()) {
			int f = bMap.get(s);
			if (f == 2) {
				bList.add(String.format("%c ", s));
			}
		}
		
		aList.sort(cardComparator);
		bList.sort(cardComparator);
		int comp;
		if (pair == 2) {
			for(int i = aList.size() - 1; i >= 0; --i) {
				comp = facePoint.get(aList.get(i).charAt(0)) - facePoint.get(bList.get(i).charAt(0));
				aMap.remove(aList.get(i).charAt(0));
				bMap.remove(bList.get(i).charAt(0));
				if (comp > 0) {
					winner = WINNER.BLACK;
					break;
				} else if (comp < 0) {
					winner = WINNER.WHITE;
					break;
				}
			}
			if (winner == WINNER.TIE) {
				char a = aMap.keySet().toArray(new Character[aMap.size()])[0];
				char b = bMap.keySet().toArray(new Character[bMap.size()])[0];
				comp = facePoint.get(a) - facePoint.get(b);
				if (comp > 0) {
					winner = WINNER.BLACK;
				} else if (comp < 0) {
					winner = WINNER.WHITE;
				}
			}
		} else if (pair == 1) {
			char a = aList.get(0).charAt(0);
			char b = bList.get(0).charAt(0);
			comp = facePoint.get(a) - facePoint.get(b);
			if (comp > 0) {
				winner = WINNER.BLACK;
			} else if (comp < 0) {
				winner = WINNER.WHITE;
			}
			
			if (winner == WINNER.TIE) {
				aList.clear();
				bList.clear();
				for(Character s : aMap.keySet()) {
					aList.add(String.format("%c ", s));
				}
				for(Character s : bMap.keySet()) {
					bList.add(String.format("%c ", s));
				}
				aList.sort(cardComparator);
				bList.sort(cardComparator);
				for(int i = aList.size() - 1; i >= 0; --i) {
					comp = facePoint.get(aList.get(i).charAt(0)) - facePoint.get(bList.get(i).charAt(0));
					if (comp > 0) {
						winner = WINNER.BLACK;
						break;
					} else if (comp < 0) {
						winner = WINNER.WHITE;
						break;
					}
				}
			}
		} 
		
		return winner;
	}
	
	static String[] sortHand(String[] hand) {
		List<String> list = new ArrayList<>(Arrays.asList(hand));
		list.sort(cardComparator);
		return list.toArray(new String[hand.length]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		String[] handA;
		String[] handB;
		while ((s=br.readLine())!=null) {
			if (s.trim().equals("")) break;
			String[] temp = s.split(" ");
			handA = new String[5];
			handB = new String[5];
			for (int i = 0; i < temp.length; ++i) {
				if (i < 5) {
					handA[i] = temp[i];
				} else {
					handB[i - 5] = temp[i];
				}
			}

			handA = sortHand(handA);
			handB = sortHand(handB);
			WINNER winner = whoWin(handA, handB);
			if (winner == WINNER.BLACK) {
				System.out.println("Black wins.");
			} else if (winner == WINNER.WHITE) {
				System.out.println("White wins.");
			} else {
				System.out.println("Tie.");
			}
		}
	}

	static Comparator<String> cardComparator = (c1, c2) -> {
		return facePoint.get(c1.charAt(0)) - facePoint.get(c2.charAt(0));
	};
	
}
