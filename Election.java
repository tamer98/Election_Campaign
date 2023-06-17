
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class Election {
	protected static int countVotersExist = 0;
	protected static int countPartiesExist = 0;
	protected static int countBallotBoxesExist = 0;

	public static final int MAX_VOTERS = 2;
	public static final int MAX_PARTIES = 2;
	public static final int MAX_STATIONS = 2;
	public final static int VOTE_AGE = 18;
	private BallotBox listBallotBoxes[];
	private Party listOfParties[];
	private Citizen listOfVoters[];
	private int year, month;

	public Election() {
	}

	public Election(int year, int month) {
		listBallotBoxes = new BallotBox[MAX_STATIONS];
		listOfParties = new Party[MAX_PARTIES];
		listOfVoters = new Citizen[MAX_VOTERS];
		setYear(year);
		setMonth(month);

	}

	public int getYear() {
		return year;
	}

	public boolean setYear(int year) {
		if (year < 2000) {
			this.year = 2000;
			return false;

		} else if (year > 2020) {
			this.year = 2020;
			return false;
		}
		this.year = year;
		return true;

	}

	public int getMonth() {
		return month;
	}

	public boolean setMonth(int month) {
		if (month < 1) {
			this.month = 1;
			return false;

		} else if (month > 12) {
			this.month = 12;
			return false;
		}
		this.month = month;
		return true;
	}

	public static void show(Object obj) {
		System.out.println(obj.toString());
	}

	public boolean setAllSides(Party party, Candidate member) {
		if (party.addMember(member) == true && member.setParty(party) == true) {
			return true;
		}
		return false;
	}

	public boolean setAllSides(Citizen voter, BallotBox ballot) {
		if (voter.setBallotBox(ballot) == true && ballot.setVoter(voter) == true) {
			return true;
		}
		return false;
	}

	public BallotBox findStationByID(int ID) {
		for (int i = 0; i < BallotBox.ballotsCounter; i++) {
			if (ID == listBallotBoxes[i].getId())
				return listBallotBoxes[i];
		}
		return null;
	}

	public Party findPartyByName(String name) {
		for (int i = 0; i < Party.partiesCounter; i++) {
			if (listOfParties[i].getName().equalsIgnoreCase(name))
				return listOfParties[i];
		}
		System.out.println("could not find " + name);
		return null;
	}

	public Citizen findVoterByID(int ID) {
		for (int i = 0; i < Citizen.citizenCounter; i++) {
			if (ID == listOfVoters[i].getId())
				return listOfVoters[i];
		}
		return null;
	}

	public boolean CheckIfExist(Object obj) {
		if (obj instanceof BallotBox) {
			for (int i = 0; i < countBallotBoxesExist; i++) {
				if (listBallotBoxes[i].equals(obj)) {
					return true;
				}
			}

		} else if (obj instanceof Party) {
			for (int i = 0; i < countPartiesExist; i++) {
				if (listOfParties[i].equals(obj)) {
					return true;
				}
			}

		} else if (obj instanceof Citizen) {
			for (int i = 0; i < countVotersExist; i++) {
				if (listOfVoters[i].equals(obj)) {
					return true;
				}
			}
		}
		return false;

	}

	public boolean addToList(Object obj) {
		if (obj instanceof BallotBox && CheckIfExist(obj) == false) {
			for (int i = 0; i < listBallotBoxes.length; i++) {
				if (listBallotBoxes[i] == null) {
					listBallotBoxes[i] = (BallotBox) obj;
					countBallotBoxesExist++;
					return true;
				}
			}
			listBallotBoxes = Arrays.copyOf(listBallotBoxes, listBallotBoxes.length * 2);
			listBallotBoxes[(listBallotBoxes.length / 2)] = (BallotBox) obj;
			countBallotBoxesExist++;

			return true;
		} else if (obj instanceof Party && CheckIfExist(obj) == false) {
			for (int i = 0; i < listOfParties.length; i++) {
				if (listOfParties[i] == null) {
					listOfParties[i] = (Party) obj;
					countPartiesExist++;
					return true;

				}
			}
			listOfParties = Arrays.copyOf(listOfParties, listOfParties.length * 2);
			listOfParties[(listOfParties.length / 2)] = (Party) obj;
			countPartiesExist++;
			return true;

		} else if (obj instanceof Citizen && CheckIfExist(obj) == false && ((Citizen) obj).getAge() >= VOTE_AGE) {
			for (int i = 0; i < listOfVoters.length; i++) {
				if (listOfVoters[i] == null) {
					listOfVoters[i] = (Citizen) obj;
					countVotersExist++;
					return true;

				}
			}
			listOfVoters = Arrays.copyOf(listOfVoters, listOfVoters.length * 2);
			listOfVoters[(listOfVoters.length / 2)] = (Citizen) obj;
			countVotersExist++;
			return true;

		}
		return false;

	}

	public boolean election(Scanner scan) {
		int voteAnswear;
		int isProtected;
		System.out.println("Starting Election!");

		for (int i = 0; i < countVotersExist; i++) {
			System.out.println("Hello " + listOfVoters[i].getName() + ", would you like to vote?");
			System.out.println("1: yes");
			System.out.println("2: no");

			if (listOfVoters[i].situation == true) {
				System.out.println("Are you protected?");
				System.out.println("1: yes");
				System.out.println("2: no");
				isProtected = scan.nextInt();
				scan.nextLine();
			}

			voteAnswear = scan.nextInt();
			scan.nextLine();
			if (voteAnswear == 1) {
				System.out.println(showAllParty());
				System.out.println("enter name of party you want to vote for : ");
				Party party = findPartyByName(scan.nextLine());
				if (listOfVoters[i].getBallotBox().setAllSides(party, listOfVoters[i]) == true) {
					System.out.println("Thank you, you voted for the democracy , next please ");

				}
			}
		}
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		// No two elections can be done in the same year and month.
		if (!(obj instanceof Election))
			return false;
		if (this.month != ((Election) obj).getMonth())
			return false;
		if (this.year != ((Election) obj).getYear())
			return false;
		return true;
	}

	public String showAllBallotBox() {
		String s = "\n full Ballots list:\n";
		for (int i = 0; i < countBallotBoxesExist; i++) {
			s += i + 1 + ")" + listBallotBoxes[i].toString() + "\n\n";

		}
		return s;
	}

	public String showAllParty() {
		String s = "\n full PoliticalParties list:\n";
		for (int i = 0; i < countPartiesExist; i++) {
			s += i + 1 + ")" + listOfParties[i].toString() + "\n\n";
		}
		return s;
	}

	public String showAllvoters() {
		String s = "\n full voters list:\n";
		for (int i = 0; i < countVotersExist; i++) {
			s += i + 1 + ") " + listOfVoters[i].toString() + "\n\n";

		}
		return s;
	}

	@Override
	public String toString() {
		String s = "\n--- full results --- \n";
		for (int i = 0; i < countBallotBoxesExist; i++) {
			s += "in ballot number : " + listBallotBoxes[i].getId() + "\naddress : " + listBallotBoxes[i].getAddress()
					+ "\n" + listBallotBoxes[i].getResults() + "\n";
		}

		for (int i = 0; i < countPartiesExist; i++) {
			s += "total votes for " + listOfParties[i].getName() + " : " + listOfParties[i].votersCounter + "\n";

		}
		return s;
	}

}
