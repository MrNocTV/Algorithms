import java.util.Arrays;
import java.util.Scanner;

public class UVa_11683 {

	static void fillColumn(int[][] block, int col, int h) {
		for (int i = 1; i <= h; ++i)
			block[i-1][col] = 1;
	}
	
	static void printBlock(int[][] block) {
		for (int i = 0; i < block.length; ++i) {
			for (int j = 0; j < block[i].length; ++j)
				System.out.print(block[i][j] + " ");
			System.out.println();
		}
	}
	
	static int onOffCount(int[][] block) {
		int count = 0;
		for(int i = 0; i < block.length; ++i) {
			int prev = block[i][0];
			for(int j = 1; j < block[i].length; ++j) {
				if (j==block[i].length-1) {
					if (block[i][j] == 0)
						++count;
				} else {
					if (block[i][j] == 1) {
						if (prev == 0) {
							++count;
							prev = 1;
						}
					} else {
						prev = 0;
					}
				}
			}
		}
		
		return count;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int a, c;
		int[][] block;
		int[] heights;
		
		while (true) {
			a = input.nextInt();
			if (a == 0)
				break;
			c = input.nextInt();
			block = new int[a][c];
			heights = new int[c];
			
			for(int i = 0; i < a; ++i)
				Arrays.fill(block[i], 0);
			
			for (int i = 0; i < c; ++i) {
				int h = input.nextInt();
//				System.out.println(h);
				fillColumn(block, i, h);
			}
			
			printBlock(block);
			System.out.println(onOffCount(block));
		}

	}

}
