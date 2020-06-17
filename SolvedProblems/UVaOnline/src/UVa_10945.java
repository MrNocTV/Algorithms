import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UVa_10945 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line;

            while (!(line = br.readLine().trim()).equals("DONE")) {
                line = line.toLowerCase();
                line = line.replaceAll(" ", "")
                    .replaceAll("\\.", "")
                    .replaceAll(",", "")
                    .replaceAll("!", "")
                    .replaceAll("\\?", "");

                if (!isPalindrome(line)) {
                    System.out.println("Uh oh..");
                } else {
                    System.out.println("You won't be eaten!");
                }
            }
        }
    }

    private static boolean isPalindrome(String s) {
        int middle = s.length() / 2;
        for (int i = 0; i < middle; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i))
                return false;
        }

        return true;
    }
}
