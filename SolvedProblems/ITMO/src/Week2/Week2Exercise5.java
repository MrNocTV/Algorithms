package Week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class Week2Exercise5 {

	private static int compute(int a, int b, char op) {
		switch (op) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		}

		return -1;
	}

	private static int evaluate(String s) {
		String[] tokens = s.split(" ");
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < tokens.length; ++i) {
			char c = tokens[i].charAt(0);
			if (Character.isDigit(c)) {
				stack.push(Integer.parseInt(tokens[i]));
			} else {
				int a = stack.pop();
				int b = stack.pop();
				char op = tokens[i].charAt(0);
				stack.push(compute(b, a, op));
			}
		}
		return stack.pop();

	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
			BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
			Integer.parseInt(br.readLine().trim());
			String s = br.readLine().trim();
			bw.write(evaluate(s) + "\n");
			bw.close();
		}
	}

}
