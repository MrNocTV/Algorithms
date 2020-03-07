import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UVa_10196 {

	static abstract class Piece {
		Set<Point> points;
		int x;
		int y;
		Type type;
		boolean isBlack;

		Piece(int x, int y, Type type, boolean isBlack) {
			this.x = x;
			this.y = y;
			this.type = type;
			this.isBlack = isBlack;
		}

		abstract boolean isCheckingTheKing(Piece king);
	}

	static class Pawn extends Piece {
		Pawn(int x, int y, Type type, boolean isBlack) {
			super(x, y, type, isBlack);
		}

		@Override
		public boolean isCheckingTheKing(Piece king) {
			if (this.isBlack) {
				return (x + 1 == king.x && y - 1 == king.y) || (x + 1 == king.x && y + 1 == king.y);
			} else {
				return (x - 1 == king.x && y - 1 == king.y) || (x - 1 == king.x && y + 1 == king.y);
			}
		}
	}

	static class Queen extends Piece {

		Queen(int x, int y, Type type, boolean isBlack) {
			super(x, y, type, isBlack);
		}

		Queen(int x, int y, Type type, boolean isBlack, Set<Point> points) {
			this(x, y, type, isBlack);
			this.points = points;
		}

		@Override
		public boolean isCheckingTheKing(Piece king) {
			return checkTopAndBottom(king) || checkLeftAndRight(king) || checkDiagonals(king);
		}

		boolean checkTopAndBottom(Piece king) {
			int top = x - 1;
			while (top >= 0) {
				if (top == king.x && y == king.y) {
					return true;
				}
				if (points.contains(new Point(top, y))) {
					break;
				}
				top--;
			}

			int bottom = x + 1;
			while (bottom < 8) {
				if (bottom == king.x && y == king.y) {
					return true;
				}
				if (points.contains(new Point(bottom, y))) {
					break;
				}
				bottom++;
			}

			return false;
		}

		boolean checkDiagonals(Piece king) {
			int top = x - 1;
			int left = y - 1;
			while (top >= 0 && left >= 0) {
				if (top == king.x && left == king.y)
					return true;
				if (points.contains(new Point(top, left)))
					break;
				top--;
				left--;
			}

			top = x - 1;
			int right = y + 1;
			while (top >= 0 && right < 8) {
				if (top == king.x && right == king.y)
					return true;
				if (points.contains(new Point(top, right)))
					break;
				top--;
				right++;
			}

			int bottom = x + 1;
			left = y - 1;
			while (bottom < 8 && left >= 0) {
				if (bottom == king.x && left == king.y)
					return true;
				if (points.contains(new Point(bottom, left)))
					break;
				bottom++;
				left--;
			}

			bottom = x + 1;
			right = y + 1;
			while (bottom < 8 && right < 8) {
				if (bottom == king.x && right == king.y)
					return true;
				if (points.contains(new Point(bottom, right)))
					break;
				bottom++;
				right++;
			}

			return false;
		}

		boolean checkLeftAndRight(Piece king) {
			int left = y - 1;
			while (left >= 0) {
				if (x == king.x && left == king.y)
					return true;
				if (points.contains(new Point(x, left)))
					break;
				left--;
			}

			int right = y + 1;
			while (right < 8) {
				if (x == king.x && right == king.y)
					return true;
				if (points.contains(new Point(x, right)))
					break;
				right++;
			}
			return false;
		}
	}

	static class Rook extends Queen {
		Rook(int x, int y, Type type, boolean isBlack, Set<Point> points) {
			super(x, y, type, isBlack, points);
		}

		@Override
		public boolean isCheckingTheKing(Piece king) {
			return checkTopAndBottom(king) || checkLeftAndRight(king);
		}
	}

	static class Bishop extends Queen {
		Bishop(int x, int y, Type type, boolean isBlack, Set<Point> points) {
			super(x, y, type, isBlack, points);
		}

		@Override
		public boolean isCheckingTheKing(Piece king) {
			return checkDiagonals(king);
		}
	}

	static class Knight extends Piece {
		Knight(int x, int y, Type type, boolean isBlack) {
			super(x, y, type, isBlack);
		}

		@Override
		public boolean isCheckingTheKing(Piece king) {
			if (x - 2 == king.x && y - 1 == king.y)
				return true;
			if (x - 1 == king.x && y - 2 == king.y)
				return true;
			if (x - 2 == king.x && y + 1 == king.y)
				return true;
			if (x - 1 == king.x && y + 2 == king.y)
				return true;
			if (x + 1 == king.x && y - 2 == king.y)
				return true;
			if (x + 2 == king.x && y - 1 == king.y)
				return true;
			if (x + 1 == king.x && y + 2 == king.y)
				return true;
			if (x + 2 == king.x && y + 1 == king.y)
				return true;
			return false;
		}
	}

	static class King extends Piece {
		King(int x, int y, Type type, boolean isBlack) {
			super(x, y, type, isBlack);
		}

		@Override
		public boolean isCheckingTheKing(Piece king) {
			return false;
		}
	}

	enum Type {
		PAWN, QUEEN, KING, ROOK, BISHOP, KNIGHT
	}

	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String line;
			int game = 1;

			while (true) {
				Set<Point> points = new HashSet<>();
				List<Piece> blackPieces = new ArrayList<>();
				List<Piece> whitePieces = new ArrayList<>();
				Piece blackKing = null;
				Piece whiteKing = null;
				int row = 0;
				while ((line = br.readLine()) != null && !line.trim().equals("")) {
					for (int col = 0; col < line.length(); col++) {
						Piece piece = charToPiece(line.charAt(col), row, col, points);
						if (piece == null)
							continue;
						points.add(new Point(row, col));
						if (piece.isBlack) {
							if (piece.type == Type.KING) {
								blackKing = piece;
							} else {
								blackPieces.add(piece);
							}
						} else {
							if (piece.type == Type.KING) {
								whiteKing = piece;
							} else {
								whitePieces.add(piece);
							}
						}
					}
					row++;
				}

				if (points.size() == 0) {
					// empty board
					break;
				}

				if (isKingInCheck(whitePieces, blackKing)) {
					System.out.println("Game #" + game + ": black king is in check.");
				} else if (isKingInCheck(blackPieces, whiteKing)) {
					System.out.println("Game #" + game + ": white king is in check.");
				} else {
					System.out.println("Game #" + game + ": no king is in check.");
				}
				game++;
			}
		}
	}

	static boolean isKingInCheck(List<Piece> pieces, final Piece king) {
		return pieces.stream().anyMatch(piece -> piece.isCheckingTheKing(king));
	}

	static Piece charToPiece(char c, int row, int col, Set<Point> points) {
		switch (c) {
			case 'k':
			case 'K':
				return new King(row, col, Type.KING, c == 'k' ? true : false);
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
				return new Pawn(row, col, Type.PAWN, c == 'p' ? true : false);
			case 'n':
			case 'N':
				return new Knight(row, col, Type.KNIGHT, c == 'n' ? true : false);
		}
		return null;
	}

}
