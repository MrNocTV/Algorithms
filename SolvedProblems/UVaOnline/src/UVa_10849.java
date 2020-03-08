import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UVa_10849 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int i = 1; i <= T; i++) {
			br.readLine();
			int t = Integer.parseInt(br.readLine());
			int n = Integer.parseInt(br.readLine());

			for (int j = 1; j <= t; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				Point bishopPosition = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				Point targetPosition = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				if (sameSquare(bishopPosition, targetPosition)) {
					System.out.println(0);
				} else if (onDifferentSquareColor(bishopPosition, targetPosition)) {
					System.out.println("no move");
				} else if (onSameDiagonal(bishopPosition, targetPosition)) {
					System.out.println(1);
				} else {
					System.out.println(2);
				}
			}
		}
	}

	private static boolean onSameDiagonal(Point bishopPosition, Point targetPosition) {
		int xDiff = bishopPosition.x - targetPosition.x;
		return  (targetPosition.y + xDiff == bishopPosition.y || targetPosition.y - xDiff == bishopPosition.y);
	}

	private static boolean onDifferentSquareColor(Point bishopPosition, Point targetPosition) {
		return isBlack(bishopPosition) != isBlack(targetPosition);
	}

	private static boolean isBlack(Point position) {
		if (position.x % 2 == 0) {
			return position.y % 2 != 0;
		}
		return position.y % 2 == 0;
	}

	private static boolean sameSquare(Point bishopPosition, Point targetPosition) {
		return bishopPosition.equals(targetPosition);
	}
}
