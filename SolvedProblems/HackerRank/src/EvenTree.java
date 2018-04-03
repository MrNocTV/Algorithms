import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * @author Loc Truong Van
 * @category Algorithm
 * @site HackerRank
 * @link https://www.hackerrank.com/challenges/even-tree/problem
 * @idea While inserting edges, count the children of a node, then check how
 *       many subtrees have even number of nodes, and minus one (tree with 2
 *       nodes does not need to be cut)
 * 
 */

public class EvenTree {

	private class Node {
		int data;
		int numNode;
		List<Node> children;
		Node parent;

		Node(int data) {
			this.data = data;
			numNode = 1;
			children = new ArrayList<>();
		}

		@Override
		public String toString() {
			return String.valueOf(data);
		}
	}

	Map<Integer, Node> tree;
	boolean[] visited;

	public EvenTree(int n) {
		tree = new HashMap<>();
		for (int i = 1; i <= n; ++i)
			tree.put(i, new Node(i));
		visited = new boolean[n + 1];
		Arrays.fill(visited, false);
		visited[1] = true;
	}

	public void insert(int u, int v) {
		if (visited[u]) {
			Node parent = tree.get(u);
			Node child = tree.get(v);
			child.parent = parent;
			parent.children.add(child);

			while (parent != null) {
				parent.numNode += 1;
				parent = parent.parent;
			}

			visited[v] = true;
		} else if (visited[v]) {
			Node parent = tree.get(v);
			Node child = tree.get(u);
			child.parent = parent;
			parent.children.add(child);

			while (parent != null) {
				parent.numNode += 1;
				parent = parent.parent;
			}

			visited[u] = true;
		}
	}

	public long countCut() {
		return tree.values().stream().filter(node -> node.numNode % 2 == 0).count() - 1;
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int m = input.nextInt();
		EvenTree et = new EvenTree(n);

		for (int i = 1; i <= m; ++i) {
			int u = input.nextInt();
			int v = input.nextInt();
			et.insert(u, v);
		}
		System.out.println(et.countCut());
	}
}
