import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UVa_12468 {
	
	static int getMinPress(int a, int b) {
		// this is slow but easy to understand
		int pressForward = 0;
		int pressBackward = 0;
		
		if (a == b) return 0;
		
		int forWard = a;
		int backWard = a;
		
		while (forWard != b) {
			if (forWard == 99)
				forWard = 0;
			else
				++forWard;
			++pressForward;
		}
		
		while (backWard != b) {
			if (backWard == 0)
				backWard = 99;
			else
				--backWard;
			++pressBackward;
		}
		
		return Math.min(pressForward, pressBackward);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a, b;
		String[] temp;
		
		while (true) {
			temp = br.readLine().trim().split(" ");
			a = Integer.parseInt(temp[0]);
			b = Integer.parseInt(temp[1]);
			if (a == -1 && b == -1) break;
			System.out.println(getMinPress(a, b));
		}
	}
	
}
