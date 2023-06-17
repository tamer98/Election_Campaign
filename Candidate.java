
public class Candidate extends Citizen {

	private Party partyBelong;

	public Candidate(String name, int id, String bornDate, boolean situation, Party party, BallotBox ballotBelong) {
		super(name, id, bornDate, situation, ballotBelong);
		partyBelong = party;

	}

	public Candidate(String name, int id, String bornDate, boolean situation, BallotBox ballotBelong) {
		super(name, id, bornDate, situation, ballotBelong);
	}

	public Party getParty() {
		return partyBelong;
	}

	public boolean equals(Object obj) {
		if ((obj instanceof Candidate) && super.equals(obj) && this.partyBelong.equals(((Candidate) obj).getParty())) {
			return true;
		}
		return false;
	}

	public Boolean setParty(Party party) {
		if (partyBelong == null) {
			partyBelong = party;
			return true;
		}
		return false;
	}

	public String toString() {
		return "--- (Candidate) ---\n" + super.toString() + "\nrunnig for : " + partyBelong.getName() + "\n";

	}

}
