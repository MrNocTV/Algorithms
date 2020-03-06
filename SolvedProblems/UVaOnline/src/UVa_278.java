import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UVa_278 {

	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String line;

			int cases = Integer.parseInt(br.readLine());
			for (int i = 1; i <= cases; i++) {
				line = br.readLine();
				String[] token = line.split(" ");
				String piece = token[0];
				int m = Integer.parseInt(token[1]);
				int n = Integer.parseInt(token[2]);

				switch (piece) {
					case "r":
					case "Q":
						System.out.println(Math.min(m, n));
						break;
					case "k":
						System.out.println((m*n + 1)/2);
						break;
					case "K":
						if (m%2 != 0) m += 1;
						if (n%2 != 0) n += 1;
						System.out.println(m*n / 4);
						break;
				}
			}
		}
	}
}
