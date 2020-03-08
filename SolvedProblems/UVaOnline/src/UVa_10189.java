import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UVa_10189 {

	private static final char MINE = '*';

	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String line;
			int x = 1;

			while ((line = br.readLine()) != null) {
				if (line.trim().equals("0 0"))
					break;
				StringTokenizer st = new StringTokenizer(line);
				int n = Integer.parseInt(st.nextToken());
				int m = Integer.parseInt(st.nextToken());

				char[][] fields = new char[n][m];
				for (int i = 0; i < n; i++)
					fields[i] = br.readLine().toCharArray();
				discoverMines(fields);
				System.out.println(((x > 1) ? "\n" : "") + "Field #" + x++ + ":");
				for (char[] row : fields) {
					System.out.println(String.valueOf(row));
				}
			}
		}
	}

	private static void discoverMines(char[][] fields) {
		for (int r = 0; r < fields.length; r++) {
			for (int c = 0; c < fields[r].length; c++) {
				if (fields[r][c] == MINE || Character.isDigit(fields[r][c]))
					continue;
				// explore eight directions
				int minesCount = 0;
				if (r - 1 >= 0 && c - 1 >= 0 && fields[r-1][c-1] == MINE)
					minesCount++;
				if (r - 1 >= 0 && fields[r-1][c] == MINE)
					minesCount++;
				if (r - 1 >= 0 && c + 1 < fields[r].length && fields[r-1][c+1] == MINE)
					minesCount++;
				if (c + 1 < fields[r].length && fields[r][c+1] == MINE)
					minesCount++;
				if (r + 1 < fields.length && c + 1 < fields[r].length && fields[r+1][c+1] == MINE)
					minesCount++;
				if (r + 1 < fields.length && fields[r+1][c] == MINE)
					minesCount++;
				if (r + 1 < fields.length && c - 1 >= 0 && fields[r+1][c-1] == MINE)
					minesCount++;
				if (c - 1 >= 0 && fields[r][c-1] == MINE)
					minesCount++;
				fields[r][c] = Character.forDigit(minesCount, 10);
			}
		}
	}

}
