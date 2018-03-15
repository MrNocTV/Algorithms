import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UVa_10114 {
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		double payPerMonth = input.nextDouble();
		double own = input.nextDouble();
		double carWorth = own;
		int deprecations = input.nextInt();
		double prevRate;
		int curMonth;
		int i;
		Map<Integer, Double> monthRate;
		double monthlyPay;
		
		while (n > 0) {
			
			curMonth = 1;
			prevRate = -1.0;
			monthRate = new HashMap<>();
			
			for( i = 1; i <= deprecations; ++i) {
				monthRate.put(input.nextInt(), input.nextDouble());
			}
			
			// stimulating code
			monthlyPay = own / n;
			prevRate = monthRate.get(0);
			carWorth = (own + payPerMonth) * (1 - prevRate);
			
			if (own < carWorth)
				System.out.println("0 months");
			else {
				while (own >= carWorth) {
					
					if (monthRate.containsKey(curMonth)) {
						prevRate = monthRate.get(curMonth);
					}
					
					own -= monthlyPay;
					carWorth = carWorth * (1 - prevRate);
					++curMonth;
				}
				curMonth--;
				System.out.println(curMonth + (curMonth != 1 ? " months" : " month"));
			}
			n = input.nextInt();
			payPerMonth = input.nextDouble();
			own = input.nextDouble();
			carWorth = own;
			deprecations = input.nextInt();
		}
	}
}
