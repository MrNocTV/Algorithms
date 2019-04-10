package Week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.SortedMap;
import java.util.TreeMap;

public class Week2Exercise3 {
	private static final String QUERY = "?";

	public static void main(String[] args) throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
			BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
			LinkedList<Integer> queue = new LinkedList<>();
			SortedMap<Integer, Integer> freqMap = new TreeMap<>();
			int N = Integer.parseInt(br.readLine().trim());
			for (int i = 1; i <= N; ++i) {
				String[] tokens = br.readLine().trim().split(" ");
				if (tokens.length == 1) {
					if (tokens[0].equals(QUERY)) {
						bw.write(freqMap.firstKey() + "\n");
					} else {
						Integer val = queue.removeFirst();
						int freq = freqMap.get(val);
						if (freq == 1) {
							freqMap.remove(val);
						} else {
							freqMap.put(val, freq - 1);
						}
					}
				} else {
					Integer val = Integer.parseInt(tokens[1]);
					queue.add(val);
					freqMap.put(val, freqMap.getOrDefault(val, 0) + 1);
				}
			}
			bw.close();
		}
	}

}
