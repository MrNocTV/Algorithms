package Week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class Week2Exercise4 { 
	
	private static boolean isCorrectBS(String s) {
		Stack<Character> stack = new Stack<>();
		
		for(int i = 0; i < s.length(); ++i) {
			if (s.charAt(i) == '(' || s.charAt(i) =='[')
				stack.push(s.charAt(i));
			else {
				if (s.charAt(i) == ']') {
					if (stack.isEmpty() || stack.peek() != '[')
						return false;
					else
						stack.pop();
				} else if (s.charAt(i) == ')') {
					if (stack.isEmpty() || stack.peek() != '(')
						return false;
					else
						stack.pop();
				}
			}
		}
		
		return stack.size() == 0;
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
			BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
			StringBuilder sb = new StringBuilder();
			int N = Integer.parseInt(br.readLine().trim());
			for(int i = 1; i <= N; ++i) {
				String s = br.readLine().trim();
				sb.append(isCorrectBS(s) ? "YES\n" : "NO\n");
			}
			
			bw.write(sb.toString());
			bw.close();
		}
	}
}