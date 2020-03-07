import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UVa_10284 {

	static abstract class Piece {
		Set<Point> points;
		int x;
		int y;
		Type type;
		boolean isBlack;

		Piece(int x, int y, Type type, boolean isBlack, Set<Point> points) {
			this.x = x;
			this.y = y;
			this.type = type;
			this.points = points;
			this.isBlack = isBlack;
		}

		abstract Set<Point> explore();

		@Override
		public String toString() {
			return "Piece{" +
					"x=" + x +
					", y=" + y +
					", type=" + type.name() +
					", isBlack=" + isBlack +
					'}';
		}
	}

	static class Pawn extends Piece {
		Pawn(int x, int y, Type type, boolean isBlack, Set<Point> points) {
			super(x, y, type, isBlack, points);
		}

		@Override
		public Set<Point> explore() {
			Set<Point> result = new HashSet<>();
			if (this.isBlack) {
				Point p1 = new Point(x + 1, y - 1);
				Point p2 = new Point(x + 1, y + 1);
				if (p1.x < 8 && p1.y >= 0)
					result.add(p1);
				if (p2.x < 8 && p2.y < 8)
					result.add(p2);
			} else {
				Point p1 = new Point(x - 1, y - 1);
				Point p2 = new Point(x - 1, y + 1);
				if (p1.x >= 0 && p1.y >= 0)
					result.add(p1);
				if (p2.x >= 0 && p2.y < 8)
					result.add(p2);
			}

			return result;
		}
	}

	static class Queen extends Piece {

		Queen(int x, int y, Type type, boolean isBlack, Set<Point> points) {
			super(x, y, type, isBlack, points);
		}

		@Override
		public Set<Point> explore() {
			Set<Point> result = new HashSet<>();
			exploreTopAndBottom(result);
			exploreDiagonals(result);
			exploreLeftAndRight(result);
			return result;
		}

		void exploreTopAndBottom(Set<Point> result) {
			int top = x - 1;
			while (top >= 0) {
				Point p = new Point(top, y);
				if (points.contains(p)) {
					break;
				}
				result.add(p);
				top--;
			}

			int bottom = x + 1;
			while (bottom < 8) {
				Point p = new Point(bottom, y);
				if (points.contains(p)) {
					break;
				}
				result.add(p);
				bottom++;
			}
		}

		void exploreDiagonals(Set<Point> result) {
			int top = x - 1;
			int left = y - 1;
			while (top >= 0 && left >= 0) {
				Point p = new Point(top ,left);
				if (points.contains(p))
					break;
				result.add(p);
				top--;
				left--;
			}

			top = x - 1;
			int right = y + 1;
			while (top >= 0 && right < 8) {
				Point p = new Point(top, right);
				if (points.contains(p))
					break;
				result.add(p);
				top--;
				right++;
			}

			int bottom = x + 1;
			left = y - 1;
			while (bottom < 8 && left >= 0) {
				Point p = new Point(bottom, left);
				if (points.contains(p))
					break;
				result.add(p);
				bottom++;
				left--;
			}

			bottom = x + 1;
			right = y + 1;
			while (bottom < 8 && right < 8) {
				Point p = new Point(bottom, right);
				if (points.contains(p))
					break;
				result.add(p);
				bottom++;
				right++;
			}
		}

		void exploreLeftAndRight(Set<Point> result) {
			int left = y - 1;
			while (left >= 0) {
				Point p = new Point(x, left);
				if (points.contains(p))
					break;
				result.add(p);
				left--;
			}

			int right = y + 1;
			while (right < 8) {
				Point p = new Point(x, right);
				if (points.contains(p))
					break;
				result.add(p);
				right++;
			}
		}
	}

	static class Rook extends Queen {
		Rook(int x, int y, Type type, boolean isBlack, Set<Point> points) {
			super(x, y, type, isBlack, points);
		}

		@Override
		public Set<Point> explore() {
			Set<Point> result = new HashSet<>();
			exploreLeftAndRight(result);
			exploreTopAndBottom(result);
			return result;
		}
	}

	static class Bishop extends Queen {
		Bishop(int x, int y, Type type, boolean isBlack, Set<Point> points) {
			super(x, y, type, isBlack, points);
		}

		@Override
		public Set<Point> explore() {
			Set<Point> result = new HashSet<>();
			exploreDiagonals(result);
			return result;
		}
	}

	static class Knight extends Piece {
		Knight(int x, int y, Type type, boolean isBlack, Set<Point> points) {
			super(x, y, type, isBlack, points);
		}

		@Override
		public Set<Point> explore() {
			Set<Point> result = new HashSet<>();
			Point p = new Point(x - 2, y - 1);
			if (p.x >= 0 && p.y >= 0)
				result.add(p);
			p = new Point(x - 1, y - 2);
			if (p.x >= 0 && p.y >= 0)
				result.add(p);
			p = new Point(x - 2, y + 1);
			if (p.x >= 0 && y < 8)
				result.add(p);
			p = new Point(x - 1, y + 2);
			if (p.x >= 0 && y < 8)
				result.add(p);
			p = new Point(x + 1, y - 2);
			if (p.x < 8 && p.y >= 0)
				result.add(p);
			p = new Point(x + 2, y - 1);
			if (p.x < 8 && p.y >= 0)
				result.add(p);
			p = new Point(x + 1, y + 2);
			if (p.x < 8 && p.y < 8)
				result.add(p);
			p = new Point(x + 2, y + 1);
			if (p.x < 8 && p.y < 8)
				result.add(p);
			return result;
		}
	}

	static class King extends Piece {
		King(int x, int y, Type type, boolean isBlack, Set<Point> points) {
			super(x, y, type, isBlack, points);
		}

		@Override
		public Set<Point> explore() {
			Set<Point> result = new HashSet<>();
			Point p = new Point(x, y - 1);
			if (p.y >= 0)
				result.add(p);
			p = new Point(x, y + 1);
			if (p.y < 8)
				result.add(p);
			p = new Point(x + 1, y);
			if (p.x < 8)
				result.add(p);
			p = new Point(x - 1, y);
			if (p.x >= 0)
				result.add(p);
			p = new Point(x - 1, y - 1);
			if (p.x >= 0 && p.y >= 0)
				result.add(p);
			p = new Point(x - 1, y + 1);
			if (p.x >= 0 && p.y < 8)
				result.add(p);
			p = new Point(x + 1, y + 1);
			if (p.x < 8 && p.y < 8)
				result.add(p);
			p = new Point(x + 1, y - 1);
			if (p.x < 8 && p.y >= 0)
				result.add(p);
			return result;
		}
	}

	enum Type {
		PAWN, QUEEN, KING, ROOK, BISHOP, KNIGHT
	}

	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String line;
			while ((line = br.readLine()) != null && !line.trim().equals("")) {
				String[] rows = line.split("/");
				Set<Point> points = new HashSet<>();
				List<Piece> pieces = new ArrayList<>();
				for (int r = 0; r < rows.length; r++) {
					int c = 0;
					for (char ch : rows[r].toCharArray()) {
						if (Character.isDigit(ch)) {
							c += Character.digit(ch, 10);
						} else {
							Piece piece = charToPiece(ch, r, c, points);
							points.add(new Point(r, c));
							pieces.add(piece);
							c++;
						}
					}
				}
				Set<Point> attackedPoints = new HashSet<>();
				attackedPoints.addAll(points);
				for (Piece piece : pieces) {
					attackedPoints.addAll(piece.explore());
				}

				int notAttackedPoints = 0;

				for (int r = 0; r < 8; r++) {
					for (int c = 0; c < 8; c++) {
						if (!attackedPoints.contains(new Point(r, c))) {
							notAttackedPoints++;
						}
					}
				}
				System.out.println(notAttackedPoints);
			}
		}
	}

	static Piece charToPiece(char c, int row, int col, Set<Point> points) {
		switch (c) {
			case 'k':
			case 'K':
				return new King(row, col, Type.KING, c == 'k' ? true : false, points);
			case 'q':
			case 'Q':
				return new Queen(row, col, Type.QUEEN, c == 'q' ? true : false, points);
			case 'r':
			case 'R':
				return new Rook(row, col, Type.ROOK, c == 'r' ? true : false, points);
			case 'b':
			case 'B':
				return new Bishop(row, col, Type.BISHOP, c == 'b' ? true : false, points);
			case 'p':
			case 'P':
				return new Pawn(row, col, Type.PAWN, c == 'p' ? true : false, points);
			case 'n':
			case 'N':
				return new Knight(row, col, Type.KNIGHT, c == 'n' ? true : false, points);
		}
		return null;
	}

}
