
import java.util.Arrays;

public class MilitaryBallotBox extends BallotBox {

	public MilitaryBallotBox(String address) {
		super(address);
		allBoxVoters = new Citizen[MAX_VOTERS];
	}

	public boolean addVoter(Citizen voter) {
		if (super.setVoter(voter) && voter.getAge() <= 21) {
			return true;
		}
		return false;
	}

	public String toString() {
		String s = "*Military Ballot\n*";
		s += super.toString();
		return s;
	}
}
