import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class UVa_00401 {
    private static final Map<Character, Character> MIRROR_MAP;

    static {
        MIRROR_MAP = new HashMap<>();
        MIRROR_MAP.put('A', 'A');
        MIRROR_MAP.put('B', Character.MIN_VALUE);
        MIRROR_MAP.put('C', Character.MIN_VALUE);
        MIRROR_MAP.put('D', Character.MIN_VALUE);
        MIRROR_MAP.put('E', '3');
        MIRROR_MAP.put('3', 'E');
        MIRROR_MAP.put('F', Character.MIN_VALUE);
        MIRROR_MAP.put('G', Character.MIN_VALUE);
        MIRROR_MAP.put('H', 'H');
        MIRROR_MAP.put('I', 'I');
        MIRROR_MAP.put('J', 'L');
        MIRROR_MAP.put('K', Character.MIN_VALUE);
        MIRROR_MAP.put('L', 'J');
        MIRROR_MAP.put('M', 'M');
        MIRROR_MAP.put('N', Character.MIN_VALUE);
        MIRROR_MAP.put('O', 'O');
        MIRROR_MAP.put('P', Character.MIN_VALUE);
        MIRROR_MAP.put('Q', Character.MIN_VALUE);
        MIRROR_MAP.put('R', Character.MIN_VALUE);
        MIRROR_MAP.put('S', '2');
        MIRROR_MAP.put('2', 'S');
        MIRROR_MAP.put('T', 'T');
        MIRROR_MAP.put('U', 'U');
        MIRROR_MAP.put('V', 'V');
        MIRROR_MAP.put('W', 'W');
        MIRROR_MAP.put('X', 'X');
        MIRROR_MAP.put('Y', 'Y');
        MIRROR_MAP.put('Z', '5');
        MIRROR_MAP.put('5', 'Z');
        MIRROR_MAP.put('1', '1');
        MIRROR_MAP.put('4', Character.MIN_VALUE);
        MIRROR_MAP.put('6', Character.MIN_VALUE);
        MIRROR_MAP.put('7', Character.MIN_VALUE);
        MIRROR_MAP.put('8', '8');
        MIRROR_MAP.put('9', Character.MIN_VALUE);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty())
                    continue;
                if (isPalindrome(line)) {
                    if (isMirrored(line)) {
                        System.out.println(line + " -- " + "is a mirrored palindrome.\n");
                    } else {
                        System.out.println(line + " -- " + "is a regular palindrome.\n");
                    }
                } else {
                    if (isMirrored(line)) {
                        System.out.println(line + " -- " + "is a mirrored string.\n");
                    } else {
                        System.out.println(line + " -- " + "is not a palindrome.\n");
                    }
                }
            }
        }
    }

    private static boolean isPalindrome(String s) {
        int middle = s.length() / 2;
        for (int i = 0; i <= middle; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i))
                return false;
        }

        return true;
    }

    private static boolean isMirrored(String s) {
        int middle = s.length() / 2;
        for (int i = 0; i <= middle; i++) {
            if (MIRROR_MAP.get(s.charAt(i)) != s.charAt(s.length() - 1 - i))
                return false;
        }

        return true;
    }
}
