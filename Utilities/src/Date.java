import java.util.Calendar;
import java.util.GregorianCalendar;

public class Date {

	public static int getDayOfWeek(int year, int month, int day) {
		Calendar calendar = new GregorianCalendar(year, month, day);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
	
}
