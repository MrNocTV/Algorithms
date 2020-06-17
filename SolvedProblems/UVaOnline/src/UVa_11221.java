import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UVa_11221 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder sb = new StringBuilder();
            int n = Integer.parseInt(br.readLine().trim());
            for (int i = 1; i <= n; i++) {
                sb.append("Case #" + i + ":\n");
                String line = br.readLine().trim();
                line = removePunctuations(line);
                int k = (int) Math.sqrt(line.length());
                if (k*k != line.length()) {
                    sb.append("No magic :(\n");
                } else {
                    sb.append(isMagicSquarePalindrome(toCharMatrix(line, k)) ? k + "\n" : "No magic :(\n");
                }
            }
            System.out.println(sb.toString().trim());
        }
    }

    private static boolean isMagicSquarePalindrome(char[][] cArr) {
        StringBuilder readByRow = new StringBuilder();
        StringBuilder readByCol = new StringBuilder();
        StringBuilder readFromKRow = new StringBuilder();
        StringBuilder readFromKCol = new StringBuilder();

        for (int i = 0; i < cArr.length; i++) {
            for (int j = 0; j < cArr.length; j++) {
                readByRow.append(cArr[i][j]);
            }
        }

        for (int i = 0; i < cArr.length; i++) {
            for (int j = 0; j < cArr.length; j++) {
                readByCol.append(cArr[j][i]);
            }
        }

        for (int i = cArr.length - 1; i >= 0; i--) {
            for (int j = cArr.length - 1; j >= 0; j--) {
                readFromKRow.append(cArr[i][j]);
            }
        }

        for (int i = cArr.length - 1; i >= 0; i--) {
            for (int j = cArr.length - 1; j >= 0; j--) {
                readFromKCol.append(cArr[j][i]);
            }
        }

        return readByRow.toString().equals(readByCol.toString())
            && readByCol.toString().equals(readFromKRow.toString())
            && readFromKRow.toString().equals(readFromKCol.toString());
    }

    private static char[][] toCharMatrix(String s, int k) {
        char[][] cArr = new char[k][k];
        int cIndex = 0;

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                cArr[i][j] = s.charAt(cIndex++);
            }
        }

        return cArr;
    }

    private static String removePunctuations(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
