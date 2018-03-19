import java.math.BigInteger;

public class NextBiggerNumberSameBit1 {
	static BigInteger nextBigger(BigInteger n) {
		char[] arr = n.toString(2).toCharArray();
		int length = arr.length;

		// end with 0
		if (arr[length - 1] == '0') {
			return zeroCase(arr, length);
		}
		// end with 1
		else {
			return oneCase(arr, length);
		}
	}

	private static BigInteger oneCase(char[] arr, int length) {
		// first last 0s from right
		int zero = -1;

		for (int i = length - 1; i >= 0; --i) {
			if (arr[i] == '0') {
				zero = i;
				break;
			}
		}

		if (zero == -1) { // '111..' full 1s form
			char[] newArr = new char[length + 1];
			newArr[0] = '0';

			System.arraycopy(arr, 0, newArr, 1, length);
			newArr[0] = '1';
			newArr[1] = '0';

			String s = new String(newArr);
			return new BigInteger(s, 2);
		} else {
			arr[zero] = '1';
			arr[zero + 1] = '0';

			String s = new String(arr);
			return new BigInteger(s, 2);
		}
	}

	private static BigInteger zeroCase(char[] arr, int length) {
		// first last 1s from right
		int one = length;
		for (int i = length - 1; i >= 0; --i) {
			if (arr[i] == '1') {
				one = i;
				break;
			}
		}

		int zero = -1;
		// now find the first zero before it
		for (int i = one; i >= 0; --i) {
			if (arr[i] == '0') {
				zero = i;
				break;
			}
		}

		// can find zero
		if (zero != -1) {
			// swap it with the right next 1
			arr[zero] = '1';
			arr[zero + 1] = '0';

			// move all remaining 1's to extreme right
			int last = length - 1;
			for (int i = one; i >= 0; --i) {
				if (arr[i] == '0')
					break;
				arr[i] = '0';
				arr[last--] = '1';
			}
			String s = new String(arr);
			return new BigInteger(s, 2);
		} else {
			char[] newArr = new char[length + 1];
			newArr[0] = '0';

			System.arraycopy(arr, 0, newArr, 1, length);
			return zeroCase(newArr, newArr.length);
		}
	}
}
