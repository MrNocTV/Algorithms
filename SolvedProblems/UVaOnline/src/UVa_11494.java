import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UVa_11494 {
	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String line;

			while ((line = br.readLine()) != null) {
				if (line.trim().equals("0 0 0 0"))
					break;
				StringTokenizer st = new StringTokenizer(line);
				Point queenPosition = new Point(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()));
				Point targetPosition = new Point(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()));

				if (onSameSquare(queenPosition, targetPosition)) {
					System.out.println(0);
				} else if (onSameRowOrCol(queenPosition, targetPosition) || onSameDiagonal(queenPosition, targetPosition)) {
					System.out.println(1);
				} else {
					System.out.println(2);
				}
			}
		}
	}

	private static boolean onSameDiagonal(Point srcPosition, Point targetPosition) {
		int xDiff = srcPosition.x - targetPosition.x;
		return  (targetPosition.y + xDiff == srcPosition.y || targetPosition.y - xDiff == srcPosition.y);
	}

	private static boolean onSameRowOrCol(Point srcPosition, Point targetPosition) {
		return srcPosition.x == targetPosition.x || srcPosition.y == targetPosition.y;
	}

	private static boolean onSameSquare(Point srcPosition, Point targetPosition) {
		return srcPosition.equals(targetPosition);
	}
}
