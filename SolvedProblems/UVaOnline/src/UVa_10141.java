import java.util.Scanner;

class Proposal {
	String name;
	double price;
	int metRequirements;

	public Proposal(String name, double price, int metRequirements) {
		this.name = name;
		this.price = price;
		this.metRequirements = metRequirements;
	}

	public boolean isBetter(Proposal other) {
		if (this.metRequirements == other.metRequirements)
			return this.price < other.price;
		return this.metRequirements > other.metRequirements;
	}
}

public class UVa_10141 {

	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		int rfp = 1;
		int numberOfRequirements = input.nextInt();
		int numberOfProposals = input.nextInt();

		StringBuilder sb = new StringBuilder();
		while (numberOfRequirements != 0 && numberOfProposals != 0) {
			input.nextLine(); // read the trailing '\n'
			for (int i = 0; i < numberOfRequirements; i++)
				input.nextLine();

			Proposal bestProposal = null;
			for (int propose = 1; propose <= numberOfProposals; propose++) {
				String name = input.nextLine();
				double price = input.nextDouble();
				int metRequirements = input.nextInt();

				Proposal proposal = new Proposal(name, price, metRequirements);
				if (bestProposal == null) {
					bestProposal = proposal;
				} else if (proposal.isBetter(bestProposal)) {
					bestProposal = proposal;
				}

				input.nextLine(); // read the trailing '\n'
				for (int i = 1; i <= metRequirements; i++) {
					input.nextLine();
				}
			}

			if (rfp > 1)
				sb.append("\n");
			sb.append("RFP #" + rfp++).append("\n");
			sb.append(bestProposal.name).append("\n");
			numberOfRequirements = input.nextInt();
			numberOfProposals = input.nextInt();
		}

		System.out.print(sb.toString());
	}

}
