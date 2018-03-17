import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UVa_11679 {
	
	static List<Integer> listParseString(String s) {
		return Arrays.stream(s.trim().split(" ")).map(e -> Integer.parseInt(e)).collect(Collectors.toList());
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		List<Integer> list = listParseString(s);
		int b = list.get(0);
		int n = list.get(1);
		while ( b != 0 && n != 0 ) {
			List<Integer> banks = listParseString(br.readLine());
			for (int i = 1; i <= n; ++i) {
				list = listParseString(br.readLine());
				int deptor = list.get(0);
				int creditor = list.get(1);
				int deben = list.get(2);
				banks.set(deptor - 1, banks.get(deptor - 1) - deben);
				banks.set(creditor - 1, banks.get(creditor - 1) + deben);
			}
			boolean possible = true;
			for (int bank : banks) {
				if (bank < 0)
					possible = false;
			}
			
			System.out.println(possible ? "S" : "N");
			
			list = listParseString(br.readLine());
			b = list.get(0);
			n = list.get(1);
		}
	}
	
}
