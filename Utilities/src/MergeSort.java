import java.util.Arrays;

public class MergeSort {
	
	public static void mergeSort(int[] A, int[] W, int s, int t) {
		if (s + 1 == t) return;
		int mid = (s + t) / 2;
		mergeSort(A, W, s, mid);
		mergeSort(A, W, mid, t);
		
		int i = s;
		int j = mid;
		int k = s;
		
		while (i < mid || j < t) {
			if (j == t || (i < mid && A[i] <= A[j]))
				W[k++] = A[i++];
			else
				W[k++] = A[j++];
		}
		
		for(i = s; i < t; ++i)
			A[i] = W[i];
	}
	
	public static void main(String[] args) {
		int[] A = {5, 1, 3, 7, 9, 8};
		int[] W = new int[A.length];
		mergeSort(A, W, 0, A.length);
		System.out.println(Arrays.toString(A));
	}
	
}
