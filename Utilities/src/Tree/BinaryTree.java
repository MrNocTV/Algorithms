package Tree;
import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T extends Comparable<T>> {

	@SuppressWarnings("hiding")
	private class TreeNode<T extends Comparable<T>> {
		TreeNode<T> leftChild;
		TreeNode<T> rightChild;
		T data;

		public TreeNode(T data) {
			this.data = data;
			this.leftChild = null;
			this.rightChild = null;
		}
	}

	public TreeNode<T> root;

	public BinaryTree() {
		root = null;
	}

	private void insertHelper(TreeNode<T> node, T data) {
		// left insert
		if (data.compareTo(node.data) < 0) {
			if (node.leftChild == null)
				node.leftChild = new TreeNode<T>(data);
			else
				insertHelper(node.leftChild, data);
		}

		// right insert
		else {
			if (node.rightChild == null)
				node.rightChild = new TreeNode<T>(data);
			else
				insertHelper(node.rightChild, data);
		}
	}

	private void inorderTraversalHelper(TreeNode<T> node) {
		if (node != null) {
			inorderTraversalHelper(node.leftChild);
			System.out.println(node.data);
			inorderTraversalHelper(node.rightChild);
		}
	}

	private void preorderTraversalHelper(TreeNode<T> node) {
		if (node != null) {
			System.out.println(node.data);
			preorderTraversalHelper(node.leftChild);
			preorderTraversalHelper(node.rightChild);
		}
	}

	private void postorderTraversalHelper(TreeNode<T> node) {
		if (node != null) {
			postorderTraversalHelper(node.leftChild);
			postorderTraversalHelper(node.rightChild);
			System.out.println(node.data);
		}
	}

	private void levelorderTraversalHelper(TreeNode<T> node) {
		if (node == null)
			return;
		List<TreeNode<T>> nodes = new ArrayList<>();
		nodes.add(node);

		while (true) {
			List<TreeNode<T>> nextNodes = new ArrayList<>();
			for (int i = 0; i < nodes.size(); ++i) {
				System.out.print(nodes.get(i).data + " ");
				if (nodes.get(i).leftChild != null && nodes.get(i).rightChild != null) {
					nextNodes.add(nodes.get(i).leftChild);
					nextNodes.add(nodes.get(i).rightChild);
				}
			}

			System.out.println();
			if (nextNodes.size() == 0)
				break;
			nodes = nextNodes;
		}
	}

	public void levelorderTraversal() {
		levelorderTraversalHelper(root);
	}

	public void preorderTraversal() {
		preorderTraversalHelper(root);
	}

	public void inorderTraversal() {
		inorderTraversalHelper(root);
	}

	public void postorderTraversal() {
		postorderTraversalHelper(root);
	}

	public void insert(T data) {
		if (root == null)
			root = new TreeNode<T>(data);
		else
			insertHelper(root, data);
	}

}
