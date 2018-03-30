import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * 
 * @author Loc Truong Van
 * @category Algorithm
 * @site GeeksForGeeks
 * @link https://practice.geeksforgeeks.org/problems/print-binary-tree-levels-in-sorted-order/0
 * @idea Use SortedSet in Java, every level has 2^i (i is level) nodes
 * 
 */

public class BinaryTreeLevelsInSortedOrder {

	static void levelTraverse(Integer[] arr) {
		int i = 0;
		int k = 0;
		int numNodes = (int) Math.pow(2, k);
		SortedSet<Integer> ss = new TreeSet<>();

		while (i < arr.length) {
			int j = 1;
			while (j <= numNodes) {
				ss.add(arr[i]);
				++i;
				++j;
				if (i >= arr.length)
					break;
			}
			Iterator<Integer> ssIter = ss.iterator();
			while(ssIter.hasNext())
				System.out.print(ssIter.next() + " ");
			ss.clear();
			System.out.println();
			numNodes = (int)Math.pow(2, ++k);
			if (i >= arr.length)
				break;
			
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int n;
		Integer[] arr;
		
		for (int t = 1; t <= T; ++t) {
			n = Integer.parseInt(br.readLine());
			arr = Arrays.stream(br.readLine().trim().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
			levelTraverse(arr);
		}
	}

}
