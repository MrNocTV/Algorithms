import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class UVa_00156 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            Map<String, List<String>> sorted2OriginalWords = new HashMap<>();
            Set<String> sortedWords = new HashSet<>();
            SortedSet<String> result = new TreeSet<>();

            String line;

            while (!(line = br.readLine().trim()).equals("#")) {
                String[] tokens = line.split(" ");

                for (String token : tokens) {
                    String lowerCaseToken = token.toLowerCase();
                    lowerCaseToken = sortCharsInString(lowerCaseToken);
                    List<String> originalWords = sorted2OriginalWords.getOrDefault(lowerCaseToken, new LinkedList<>());
                    originalWords.add(token);
                    sorted2OriginalWords.put(lowerCaseToken, originalWords);
                    sortedWords.add(lowerCaseToken);
                }
            }


            for (List<String> originalWords : sorted2OriginalWords.values()) {
                if (originalWords.size() == 1) {
                    result.addAll(originalWords);
                }
            }

            for (String relativeAnagram : result) {
                System.out.println(relativeAnagram);
            }
        }
    }

    private static String sortCharsInString(String s) {
        return s.chars()
                .mapToObj(c -> (char) c)
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

}
