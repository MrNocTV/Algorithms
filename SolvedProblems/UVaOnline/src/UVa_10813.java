import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UVa_10813 {

    private static class BingoNumber {
        int number;
        boolean marked;

        BingoNumber(int number) {
            this.marked = false;
            this.number = number;
        }
    }

    private static class Pair<K, V> {
        K x;
        V y;
        Pair(K k, V v) {
            this.x = k;
            this.y = v;
        }
    }

    private static boolean haveWon(BingoNumber[][] bingoNumbers) {
        // check all rounds
        for (int i = 0; i < 5; i++) {
            int count = 0;
            for (int j = 0; j < 5; j++) {
                count += bingoNumbers[i][j].marked ? 1 : 0;
            }
            if (count == 5) {
                return true;
            }
        }

        // check all columns
        for (int i = 0; i < 5; i++) {
            int count = 0;
            for (int j = 0; j < 5; j++) {
                count += bingoNumbers[j][i].marked ? 1 : 0;
            }
            if (count == 5) {
                return true;
            }
        }

        // check two diagonals
        int count = 0;
        for (int i = 0; i < 5; i++) {
            if (bingoNumbers[i][i].marked)
                count++;
        }
        if (count == 5) {
            return true;
        }
        count = 0;
        for (int i = 0; i < 5; i++) {
            if (bingoNumbers[i][4-i].marked)
                count++;
        }
        return count == 5;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int testCase = 1; testCase <= n; testCase++) {
            BingoNumber[][] bingoNumbers = new BingoNumber[5][5];
            Map<Integer, Pair<Integer, Integer>> numberPositionMap = new HashMap<>();
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (i == 2 && j == 2) {
                        bingoNumbers[i][j] = new BingoNumber(-1);
                        bingoNumbers[i][j].marked = true;
                    } else {
                        int num = input.nextInt();
                        bingoNumbers[i][j] = new BingoNumber(num);
                        numberPositionMap.put(num, new Pair<>(i, j));
                    }
                }
            }

            String result = null;
            for (int i = 1; i <= 75; i++) {
                int num = input.nextInt();
                Pair<Integer, Integer> position = numberPositionMap.getOrDefault(num, null);
                if (position != null) {
                    bingoNumbers[position.x][position.y].marked = true;
                }
                if (result == null && haveWon(bingoNumbers)) {
                   result = "BINGO after " + i + " numbers announced";
                }
            }
            System.out.println(result);
        }
    }

}
