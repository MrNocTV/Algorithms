import java.util.Arrays;
import java.util.Scanner;

public class UVa_11956 {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int t = input.nextInt();
		int[] arr = new int[100];
		String s;
		int pos;

		for (int i = 1; i <= t; ++i) {
			s = input.next();
			Arrays.fill(arr, 0);
			pos = 0;
			for (char c : s.toCharArray()) {
				if (c == '+') {
					arr[pos] = (arr[pos] + 1) % 256;
				} else if (c == '>') {
					pos = (pos + 1) % 100;
				} else if (c == '<') {
					pos = pos == 0 ? 99 : pos - 1;
				} else if (c == '-') {
					arr[pos] = arr[pos] == 0 ? 255 : arr[pos] - 1;
				}
			}

			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < arr.length; ++j)
				sb.append(String.format("%02X", arr[j]) + " ");
			System.out.println("Case " + i + ": " + sb.toString().trim());
		}

	}

}
