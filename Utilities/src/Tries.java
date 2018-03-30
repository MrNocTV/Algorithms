import java.util.HashMap;
import java.util.Set;

public class Tries {

	private class TriesNode {
		HashMap<Character, TriesNode> children;
		boolean isCompletedWord;
		int freq;

		TriesNode() {
			this(null);
		}

		TriesNode(Character c) {
			children = new HashMap<>();
			if (c != null)
				children.put(c, new TriesNode());
			isCompletedWord = false;
			freq = 0;
		}
	}

	private TriesNode root;

	public Tries() {
		root = new TriesNode();
	}

	private int findHelper(TriesNode node, String value, boolean isCompletedWord) {
		int result = 0;
		int i = 0;
		char key;
		TriesNode curNode = node;

		while (i < value.length()) {
			key = value.charAt(i);
			if (curNode.children.containsKey(key)) {
				curNode = curNode.children.get(key);
			} else {
				break;
			}
			++i;
		}

		if (i == value.length()) {
			if (isCompletedWord && curNode.isCompletedWord)
				return curNode.freq;
			result = curNode.freq;
		}

		return result;
	}

	private void insertHelper(TriesNode node, String value) {
		int i = 0;
		TriesNode curNode = node;
		char key;

		// part that exist
		while (i < value.length()) {
			key = value.charAt(i);
			if (curNode.children.containsKey(key)) {
				curNode = curNode.children.get(key);
				curNode.freq += 1;
			} else {
				break;
			}
			++i;
		}

		// part that does not exist
		while (i < value.length()) {
			key = value.charAt(i);
			curNode.children.put(key, new TriesNode());
			curNode = curNode.children.get(key);
			curNode.freq = 1;
			++i;
		}

		curNode.isCompletedWord = true;
	}

	public void insert(String value) {
		insertHelper(root, value);
	}

	public int find(String key, boolean isCompletedWord) {
		return findHelper(root, key, isCompletedWord);
	}

	public void helpPrint(String tab, TriesNode node) {
		Set<Character> children = node.children.keySet();
		if (children.size() > 0) {
			for (Character key : children) {
				System.out.println(tab + "" + key);
				helpPrint(tab + "\t", node.children.get(key));
			}
		}
	}

	public void print() {
		helpPrint("", root);
	}

	public static void main(String[] args) {
		String[] words = { "abracadabra", "geeksforgeeks", "abracadabra", "geeks", "geeksthrill" };
		Tries tries = new Tries();
		for (String word : words) {
			tries.insert(word);
		}
		tries.print();
		String[] queries = {"abr", "geeks", "geeksforgeeks", "ge", "gar"};
		for (String word : queries) {
			System.out.println(word + " : " + tries.find(word, false));
		}

	}

}
