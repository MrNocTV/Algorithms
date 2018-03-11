import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class UVa_12250 {
	
	static Map<String, String> from = new TreeMap<>();
	static {
		from.put("HELLO", "ENGLISH");
		from.put("HOLA", "SPANISH");
		from.put("HALLO", "GERMAN");
		from.put("BONJOUR", "FRENCH");
		from.put("CIAO", "ITALIAN");
		from.put("ZDRAVSTVUJTE", "RUSSIAN");
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String hello = br.readLine().trim();
		int test = 1;
		
		do {
			System.out.println("Case " + test + ": " + from.getOrDefault(hello, "UNKNOWN"));
			hello = br.readLine().trim();
			++test;
		} while (!hello.equals("#"));
		
	}
	
}
