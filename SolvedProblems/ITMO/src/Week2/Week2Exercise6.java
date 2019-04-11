package Week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Week2Exercise6 {
	static class SnowMan {
		private LinkedList<Integer> balls = new LinkedList<>();
		private int sum = 0;

		public void push(int x) {
			balls.addFirst(x);
			sum += x;
		}

		public void pop() {
			if (!balls.isEmpty()) {
				sum -= balls.removeLast();
			}
		}

		public int sum() {
			return sum;
		}

		public SnowMan clone() {
			SnowMan s = new SnowMan();
			for (int i : balls)
				s.push(i);

			return s;
		}

		@Override
		public String toString() {
			return balls.toString();
		}
	}

	private static List<SnowMan> snowMen = new ArrayList<>();

	private static void clone(int ith, int x) {
		SnowMan s = snowMen.get(ith).clone();
		s.push(x);
		snowMen.add(s);
	}

	public static void remove(int ith) {
		SnowMan s = snowMen.get(ith).clone();
		if (s != null)
			s.pop();
		snowMen.add(s);
	}

	public static long play(int N, String[] ops) {
		snowMen = new ArrayList<SnowMan>();
		snowMen.add(new SnowMan());

		for (String s : ops) {
			String[] tokens = s.split(" ");
			int ith = Integer.parseInt(tokens[0]);
			int x = Integer.parseInt(tokens[1]);
			if (x == 0)
				remove(ith);
			else
				clone(ith, x);
		}

		long sum = 0;
		for (SnowMan s : snowMen) {
			if (s != null) {
				sum += s.sum;
			}
		}

		return sum;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
			BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
			int N = Integer.parseInt(br.readLine().trim());
			String[] ops = new String[N];
			for (int i = 0; i < N; ++i) {
				ops[i] = br.readLine().trim();
			}

			bw.write(play(N, ops) + "\n");
			bw.close();
		}
	}
}
