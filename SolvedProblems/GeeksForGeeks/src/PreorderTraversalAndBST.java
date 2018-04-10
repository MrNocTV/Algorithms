import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author Loc Truong Van
 * @category Algorithm
 * @site GeeksForGeeks
 * @link https://practice.geeksforgeeks.org/problems/preorder-traversal-and-bst/0
 * @idea The idea is simple, build a BST from the input and compare the preorder
 *       traversal list of BST with the original one
 * 
 */

public class PreorderTraversalAndBST {

	static class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
			left = right = null;
		}
	}

	static class BST {
		Node root;

		public void insert(int data) {
			if (root == null)
				root = new Node(data);
			else
				insertHelper(data, root);
		}

		private void insertHelper(int data, Node node) {
			if (data < node.data) {
				if (node.left == null)
					node.left = new Node(data);
				else
					insertHelper(data, node.left);
			} else {
				if (node.right == null)
					node.right = new Node(data);
				else
					insertHelper(data, node.right);
			}
		}

		public List<Integer> getPreorderList() {
			if (root == null)
				return null;
			List<Integer> result = new ArrayList<>();
			getPreorderListHelper(result, root);
			return result;
		}

		private void getPreorderListHelper(List<Integer> result, Node node) {
			if (node != null) {
				result.add(node.data);
				getPreorderListHelper(result, node.left);
				getPreorderListHelper(result, node.right);
			}
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		BST bst;
		List<Integer> list;
		int n;

		for (int t = 1; t <= T; ++t) {
			n = Integer.parseInt(br.readLine());
			list = Arrays.stream(br.readLine().trim().split(" ")).filter(s -> !s.equals("")).map(Integer::new)
					.collect(Collectors.toList());
			bst = new BST();
			for (int e : list)
				bst.insert(e);

			List<Integer> bstList = bst.getPreorderList().stream().collect(Collectors.toList());
			System.out.println(bstList.equals(list) ? "1" : "0");

		}
	}

}
