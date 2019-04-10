package Week1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class Week1Exercise2 {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
			String[] input = br.readLine().trim().split(" ");
			BigInteger a = new BigInteger(input[0]);
			BigInteger b = new BigInteger(input[1]);
			;
			BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
			bw.write(a.add(b.pow(2)).toString()+"\n");
			bw.close();
		}
	}
	
}
