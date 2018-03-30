import java.util.HashMap;
import java.util.Scanner;

/**
 * 
 * @author Loc Truong Van
 * @category Algorithm
 * @site GeeksForGeeks
 * @link https://practice.geeksforgeeks.org/problems/geek-and-strings/0
 * @idea Use tries data structure
 * 
 */

public class GeekAndStrings {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int T = input.nextInt();
		int n;
		int q;
		Tries tries;
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= T; ++t) {
			tries = new Tries();

			n = input.nextInt();
			for (int i = 1; i <= n; ++i) {
				String s = input.next().trim();
				tries.insert(s);
			}

			q = input.nextInt();
			for(int i = 1; i <= q; ++i) {
				String s = input.next().trim();
				sb.append(tries.find(s, false) + "\n");
			}
		}
		System.out.println(sb.toString().trim());
	}

}

class Tries {

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

}
