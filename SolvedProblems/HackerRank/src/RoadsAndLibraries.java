import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @author Loc Truong Van
 * @category Algorithm
 * @site HackerRank
 * @link https://www.hackerrank.com/challenges/torque-and-development/problem
 * @idea Use FastIO for IO, use DisjointSet to 'cluster' cities, then find
 *       minimal cost on these set of 'cluster'
 * 
 */

public class RoadsAndLibraries {

	private static class DisjointSet {

		private Map<Long, Node> map = new HashMap<>();

		private class Node {
			int rank;
			long data;
			Node parent;
		}

		public void makeSet(long data) {
			Node node = new Node();
			node.data = data;
			node.rank = 0;
			node.parent = node;
			map.put(data, node);
		}

		// union based on ranking
		public void union(long data1, long data2) {
			Node node1 = map.get(data1);
			Node node2 = map.get(data2);

			Node parent1 = findSet(node1);
			Node parent2 = findSet(node2);

			// in same set
			if (parent1.data == parent2.data) {
				return;
			}

			if (parent1.rank >= parent2.rank) {
				parent1.rank = (parent1.rank == parent2.rank) ? parent1.rank + 1 : parent1.rank;
				parent2.parent = parent1;
			} else {
				parent1.parent = parent2;
			}

		}

		public long findSet(long data) {
			return findSet(map.get(data)).data;
		}

		// find and does path compression
		public Node findSet(Node node) {
			Node parent = node.parent;
			if (parent == node) {
				return parent;
			}
			node.parent = findSet(parent);
			return node.parent;
		}
	}

	private static class Reader {
		final private int BUFFER_SIZE = 1 << 16;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;

		public Reader() {
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public int nextInt() throws IOException {
			int ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (neg)
				return -ret;
			return ret;
		}

		private void fillBuffer() throws IOException {
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() throws IOException {
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}

		public void close() throws IOException {
			if (din == null)
				return;
			din.close();
		}
	}

	static long minCost(int numCities, int libCost, int roadCost) {
		long totalCost = 0;
		if (numCities == 1) {
			totalCost = libCost;
		} else {
			long cost1 = libCost * numCities;
			long cost2 = libCost + roadCost * (numCities - 1);
			totalCost = Math.min(cost1, cost2);
		}
		return totalCost;
	}

	public static void main(String[] args) throws IOException {
		Reader r = new Reader();
		int q = r.nextInt();
		Map<Long, List<Long>> sets;
		DisjointSet ds;
		int n;
		int m;
		int libCost;
		int roadCost;

		for (int t = 1; t <= q; ++t) {
			sets = new HashMap<>();
			ds = new DisjointSet();
			n = r.nextInt();
			m = r.nextInt();
			libCost = r.nextInt();
			roadCost = r.nextInt();

			// build disjointSet
			for (int i = 1; i <= n; ++i) {
				ds.makeSet(i);
			}

			int u, v;
			for (int i = 1; i <= m; ++i) {
				u = r.nextInt();
				v = r.nextInt();
				ds.union(u, v);
			}

			// build set of 'cluster'
			long parent;
			for (long i = 1; i <= n; ++i) {
				parent = ds.findSet(i);
				if (sets.containsKey(parent)) {
					sets.get(parent).add(i);
				} else {
					List<Long> list = new ArrayList<>();
					list.add(i);
					sets.put(parent, list);
				}
			}

			// calculate min on each 'cluster'
			// here instead of storing the list as value of map
			// you can store Integer (as size of the list)
			// to save memory and got some improve in performance
			long totalCost = 0;
			for (Entry<Long, List<Long>> entry : sets.entrySet()) {
				int numCities = entry.getValue().size();
				totalCost += minCost(numCities, libCost, roadCost);
			}

			System.out.println(totalCost);
		}

		r.close();
	}

}
