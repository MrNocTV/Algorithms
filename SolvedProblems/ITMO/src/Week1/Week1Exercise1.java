package Week1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Week1Exercise1 {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
			String[] input = br.readLine().trim().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			
			BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
			bw.write((a+b)+"\n");
			bw.close();
		}
	}
	
}
