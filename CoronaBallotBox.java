
import java.util.Arrays;

public class CoronaBallotBox extends BallotBox {

	public CoronaBallotBox(String address) {
		super(address);

	}

	public boolean setVoter(Citizen voter) {
		for (int i = 0; i < allBoxVoters.length; i++) {
			if (allBoxVoters[i] == null && voter.getAge() >= 18 && CheckIfExist(voter) == false
					&& voter.situation == true) {
				allBoxVoters[i] = voter;
				existForNow++;
				return true;
			}
		}
		if (CheckIfExist(voter) == false) {
			allBoxVoters = Arrays.copyOf(allBoxVoters, allBoxVoters.length * 2);
			allBoxVoters[(allBoxVoters.length / 2)] = voter;
			existForNow++;
			return true;
		}
		return false;

	}

	public String toString() {
		String s = "*Corona Ballot* \n";
		s += super.toString();
		return s;
	}
}
