import java.util.Scanner;

public class UVa_12289 {
	static final String ONE = "one";
	static final String TWO = "two";
	static final String THREE = "three";
	
	static boolean isOne(String s) {
		int count = 0;
		if (s.length() != ONE.length()) return false;
		for(int i = 0; i < ONE.length(); ++i)
			if (s.charAt(i) == ONE.charAt(i))
				++count;
		return count >= ONE.length() - 1;
	}
	
	static boolean isTwo(String s) {
		int count = 0;
		if(s.length() != TWO.length()) return false;
		for(int i = 0; i < TWO.length(); ++i)
			if (s.charAt(i) == TWO.charAt(i))
				++count;
		return count >= TWO.length() - 1;
	}
	
	static boolean isThree(String s) {
		int count = 0;
		if (s.length() != THREE.length()) return false;
		for(int i = 0; i < THREE.length(); ++i)
			if (s.charAt(i) == THREE.charAt(i))
				++count;
		return count >= THREE.length() - 1;
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int t = input.nextInt();
		String word;
		
		for(int i = 1; i <= t; ++i) {
			word = input.next();
			if (isOne(word))
				System.out.println(1);
			else if (isTwo(word))
				System.out.println(2);
			else if (isThree(word))
				System.out.println(3);
		}
	}
	
}
