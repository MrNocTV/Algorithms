import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UVa_11507 {

	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String line;

			while ((line = br.readLine()) != null && !line.equals("0")) {
				String curDirection = "+x";
				int length = Integer.parseInt(line);
				String[] bends = br.readLine().split(" ");
				for (String direction : bends) {
					if (!direction.equals("No")) {
						curDirection = bend(curDirection, direction);
					}
				}
				System.out.println(curDirection);
			}
		}
	}

	static String bend(String curDirection, String direction) {
		if (curDirection.contains("x")) {
			return bendX(curDirection, direction);
		} else if (curDirection.contains("y")) {
			return bendY(curDirection, direction);
		} else if (curDirection.contains("z")) {
			return bendZ(curDirection, direction);
		}
		return  "";
	}

	static String bendX(String curDirection, String direction) {
		if (curDirection.equals("+x")) {
			switch (direction) {
				case "+y":
					return "+y";
				case "-y":
					return "-y";
				case "+z":
					return "+z";
				case "-z":
					return "-z";
			}
		} else if (curDirection.equals("-x")) {
			switch (direction) {
				case "+y":
					return "-y";
				case "-y":
					return "+y";
				case "+z":
					return "-z";
				case "-z":
					return "+z";
			}
		}
		return "";
	}

	static String bendY(String curDirection, String direction) {
		if (curDirection.equals("+y")) {
			switch (direction) {
				case "+y":
					return "-x";
				case "-y":
					return "+x";
				case "+z":
				case "-z":
					return "+y";
			}
		} else if (curDirection.equals("-y")) {
			switch (direction) {
				case "+y":
					return "+x";
				case "-y":
					return "-x";
				case "+z":
				case "-z":
					return "-y";
			}
		}
		return "";
	}

	static String bendZ(String curDirection, String direction) {
		if (curDirection.equals("+z")) {
			switch (direction) {
				case "+y":
				case "-y":
					return "+z";
				case "+z":
					return "-x";
				case "-z":
					return "+x";
			}
		} else if (curDirection.equals("-z")) {
			switch (direction) {
				case "+y":
				case "-y":
					return "-z";
				case "+z":
					return "+x";
				case "-z":
					return "-x";
			}
		}
		return "";
	}
}
