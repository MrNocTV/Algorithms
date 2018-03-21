import java.util.Scanner;

public class UVa_11683 {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int a, c;
		int[] arr;
		int onOffTime;
		
		while (true) {
			a = input.nextInt();
			if (a == 0)
				break;
			c = input.nextInt();
			if (c == 1) {
				int temp = input.nextInt();
				System.out.println(a - temp);
			} else {
				arr = new int[c];
				onOffTime = 0;
				
				for (int i = 0; i < arr.length; ++i)
					arr[i] = input.nextInt();
				
				int curMaxHeight = arr[0];
				if (curMaxHeight < a)
					++onOffTime;
				for (int i = 1; i < arr.length; ++i) {
					if (arr[i] < arr[i-1]) {
						onOffTime += (arr[i-1] - arr[i]); 
					} 
				}
				
				if (curMaxHeight < a)
					onOffTime += (a-curMaxHeight-1);
					
				System.out.println(onOffTime);
			}
		}
	}

}
