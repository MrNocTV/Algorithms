package Week1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Week1Exercise7 {
	private static final String TEMPLATE_END = "%TEMPLATE-END%";

	static class Location {
		int x;
		int y;

		public Location(int x, int y) {
			this.x = x;
			this.y = y;
		}

		static int distance(Location a, Location b) {
			return Math.max(Math.abs(a.x - b.x), Math.abs(a.y - b.y));
		}

		@Override
		public String toString() {
			return x + ", " + y;
		}
	}

	private static int typingTime(char[] cArr) {
		if (cArr.length == 1)
			return 0;
		int typingTime = 0;
		for (int i = 0; i < cArr.length - 1; ++i) {
			typingTime += Location.distance(CharLocationMap.get(cArr[i]), CharLocationMap.get(cArr[i + 1]));
		}
		return typingTime;
	}

	private static char[] filterOutSpaces(char[] cArr) {
		int newLength = 0;
		for (int i = 0; i < cArr.length; ++i)
			if (cArr[i] != ' ')
				++newLength;
		char[] result = new char[newLength];
		int i = 0;
		for (int j = 0; j < cArr.length; ++j) {
			if (cArr[j] != ' ')
				result[i++] = cArr[j];
		}

		return result;
	}

	private static final Map<Character, Location> CharLocationMap = new HashMap<Character, Location>();

	public static void main(String[] args) throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
			BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
			String[] inputs = br.readLine().trim().split(" ");
			int W = Integer.parseInt(inputs[0]);
			int H = Integer.parseInt(inputs[1]);
			// construct keyboard
			for (int i = 1; i <= H; ++i) {
				char[] cArr = br.readLine().trim().toCharArray();
				for (int j = 1; j <= cArr.length; ++j) {
					CharLocationMap.put(cArr[j - 1], new Location(i, j));
				}
			}
			System.out.println(CharLocationMap);
			String optimalLanguage = "";
			int optimalTypingTime = Integer.MAX_VALUE;

			for (int block = 1; block <= 3; ++block) {
				String language = br.readLine().trim();
				StringBuilder template = new StringBuilder();
				// read template start
				br.readLine();
				String line = "";
				while (true) {
					line = br.readLine().trim();
					if (line.equals(TEMPLATE_END))
						break;
					template.append(line);
				}
				char[] cArr = filterOutSpaces(template.toString().toCharArray());
				int time = typingTime(cArr);
				if (time < optimalTypingTime) {
					optimalLanguage = language;
					optimalTypingTime = time;
				}
			}

			bw.write(optimalLanguage + "\n");
			bw.write(optimalTypingTime + "\n");

			bw.close();
		}
	}
}
