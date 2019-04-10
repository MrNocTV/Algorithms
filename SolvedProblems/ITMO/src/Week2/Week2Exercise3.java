package Week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;

public class Week2Exercise3 {

	static class MinQueue {
		ArrayDeque<Integer> minQueue;
		ArrayDeque<Integer> queue;

		public MinQueue(int Q) {
			minQueue = new ArrayDeque<>(Q / 2);
			queue = new ArrayDeque<>(Q / 2);
		}

		public void enqueue(int x) {
			queue.addLast(x);
			while (!minQueue.isEmpty() && minQueue.getLast() > x)
				minQueue.removeLast();
			minQueue.addLast(x);
		}

		public void dequeue() {
			int val = queue.removeFirst();
			if (minQueue.getFirst() == val)
				minQueue.removeFirst();
		}

		public int getMin() {
			return minQueue.getFirst();
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
			BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
			int Q = Integer.parseInt(br.readLine().trim());
			MinQueue mq = new MinQueue(Q);
			StringBuilder sb = new StringBuilder();
			for (int i = 1; i <= Q; ++i) {
				String[] tokens = br.readLine().trim().split(" ");
				if (tokens.length == 1) {
					if (tokens[0].equals("?"))
						sb.append(mq.getMin() + "\n");
					else
						mq.dequeue();
				} else {
					mq.enqueue(Integer.parseInt(tokens[1]));
				}
			}

			bw.write(sb.toString());
			bw.close();
		}
	}
}