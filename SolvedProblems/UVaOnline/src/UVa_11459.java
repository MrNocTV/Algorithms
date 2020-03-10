import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class UVa_11459 {

	public static void main(String[] args) throws IOException {
		Reader reader = new Reader();
		int T = reader.nextInt();

		for (int t = 1; t <= T; t++) {
			int numberOfPlayers = reader.nextInt();
			int snakeAndLadders = reader.nextInt();
			int dieRolls = reader.nextInt();

			int[] inOutArr = new int[101];
			for (int i = 1; i <= 100; i++)
				inOutArr[i] = i;

			int[] playerPositions = new int[numberOfPlayers];
			for (int i = 0; i < playerPositions.length; i++)
				playerPositions[i] = 1;

			for (int i = 1; i <= snakeAndLadders; i++) {
				int in = reader.nextInt();
				int out = reader.nextInt();
				inOutArr[in] = out;
			}

			int player = 0;
			boolean aPlayerWins = false;
			for (int i = 1; i <= dieRolls; i++) {
				int die = reader.nextInt();
				if (aPlayerWins)
					continue;

				if (playerPositions.length == 0)
					continue;

				playerPositions[player] += die;
				if (playerPositions[player] >= 100) {
					aPlayerWins = true;
					playerPositions[player] = 100;
				} else if (inOutArr[playerPositions[player]] != playerPositions[player]) {
					playerPositions[player] = inOutArr[playerPositions[player]];
					if (playerPositions[player] == 100) {
						aPlayerWins = true;
					}
				}
				player = (player + 1) % numberOfPlayers;
			}

			for (int i = 0; i < playerPositions.length; i++) {
				System.out.println("Position of player " + (i + 1) + " is " + playerPositions[i] + ".");
			}
		}

		try {
		} catch (Exception e) {
		} finally {
			reader.close();
		}
	}

	static class Reader {
		final private int BUFFER_SIZE = 1 << 16;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;

		public Reader() {
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public Reader(String file_name) throws IOException {
			din = new DataInputStream(new FileInputStream(file_name));
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public String readLine() throws IOException {
			byte[] buf = new byte[64]; // line length
			int cnt = 0, c;
			while ((c = read()) != -1) {
				if (c == '\n')
					break;
				buf[cnt++] = (byte) c;
			}
			return new String(buf, 0, cnt);
		}

		public int nextInt() throws IOException {
			int ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (neg)
				return -ret;
			return ret;
		}

		public long nextLong() throws IOException {
			long ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}

		public double nextDouble() throws IOException {
			double ret = 0, div = 1;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();

			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (c == '.') {
				while ((c = read()) >= '0' && c <= '9') {
					ret += (c - '0') / (div *= 10);
				}
			}

			if (neg)
				return -ret;
			return ret;
		}

		private void fillBuffer() throws IOException {
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() throws IOException {
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}

		public void close() throws IOException {
			if (din == null)
				return;
			din.close();
		}
	}

}
