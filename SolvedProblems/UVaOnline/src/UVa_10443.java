import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UVa_10443 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int testCases = Integer.valueOf(br.readLine().trim());
            for (int t = 1; t <= testCases; t++) {
                String[] tokens = br.readLine().trim().split(" ");
                int r = Integer.valueOf(tokens[0]);
                int c = Integer.valueOf(tokens[1]);
                int n = Integer.valueOf(tokens[2]);
                if (r == 0 || c == 0) {
                    if (t < testCases) System.out.println();
                    br.readLine();
                } else {
                    char[][] grid = new char[r][c];
                    for (int i = 0; i < grid.length; i++) {
                        grid[i] = br.readLine().trim().toCharArray();
                    }
                    for (int i = 1; i <= n; i++) {
                        grid = executeWars(grid);
                    }
                    printlnGrid(grid);
                    if (t < testCases) System.out.println();
                }
            }
        }
    }

    private static void printlnGrid(char[][] grid) {
        for (char[] row : grid) {
            for (char c : row)
                System.out.print(c);
            System.out.println();
        }
    }

    private static char[][] executeWars(char[][] grid) {
        char[][] newGrid = new char[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                update(grid, i - 1, j, grid[i][j], newGrid);
                update(grid, i, j - 1, grid[i][j], newGrid);
                update(grid, i, j + 1, grid[i][j], newGrid);
                update(grid, i + 1, j, grid[i][j], newGrid);
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (newGrid[i][j] == '\u0000') {
                    newGrid[i][j] = grid[i][j];
                }
            }
        }
        return newGrid;
    }

    private static void update(char[][] grid, int i, int j, char c, char[][] newGrid) {
        if (i >= 0 && i < grid.length && j >= 0 && j < grid[i].length) {
            if (getBeaten(grid[i][j], c)) {
                newGrid[i][j] = c;
            }
        }
    }

    /**
     *
     * @param c1
     * @param c2
     * @return true if c1 is beaten by c2
     */
    private static boolean getBeaten(char c1, char c2) {
        if (c1 == 'R') {
            return c2 == 'P';
        } else if (c1 == 'S') {
            return c2 == 'R';
        } else if (c1 == 'P') {
            return c2 == 'S';
        }
        return false;
    }
}
