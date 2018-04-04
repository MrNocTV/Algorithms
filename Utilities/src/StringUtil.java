import java.util.HashSet;
import java.util.Set;

public class StringUtil {
	
	public static Set<String> allSubStrings(String s) {
		Set<String> result = new HashSet<>();
		for(int i = 1; i <= s.length(); ++i) {
			for(int j = 0; (i+i) <= s.length(); ++j) {
				result.add(s.substring(j, j+i));
			}
		}
		
		return result;
	}
	
}
