import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UVa_10550 {

	public static void main (String [] abc) throws IOException  {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			String [] str = s.split(" ");
			int p0=Integer.parseInt(str[0]);
			int p1=Integer.parseInt(str[1]);
			int p2=Integer.parseInt(str[2]);
			int p3=Integer.parseInt(str[3]);
			int degreePerPos = 360/40;
			
			if (p0==0 && p1==0 && p2==0 && p3==0) {
				break;
			}
			//turn 3 times
			int count = 120;
			
			if (p1>p0) {
				count+=40-p1+p0;
			} else {
				count+=p0-p1;
			}
			
			if (p2>p1) {
				count+=(p2-p1);
			} else {
				count+=40-p1+p2;
			}
			
			if (p3>p2) {
				count+=40-p3+p2;
			} else {
				count+=p2-p3;
			}
			
			System.out.println(count*degreePerPos);
		}
	}
}