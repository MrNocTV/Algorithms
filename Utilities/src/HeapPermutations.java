import java.util.Arrays;

// Java program to print all permutations using
// Heap's algorithm


public class HeapPermutations {
	
	static void heapPermutation(int a[], int size) {
		// if sizes become 1 the print the obtained
		// permutations
		if (size == 1)
			System.out.println(Arrays.toString(a));
		
		for (int i = 0; i < size; ++i) {
			heapPermutation(a, size-1);
			
			// if size is odd, swap first and last
			// element
			if (size % 2 == 1) {
				int temp = a[0];
				a[0] = a[size-1];
				a[size-1] = temp;
			} 
			
			// if size is even, swap ith and last
			// element
			else {
				int temp = a[i];
				a[i] = a[size-1];
				a[size-1] = temp;
			}
		}
	}
}
