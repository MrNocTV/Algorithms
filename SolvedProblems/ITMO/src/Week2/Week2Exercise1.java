package Week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class Week2Exercise1 {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
			BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
			Stack<Integer> stack = new Stack<Integer>();
			int N = Integer.parseInt(br.readLine().trim());
			for(int i = 1; i <= N; ++i) {
				String[] tokens = br.readLine().trim().split(" ");
				if (tokens.length == 1) {
					bw.write(stack.pop() + "\n");
				} else {
					stack.push(Integer.parseInt(tokens[1]));
				}
			}
			bw.close();
		}
	}
	
}
