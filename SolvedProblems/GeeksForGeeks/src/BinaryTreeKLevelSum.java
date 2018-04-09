import java.util.LinkedList;
import java.util.Scanner;

/**
 * 
 * @author Loc Truong Van
 * @category Algorithm
 * @site GeeksForGeeks
 * @link https://practice.geeksforgeeks.org/problems/binary-tree-k-level-sum/0
 * @idea Use a fairly complex recursive approach, divides s into left and right substring
 * 
 */

public class BinaryTreeKLevelSum {

	static class Node {
		int data;
		Node left;
		Node right;
		Node parent;

		Node(int data) {
			this.data = data;
			left = right = parent = null;
		}

		int getData() {
			return data;
		}

		@Override
		public String toString() {
			return "" + data;
		}
	}

	static class BTree {
		Node root;

		public void makeTree(String s) {
			root = makeTreeHelper(s);
		}

		public Node makeTreeHelper(String s) {
			if (s.length() == 2) {
				return null;
			}

			s = s.substring(1, s.length() - 1);
			int i = 0;
			StringBuilder sb = new StringBuilder();
			char c = s.charAt(i);
			while (Character.isDigit(c)) {
				sb.append(c);
				++i;
				c = s.charAt(i);
			}

			int data = Integer.parseInt(sb.toString());
			Node node = new Node(data);
			StringBuilder left = new StringBuilder();
			StringBuilder right = new StringBuilder();
			int idx = i;
			int count = 0;
			// left
			do {
				c = s.charAt(idx);
				if (c == '(')
					++count;
				else if (c == ')')
					--count;
				left.append(c);
				++idx;
			} while (count > 0);

			// right
			do {
				c = s.charAt(idx);
				if (c == '(')
					++count;
				else if (c == ')')
					--count;
				right.append(c);
				++idx;
			} while (count > 0);
			node.left = makeTreeHelper(left.toString());
			node.right = makeTreeHelper(right.toString());
			return node;
		}

		public int sumAtKLevel(int k) {
			if (root == null)
				return 0;

			LinkedList<Node> list = new LinkedList<>();
			list.add(root);
			int level = 0;

			if (k == 0)
				return root.data;

			while (!list.isEmpty()) {
				int n = list.size();
				for (int i = 1; i <= n; ++i) {
					Node curNode = list.removeFirst();
					if (curNode.left != null)
						list.addLast(curNode.left);
					if (curNode.right != null)
						list.addLast(curNode.right);
				}
				++level;
				if (level == k) {
					int sum = list.parallelStream().map(Node::getData).reduce(0, (total, other) -> total + other)
							.intValue();
					return sum;
				}
			}
			return 0;
		}

	}

	static boolean containsDigits(String s) {
		for (char c : s.toCharArray())
			if (Character.isDigit(c))
				return true;
		return false;
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int T = input.nextInt();

		for (int t = 1; t <= T; ++t) {
			int k = input.nextInt();
			String s = input.next();
			// corner case : (()())
			if (!containsDigits(s))
				System.out.println(0);
			else {
				BTree bTree = new BTree();
				bTree.makeTree(s);
				System.out.println(bTree.sumAtKLevel(k));
			}
		}
	}
}
