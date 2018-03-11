import java.util.Scanner;

public class UVa_272 {
	static final Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		StringBuffer sb;
		boolean first = true;
		int i;
		while (input.hasNext()) {
			sb = new StringBuffer(input.nextLine());
			i = sb.indexOf("\"");
			while (i != -1) {
				sb.replace(i, i+1, first ? "``" : "''");
				first = !first;
				i = sb.indexOf("\"", i+2);
			}
			System.out.println(sb.toString().trim());
		}
	}
}
