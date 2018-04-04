import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * @author Loc Truong Van
 * @category Algorithm
 * @site GeeksForGeeks
 * @link https://practice.geeksforgeeks.org/problems/image-multiplication/0
 * @idea BinaryTree, recursive call on left and right child, if they're not
 *       leaves, check left.left - right,right and left.right - right.left
 * 
 */

public class ImageMultiplication {

	static class Tree {
		
		static final Long MOD = (long)Math.pow(10, 9) + 7;
		
		class Node {
			long data;
			Node left;
			Node right;
		}

		Map<Integer, Node> map;
		Node root;

		public Tree() {
			map = new HashMap<>();
			root = null;
		}

		public void insert(int parent, int child, char c) {
			Node pNode = map.containsKey(parent) ? map.get(parent) : new Node();
			Node cNode = map.containsKey(child) ? map.get(child) : new Node();

			if (c == 'L') {
				pNode.left = cNode;
			} else {
				pNode.right = cNode;
			}

			pNode.data = parent;
			cNode.data = child;
			map.put(parent, pNode);
			map.put(child, cNode);

			if (root == null) {
				root = pNode;
			}
		}
		
		private Long mirrorSum(Node left, Node right) {
			if(isLeave(left) && isLeave(right)) {
				return left.data * right.data;
			}
			
			Long sum = left.data*right.data;
			if(left.left != null && right.right != null) 
				sum += mirrorSum(left.left, right.right);
			if(left.right != null && right.left != null)
				sum += mirrorSum(left.right, right.left);
			return sum;
		}
		
		public Long mirroSum() {
			Long sum = root.data * root.data;
			if (root.left != null && root.right != null)
				sum += mirrorSum(root.left, root.right);
			return sum % MOD;
		}
		
		private boolean isLeave(Node node) {
			return node.left == null && node.right == null;
		}
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int T = input.nextInt();
		int n;
		int parent, child;
		char c;
		Tree tree;

		for (int t = 1; t <= T; ++t) {
			n = input.nextInt();
			tree = new Tree();
			for (int i = 1; i <= n; ++i) {
				parent = input.nextInt();
				child = input.nextInt();
				c = input.next().charAt(0);
				tree.insert(parent, child, c);
			}
			System.out.println(tree.mirroSum());
		}
	}
}
