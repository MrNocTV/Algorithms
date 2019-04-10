package Week1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Week1Exercise6 {

	private static double solution(int a, int b, int c) {
		return (a + b + c) / 6.0;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
			BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
			String[] inputs = br.readLine().trim().split(" ");
			int a = Integer.parseInt(inputs[0]);
			int b = Integer.parseInt(inputs[1]);
			int c = Integer.parseInt(inputs[2]);
			
			bw.write(String.format("%.8f\n", solution(a, b, c)));
			bw.close();
		}
	}
}
