import java.math.BigInteger;

public class BitwiseOperator {
	
	public static int multiplyBy2(int a) {
		return a << 1;
	}
	
	public static int divideBy2(int a) {
		return a >> 1;
	}
	
	public int turnIthBitOn(int a, int ith) {
		a |= (1 << ith);
		return a;
	}
	
	public int turnIthBitOff(int a, int ith) {
		a &= ~(1 << ith);
		return a;
	}
	
	public int toggleIthBit(int a, int ith) {
		a ^= (1 << ith);
		return a;
	}
	
	public boolean checkIthBitIsOff(int a, int ith) {
		int t = a & (1 << ith);
		return t == 0;
	}
	
	public BigInteger nextBiggerWithSameNumberOfBit1s(int n) {
		return NextBiggerNumberSameBit1.nextBigger(BigInteger.valueOf(n));
	}
	
}
