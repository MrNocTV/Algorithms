import java.util.Arrays;
import java.util.Random;

public class QuickSort {

	private static final Random random = new Random();

	private static final int random(int a, int b) {
		return a + random.nextInt(b + 1 - a);
	}

	public static void quickSort(int[] A, int s, int e) {
		int sP = s;
		int eP = e;
		int M = A[random(s, e)];
		
		while (sP <= eP) {
			while (A[sP] < M) ++sP;
			while (M < A[eP]) --eP;
			if (sP <= eP) {
				swap(A, sP, eP);
				++sP;
				--eP;
			}
		}

		if (s <= eP) quickSort(A, s, eP);
		if (sP <= e) quickSort(A, sP, e);
	}

	public static void swap(int[] A, int i, int j) {
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}
	
	public static void main(String[] args) {
		int[] A = {5, 1, 3, 7, 9, 8};
		quickSort(A, 0, A.length-1);
		System.out.println(Arrays.toString(A));
	}
}
