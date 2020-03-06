import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class UVa_255 {
	private static int[] topBoundaries = {0, 1, 2, 3, 4, 5, 6, 7};
	private static int[] leftBoundaries = {0, 8, 16, 24, 32, 40, 48, 56};
	private static int[] bottomBoundaries = {56, 57, 58, 59, 60, 61, 62, 63};
	private static int[] rightBoundaries = {7, 15, 23, 31, 39, 47, 55, 63};

	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String line;

			while ((line = br.readLine()) != null) {
				String[] token = line.split(" ");
				int kingPosition = Integer.parseInt(token[0]);
				int queenPosition = Integer.parseInt(token[1]);
				int queenMove = Integer.parseInt(token[2]);
				if (kingPosition == queenPosition) {
					System.out.println("Illegal state");
				} else {
					Set<Integer> legalMovesOfQueen = getLegalMovesOfQueen(kingPosition, queenPosition);
					Set<Integer> legalMovesOfKing = getLegalMovesOfKing(kingPosition, queenPosition);
					Set<Integer> notAllowedMoves = new HashSet<>(legalMovesOfQueen);
					notAllowedMoves.retainAll(legalMovesOfKing);
					if (notAllowedMoves.contains(queenMove)) {
						System.out.println("Move not allowed");
					} else if (legalMovesOfQueen.contains(queenMove)) {
						legalMovesOfQueen = getLegalMovesOfQueen(kingPosition, queenMove);
						legalMovesOfKing = getLegalMovesOfKing(kingPosition, queenMove);
						legalMovesOfKing.removeAll(legalMovesOfQueen);
						if (legalMovesOfKing.size() > 0) {
							System.out.println("Continue");
						} else {
							System.out.println("Stop");
						}
					} else {
						System.out.println("Illegal move");
					}
				}
			}
		}
	}

	private static Set<Integer> getLegalMovesOfQueen(int kingPosition, int queenPosition) {
		Set<Integer> result = new HashSet<>();
		int row = queenPosition / 8;
		int col = queenPosition % 8;

		int left = queenPosition - 1;
		while (left >= leftBoundaries[row]) {
			if (left == kingPosition) {
				break;
			}
			result.add(left);
			left--;
		}

		int right = queenPosition + 1;
		while (right <= rightBoundaries[row]) {
			if (right == kingPosition) {
				break;
			}
			result.add(right);
			right++;
		}

		int top = queenPosition - 8;
		while (top >= topBoundaries[col]) {
			if (top == kingPosition) {
				break;
			}
			result.add(top);
			top -= 8;
		}

		int bottom = queenPosition + 8;
		while (bottom <= bottomBoundaries[col]) {
			if (bottom == kingPosition) {
				break;
			}
			result.add(bottom);
			bottom += 8;
		}

		return result;
	}

	private static Set<Integer> getLegalMovesOfKing(int kingPosition, int queenPosition) {
		Set<Integer> result = new HashSet<>();
		int row = kingPosition / 8;
		int col = kingPosition % 8;

		int left = kingPosition - 1;
		if (left >= leftBoundaries[row] && left != queenPosition) {
			result.add(left);
		}

		int right = kingPosition + 1;
		if (right <= rightBoundaries[row] && right != queenPosition) {
			result.add(right);
		}

		int top = kingPosition - 8;
		if (top >= topBoundaries[col] && top != queenPosition) {
			result.add(top);
		}

		int bottom = kingPosition + 8;
		if (bottom <= bottomBoundaries[col] && bottom != queenPosition) {
			result.add(bottom);
		}

		return result;
	}
}
