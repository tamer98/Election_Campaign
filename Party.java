
import java.util.Arrays;

import javax.print.attribute.standard.MediaSize.Other;

public class Party extends Election {

	public static enum faction {
		Right, Center, Left
	}

	public static final int MAX_CANDIDATES = 2;
	public static final int MAX_VOTERS = 2;
	protected faction side;
	protected String name;
	protected int foundedIn;
	protected Candidate members[];
	protected int votersCounter;
	protected int membersCounter;
	protected Citizen voters[];
	protected static int partiesCounter = 0;

	public Party(String name, faction side, int since) {
		setName(name);
		this.side = side;
		foundedIn = since;
		members = new Candidate[MAX_CANDIDATES];
		voters = new Citizen[MAX_VOTERS];
		votersCounter = 0;
		membersCounter = 0;
		partiesCounter++;
	}

	public faction getSide() {
		return side;
	}

	public boolean setFaction(faction side) {
		this.side = side;
		return true;
	}

	public String getName() {
		return name;
	}

	public Boolean setName(String name) {
		if (name == "")
			return false;
		this.name = name;
		return true;
	}

	public int getFoundedIn() {
		return foundedIn;
	}

	public boolean setVoter(Citizen voter) {
		if (voter.hasVoted == false && voter.getAge() > 18) {
			for (int i = 0; i < voters.length; i++) {
				if (voters[i] == null) {
					voters[i] = voter;
					votersCounter++;
					return true;
				}
			}
			voters = Arrays.copyOf(voters, voters.length * 2);
			voters[(voters.length / 2)] = voter;
			votersCounter++;
			return true;
		}

		return false;
	}

	public String getAllVoters() {
		return voters.toString();
	}

	public boolean setFoundedIn(int foundedIn) {
		this.foundedIn = foundedIn;
		return true;
	}

	public String getMembers() {
		String s = "";
		for (int i = 0; i < this.members.length; i++) {
			s += members[i].toString();
			s += "\n";
		}
		return s;
	}

	public boolean addMember(Candidate member) {
		if (member.getAge() >= 18) {
			for (int i = 0; i < members.length; i++) {
				if (members[i] == null) {
					members[i] = member;
					membersCounter++;
					return true;
				}
			}
			members = Arrays.copyOf(members, members.length * 2);
			members[(members.length / 2)] = member;
			membersCounter++;
			return true;
		}
		return false;

	}

	@Override
	public boolean equals(Object obj) {
		if ((obj instanceof Party) && this.name == ((Party) obj).name) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		String str = "party name: " + name + "\nfounded in : " + foundedIn + "\nfaction belong: " + side.name()
				+ "\nall candidates in party :      (allow primaries)\n";
		if (membersCounter == 0) {
			str += "no candidates in party yet !";
		}
		for (int i = 0; i < membersCounter; i++) {
			str += i + 1 + ")" + members[i].getName() + " ---> id : " + members[i].getId() + "\n";
		}
		return str;
	}

}
