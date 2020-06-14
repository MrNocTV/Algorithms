import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class UVa_584 {

	static class Frame {
		char firstTry;
		char secondTry;
		char extraTry;
		boolean isStrike;
		boolean isSpare;
		boolean isExtraTry;

		Frame(char firstTry, char secondTry) {
			this.firstTry = firstTry;
			this.secondTry = secondTry;
			this.isSpare = this.secondTry == '/';
		}

		Frame() {
			this.isStrike = true;
		}

		Frame(char extraTry) {
			this.extraTry = extraTry;
			this.isExtraTry = true;
		}

		int firstTryScore() {
			if (isStrike)
				return getScore();
			return Character.digit(firstTry, 10);
		}

		int getScore() {
			if (isStrike || isSpare)
				return 10;
			if (isExtraTry)
				return Character.digit(extraTry, 10);
			return Character.digit(firstTry, 10) + Character.digit(secondTry, 10);
		}

		@Override
		public String toString() {
			if (isExtraTry)
				return extraTry + "";
			return isStrike ? "X" : firstTry + " " + secondTry;
		}
	}

	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String line;

			while ((line = br.readLine()) != null && !line.equals("Game Over")) {
				List<Frame> frames = stringToFrames(line);
//				System.out.println(frames);
				int score = calculateScore(frames);
				System.out.println(score);
			}
		}
	}

	private static List<Frame> stringToFrames(String line) {
		List<Frame> result = new ArrayList<>();
		int i = 0;

		while (i < line.length()) {
			char firstTry = line.charAt(i);
			if (Character.isDigit(firstTry)) {
				i += 2;
				if (i >= line.length()) {
					result.add(new Frame(firstTry));
					break;
				}
				char secondTry = line.charAt(i);
				result.add(new Frame(firstTry, secondTry));
			} else {
				result.add(new Frame());
			}
			i += 2;
		}

		return result;
	}

	private static int calculateScore(List<Frame> frames) {
		int score = 0;
		for (int i = 0; i < 10; i++) {
			Frame frame = frames.get(i);
			score += frame.getScore();
			if (i == 9)
				break;
			if (frame.isSpare)
				score += frames.get(i+1).firstTryScore();
			else if (frame.isStrike) {
				Frame nextFrame = frames.get(i + 1);
				score += nextFrame.getScore();

				if (nextFrame.isStrike) {
					score += frames.get(i + 2).firstTryScore();
				}
			}
		}

		for (int i = 10; i < frames.size(); i++) {
			Frame frame = frames.get(i);
			score += frame.getScore();
		}

		return score;
	}

}
