import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UVa_696 {
	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String line;

			while ((line = br.readLine()) != null && !line.trim().equals("0 0")) {
				String[] token = line.split(" ");
				int m = Integer.parseInt(token[0]);
				int n = Integer.parseInt(token[1]);
				int knights = 0;

				if (m == 0 || n == 0) {
				} else if (m == 1 || n == 1) {
					knights += Math.max(m, n);
				} else if (m == 2 || n == 2) {
					if (m != 2) {
						knights += (m / 4) * 4;
						if (m % 4 != 0)
							knights += (m % 4 == 2 || m % 4 == 3 ? 4 : 2);
					} else {
						knights += (n / 4) * 4;
						if (n % 4 != 0)
							knights += (n % 4 == 2 || n % 4 == 3 ? 4 : 2);
					}
				} else {
					knights += (n*m + 1) / 2;
				}
				System.out.println(knights + " knights may be placed on a " + m + " row " + n + " column board.");
			}
		}
	}
}
