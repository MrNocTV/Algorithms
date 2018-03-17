import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class UVa_119 {

	static final int ADD = 0;
	static final int LOSE = 1;

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n;
		Map<String, Integer[]> gainLostMap;
		int init;
		int value;
		int to;
		StringBuilder result = new StringBuilder();

		try {
			while (true) {
				gainLostMap = new HashMap<String, Integer[]>();
				n = Integer.parseInt(br.readLine());
				String[] people = br.readLine().trim().split(" ");
				gainLostMap = Arrays.stream(people).collect(Collectors.toMap(e -> e, e -> new Integer[2]));
				for (String key : gainLostMap.keySet())
					Arrays.fill(gainLostMap.get(key), 0);

				for (int i = 0; i < gainLostMap.size(); ++i) {
					String[] temp = br.readLine().trim().split(" ");
					init = Integer.parseInt(temp[1]);
					to = Integer.parseInt(temp[2]);
					if (init == 0 || to == 0)
						value = -1;
					else
						value = init / to;

					if (value != -1) {
						for (int j = 3; j < temp.length; ++j)
							gainLostMap.get(temp[j])[ADD] += value;
						gainLostMap.get(temp[0])[LOSE] += value * to;
					}
				}

				for (String person : people) {
					result.append(person + " " + (gainLostMap.get(person)[ADD] - gainLostMap.get(person)[LOSE]));
					result.append("\n");
				}
				result.append("\n");
			}
		} catch (Exception e) {
			// trim to get rid of the last "\n"
			// otherwise get presentation error
			String s = result.toString().trim();
			System.out.println(s);
		}
	}

}
