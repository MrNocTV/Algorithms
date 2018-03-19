import java.util.*;

public class UVa_10963 {
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int t;
		int y1, y2;
		int initGap;
		boolean canClose;

		for (int i = 1; i <= n; ++i) {
			t = input.nextInt();
			canClose = true;
			initGap = -1;
			for (int j = 1; j <= t; ++j) {
				y1 = input.nextInt();
				y2 = input.nextInt();
				if (initGap == -1) {
					initGap = (int)Math.abs(y1-y2);
				} else {
					if (Math.abs(y1-y2) != initGap)
					canClose = false;
				}
			}

			System.out.println(canClose ? "yes" : "no");
			if (i < n)
				System.out.println();

		}
	}
}
