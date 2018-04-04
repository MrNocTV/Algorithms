import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * @author Loc Truong Van
 * @category Algorithm
 * @site GeeksForGeeks
 * @link https://practice.geeksforgeeks.org/problems/find-the-distance-between-two-nodes/0
 * @idea Find lowest common ancestor, check three cases, lca == x, lca == y and
 *       else
 * 
 */

public class ShortedDistanceInInfiniteTree {

	static final int BOUND = 10000000;

	public static int parent(int h) {
		return h / 2;
	}

	// lowest common ancestor
	public static int lca(int x, int y) {
		Set<Integer> xParents = new HashSet<>();

		xParents.add(x);
		int xTemp = x;
		while (xTemp != 1) {
			xTemp = parent(xTemp);
			xParents.add(xTemp);
		}

		// same side
		if (xParents.contains(y))
			return y;

		int yTemp = y;
		while (yTemp != 1) {
			yTemp = parent(yTemp);
			// same side
			if (yTemp == x) {
				return x;
			} else if (xParents.contains(yTemp)) {
				return yTemp;
			}
		}

		return -1;
	}

	public static int distance(int x, int y) {
		if (x == y)
			return 0;

		int lca = lca(x, y);
		int distance = 0;
		if (lca == x) {
			while (y != lca) {
				y = parent(y);
				++distance;
			}
			return distance;
		} else if (lca == y) {
			while (x != lca) {
				x = parent(x);
				++distance;
			}
			return distance;
		} else {
			while (x != lca) {
				x = parent(x);
				++distance;
			}

			while (y != lca) {
				y = parent(y);
				++distance;
			}

			return distance;
		}
	}

	static List<Point> generateTest(int t) {
		List<Point> list = new ArrayList<>();
		Random random = new Random();
		for (int i = 1; i <= t; ++i) {
			int x = 1 + random.nextInt(BOUND);
			int y = 1 + random.nextInt(BOUND);
			list.add(new Point(x, y));
		}
		return list;
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		int T = input.nextInt();
		for (int t = 1; t <= T; ++t) {
			int x = input.nextInt();
			int y = input.nextInt();
			System.out.println(distance(x, y));
		}
	}
}
