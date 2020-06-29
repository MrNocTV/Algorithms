import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class UVa_00195 {

    private static final Map<Character, Integer> CHARACTER_INTEGER_MAP;

    static {
        CHARACTER_INTEGER_MAP = new HashMap<>();
        CHARACTER_INTEGER_MAP.put('A', 1);
        CHARACTER_INTEGER_MAP.put('a', 2);
        CHARACTER_INTEGER_MAP.put('B', 3);
        CHARACTER_INTEGER_MAP.put('b', 4);
        CHARACTER_INTEGER_MAP.put('C', 5);
        CHARACTER_INTEGER_MAP.put('c', 6);
        CHARACTER_INTEGER_MAP.put('D', 7);
        CHARACTER_INTEGER_MAP.put('d', 8);
        CHARACTER_INTEGER_MAP.put('E', 9);
        CHARACTER_INTEGER_MAP.put('e', 10);
        CHARACTER_INTEGER_MAP.put('F', 11);
        CHARACTER_INTEGER_MAP.put('f', 12);
        CHARACTER_INTEGER_MAP.put('G', 13);
        CHARACTER_INTEGER_MAP.put('g', 14);
        CHARACTER_INTEGER_MAP.put('H', 15);
        CHARACTER_INTEGER_MAP.put('h', 16);
        CHARACTER_INTEGER_MAP.put('I', 17);
        CHARACTER_INTEGER_MAP.put('i', 18);
        CHARACTER_INTEGER_MAP.put('J', 19);
        CHARACTER_INTEGER_MAP.put('j', 20);
        CHARACTER_INTEGER_MAP.put('K', 21);
        CHARACTER_INTEGER_MAP.put('k', 22);
        CHARACTER_INTEGER_MAP.put('L', 23);
        CHARACTER_INTEGER_MAP.put('l', 24);
        CHARACTER_INTEGER_MAP.put('M', 25);
        CHARACTER_INTEGER_MAP.put('m', 26);
        CHARACTER_INTEGER_MAP.put('N', 27);
        CHARACTER_INTEGER_MAP.put('n', 28);
        CHARACTER_INTEGER_MAP.put('O', 29);
        CHARACTER_INTEGER_MAP.put('o', 30);
        CHARACTER_INTEGER_MAP.put('P', 31);
        CHARACTER_INTEGER_MAP.put('p', 32);
        CHARACTER_INTEGER_MAP.put('Q', 33);
        CHARACTER_INTEGER_MAP.put('q', 34);
        CHARACTER_INTEGER_MAP.put('R', 35);
        CHARACTER_INTEGER_MAP.put('r', 36);
        CHARACTER_INTEGER_MAP.put('S', 37);
        CHARACTER_INTEGER_MAP.put('s', 38);
        CHARACTER_INTEGER_MAP.put('T', 39);
        CHARACTER_INTEGER_MAP.put('t', 40);
        CHARACTER_INTEGER_MAP.put('U', 41);
        CHARACTER_INTEGER_MAP.put('u', 42);
        CHARACTER_INTEGER_MAP.put('V', 43);
        CHARACTER_INTEGER_MAP.put('v', 44);
        CHARACTER_INTEGER_MAP.put('W', 45);
        CHARACTER_INTEGER_MAP.put('w', 46);
        CHARACTER_INTEGER_MAP.put('X', 47);
        CHARACTER_INTEGER_MAP.put('x', 48);
        CHARACTER_INTEGER_MAP.put('Y', 49);
        CHARACTER_INTEGER_MAP.put('y', 50);
        CHARACTER_INTEGER_MAP.put('Z', 51);
        CHARACTER_INTEGER_MAP.put('z', 52);
    }

    private static String joinCharArrayToString(Character[] cArray) {
        StringBuilder sb = new StringBuilder();
        for (Character c : cArray)
            sb.append(c);
        return sb.toString();
    }

    private static class CustomCharComparator implements Comparator<Character> {

        @Override
        public int compare(Character c1, Character c2) {
            return CHARACTER_INTEGER_MAP.get(c1) - CHARACTER_INTEGER_MAP.get(c2);
        }
    }

    public static boolean findNextPermutation(Character[] cArray) {
        if (cArray.length <= 1)
            return false;
        int last = cArray.length - 2;

        // find the longest non-increasing suffix
        // and find the pivot
        while (last >= 0) {
            if (CHARACTER_INTEGER_MAP.get(cArray[last]) < CHARACTER_INTEGER_MAP.get(cArray[last + 1])) {
                break;
            }
            last--;
        }

        // If there is no increasing pair
        // there is no higher order permutation
        if (last < 0)
            return false;

        int nextGreater = cArray.length - 1;

        // Find the rightmost successor to the pivot
        for (int i = cArray.length - 1; i > last; i--) {
            if (CHARACTER_INTEGER_MAP.get(cArray[i]) > CHARACTER_INTEGER_MAP.get(cArray[last])) {
                nextGreater = i;
                break;
            }
        }

        // Swap the successor and the pivot
        cArray = swap(cArray, nextGreater, last);

        // Reverse the suffix
        reverse(cArray, last + 1, cArray.length - 1);

        return true;
    }

    public static Character[] swap(Character[] data, int left, int right) {
        char temp = data[left];
        data[left] = data[right];
        data[right] = temp;

        return data;
    }

    public static Character[] reverse(Character[] data, int left, int right) {
        while (left < right) {
            char temp = data[left];
            data[left++] = data[right];
            data[right--] = temp;
        }

        return data;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int n = Integer.parseInt(br.readLine());

            for (int test = 1; test <= n; test++) {
                List<String> sortedResult = new ArrayList<>();
                String s = br.readLine().trim();
                Character[] cArray = new Character[s.length()];
                for (int i = 0; i < s.length(); i++)
                    cArray[i] = s.charAt(i);
                Arrays.sort(cArray, new CustomCharComparator());

                sortedResult.add(joinCharArrayToString(cArray));

                while (findNextPermutation(cArray)) {
                    sortedResult.add(joinCharArrayToString(cArray));
                }

                for (String permutation : sortedResult) {
                    System.out.println(permutation);
                }
            }
        }
    }

}
