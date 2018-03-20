import java.util.Scanner;

public class UVA_11661 {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int l = input.nextInt();
		String highWay;

		while (l != 0) {
			highWay = input.next();
			if (highWay.contains("Z"))
				System.out.println(0);
			else {
				char[] temp = highWay.toCharArray();
				int prevLoc = 0;
				int minDist = Integer.MAX_VALUE;
				
				for (int i = 0; i < temp.length; ++i) {
					if (temp[i] == 'R' || temp[i] == 'D') {
						prevLoc = i;
						break;
					}
				}

				for (int i = 1; i < temp.length; ++i) {
					if (temp[i] == 'D') {
						if (temp[prevLoc] == 'R') {
							minDist = Math.min(minDist, i - prevLoc);
						}
						prevLoc = i;
					} else if (temp[i] == 'R') {
						if (temp[prevLoc] == 'D') {
							minDist = Math.min(minDist, i - prevLoc);
						}
						prevLoc = i;
					}
				}
				System.out.println(minDist);

			}

			l = input.nextInt();
		}

	}

}
