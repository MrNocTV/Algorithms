import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UVa_10919 {

	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String line;

			while ((line = br.readLine()) != null && !line.equals("0")) {
				String[] token = line.split(" ");
				int numberOfCourses = Integer.parseInt(token[0]);
				int numberOfCategories = Integer.parseInt(token[1]);

				Set<String> courses = new HashSet<>();
				courses.addAll(Arrays.asList(br.readLine().split(" ")));
				int categoryPassedCount = 0;

				for (int i = 1; i <= numberOfCategories; i++) {
					token = br.readLine().split(" ");
					int category = Integer.parseInt(token[0]);
					int minToPass = Integer.parseInt(token[1]);
					int count = 0;
					for (int j = 2; j < token.length; j++) {
						if (courses.contains(token[j]))
							count++;
					}
					categoryPassedCount += (count >= minToPass ? 1 : 0);
				}

				System.out.println(categoryPassedCount >=  numberOfCategories ? "yes" : "no");
			}
		} catch (IOException e) {

		}
	}
}
