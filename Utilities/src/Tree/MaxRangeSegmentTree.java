package Tree;

public class MaxRangeSegmentTree {
	private int[] arr;
	private int n;

	public MaxRangeSegmentTree(int[] input) {
		int height = (int) Math.ceil(log2(input.length));
		int maxSize = 2 * (int) Math.pow(2, height) - 1;
		arr = new int[maxSize];
		n = input.length;

		constructST(0, n - 1, input, 0);
	}

	private double log2(int x) {
		return Math.log(x) / Math.log(2);
	}

	private int getMid(int s, int e) {
		return s + (e - s) / 2;
	}

	private int constructST(int start, int end, int[] input, int i) {
		if (start == end) {
			arr[i] = input[start];
			return input[start];
		}

		int mid = getMid(start, end);
		arr[i] = Math.max(
				constructST(start, mid, input, i * 2 + 1), 
				constructST(mid + 1, end, input, i * 2 + 2)
				);

		return arr[i];
	}

	private int maxHelper(int start, int end, int l, int r, int i) {
		if (l <= start && r >= end)
			return arr[i];

		if (end < l || start > r)
			return -1;

		int mid = getMid(start, end);

		return Math.max(
				maxHelper(start, mid, l, r, i * 2 + 1), 
				maxHelper(mid + 1, end, l, r, i * 2 + 2)
				);
	}

	public int getMax(int l, int r) {
		if (l < 0 || r > n - 1 || l > r)
			return -1;

		return maxHelper(0, n - 1, l, r, 0);
	}

	
	private void updateHelper(int start, int end, int index, int value, int node) {
		if (start == end) {
			arr[node] = value;
		} else {
			int mid = getMid(start, end);

			if (index >= start && index <= mid)
				updateHelper(start, mid, index, value, node * 2 + 1);
			else
				updateHelper(mid + 1, end, index, value, node * 2 + 2);
			
			arr[node] = Math.max(arr[node * 2 + 1], arr[node * 2 + 2]);
		}
	}
	
	public void updateValue(int index, int value) {
		updateHelper(0, n - 1, index, value, 0);
	}
	
}
