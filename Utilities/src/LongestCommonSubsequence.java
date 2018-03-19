/*
 *  Tutorial: https://www.youtube.com/watch?v=NnD96abizww
 */

import java.util.Stack;

public class LongestCommonSubsequence {
	
	static int maxI;
	static int maxJ;
	static int length;
	
	// calculate lcs and get max length of lcs
	public static int[][] lcs(char[] s1, char[] s2) {
		int[][] subTable = new  int[s1.length][s2.length];
		
		// assign first row to 0
		for (int i = 0; i < subTable[0].length; ++i) 
			subTable[0][i] = 0;
		
		// assign first column to 0
		for (int i = 0; i < subTable.length; ++i)
			subTable[i][0] = 0;
		
		for (int j = 1; j < subTable.length; ++j) {
			for (int i = 1; i < subTable[j].length; ++i) {
				if (s1[j] == s2[i]) 
					subTable[j][i] = subTable[j-1][i-1] + 1;
				else 
					subTable[j][i] = Math.max(subTable[j][i-1], subTable[j-1][i]);
				
				if (subTable[j][i] > length) {
					maxI = j;
					maxJ = i;
					length = subTable[j][i];
				}
			}
		}
		
		return subTable;
	}
	
	// get the lcs as string
	public static String getString(int[][] subTable, char[] s2) {
		Stack<Character> stack = new Stack<>();
		int i = maxI;
		int j = maxJ;
		int l = length;
		while (i != 0 && j != 0) {
			if (subTable[i][j-1] == subTable[i-1][j]) {
				stack.push(s2[j]);
				--i; --j;
			} else if (subTable[i][j-1] > subTable[i-1][j]) {
				--j;
			} else {
				--i;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		return sb.toString();
	}
	
	// test code
	public static void main(String[] args) {
		String s1 = " " + "acbcf";
		String s2 = " " + "abcdaf";
		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();
		
		int[][] subTable = lcs(c1, c2);
		
		for (int i = 0; i < subTable.length; ++i) {
			for (int j = 0; j < subTable[i].length; ++j)
				System.out.print(subTable[i][j] + " ");
			System.out.println();
		}
		
		System.out.println(getString(subTable, c2));
	}
} 
