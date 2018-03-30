import java.util.Scanner;

public class RecursiveDigitSum {
	
	static int sumDigitOfNumber(String n) {
		return n.chars().map(e -> Character.getNumericValue(e)).sum();
	}
	
	static long digitSum(String n, int k) {
        long sum = sumDigitOfNumber(n);
        sum *= k;
        String s = String.valueOf(sum);
        while (s.length() > 1) {
        	sum = sumDigitOfNumber(s);
        	s = String.valueOf(sum);
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String n = in.next();
        int k = in.nextInt();
        long result = digitSum(n, k);
        System.out.println(result);
        in.close();
    }
}
