
import java.util.Arrays;

public class BallotBox extends Election {

	public static int serialNumber = 1;
	private int id;
	protected static final int MAX_VOTERS = 2;
	protected static final int MAX_Parties = 2;
	protected int existForNow = 0;
	protected String address;
	protected double perecentageOfVoters;
	protected Citizen allBoxVoters[];
	protected Party allParties[];
	protected int voted = 0;
	protected int partyCount = 0;
	protected static int ballotsCounter = 0;

	public BallotBox(String address) {
		this.address = address;
		id = serialNumber++;
		allBoxVoters = new Citizen[MAX_VOTERS];
		allParties = new Party[MAX_Parties];
		ballotsCounter++;
	}

	public int getId() {
		return id;
	}

	public boolean setAllSides(Party party, Citizen voter) {
		if (party.setVoter(voter) == true && voter.setVoteFor(party) == true) {
			voter.hasVoted = true;
			addVote();
			return true;
		}
		return false;
	}

	public String getAddress() {
		return address;
	}

	public boolean setAddress(String address) {
		this.address = address;
		return true;
	}

	public double getPerecentageOfVoters() {
		if (voted != 0) {
			perecentageOfVoters = (voted / existForNow) * 100;
			return perecentageOfVoters;
		} else
			perecentageOfVoters = 0;
		return perecentageOfVoters;

	}

	public String getallBoxVoters() {
		String s = "";
		for (int i = 0; i < existForNow; i++) {
			s += i + 1 + ")" + allBoxVoters[i].toString();
			s += "\n";
		}
		return s;
	}

	public boolean CheckIfExist(Citizen newPerson) {
		for (int i = 0; i < existForNow; i++) {
			if (allBoxVoters[i].equals(newPerson)) {
				return true;
			}
		}
		return false;
	}

	public boolean CheckIfExist(Party party) {
		for (int i = 0; i < partyCount; i++) {
			if (allParties[i].equals(party)) {
				return true;
			}
		}
		return false;
	}

	public boolean setVoter(Citizen voter) {
		if (voter.getAge() >= 18 && CheckIfExist(voter) == false && voter.situation == false) {
			for (int i = 0; i < allBoxVoters.length; i++) {
				if (allBoxVoters[i] == null) {
					allBoxVoters[i] = voter;
					existForNow++;
					return true;
				}
			}
			allBoxVoters = Arrays.copyOf(allBoxVoters, allBoxVoters.length * 2);
			allBoxVoters[(allBoxVoters.length / 2)] = voter;
			existForNow++;
			return true;
		}
		return false;

	}

	public boolean addVote() {
		voted++;
		return true;
	}

	public String getResults() {
		for (int i = 0; i < existForNow; i++) {
			if (CheckIfExist(allBoxVoters[i].getVoteFor()) == false && allBoxVoters[i].hasVoted == true) {
				for (int j = 0; j < allParties.length; j++) {
					if (allParties[j] == null) {
						allParties[j] = allBoxVoters[i].getVoteFor();

						partyCount++;
					}
				}
				if (partyCount == allParties.length) {
					allParties = Arrays.copyOf(allParties, allParties.length * 2);
					allParties[(allBoxVoters.length / 2)] = allBoxVoters[i].getVoteFor();
					partyCount++;
				}
			}
		}
		String str = "";
		for (int i = 0; i < partyCount; i++) {
			int count = 0;
			for (int j = 0; j < existForNow; j++) {
				if (allBoxVoters[j].hasVoted == true) {
					if (allParties[i].getName().equals(allBoxVoters[j].voteFor.getName())) {
						count++;
					}
				}

			}
			str += "number of people who vote for " + allParties[i].getName() + " : " + count + "\n";

		}

		return str;

	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BallotBox && this.id == ((BallotBox) obj).id) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		String s = "full ballotbox details: \n";
		s += "address : " + address + "\nserial number : " + id + "\n" + "has voted untill now : " + voted + " ("
				+ getPerecentageOfVoters() + " %)\n" + "\nall voters list : \n";
		s += getallBoxVoters();

		return s;
	}

}
