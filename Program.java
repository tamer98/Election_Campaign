
import java.util.Scanner;

public class Program {

	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		int year, month;

		System.out.println("Making a new Election system...");
		System.out.println("Please pick a year for the election : (only after 2000)");
		year = scan.nextInt();
		System.out.println("Please pick a month for the election : (only between 1-12)");
		month = scan.nextInt();
		scan.nextLine();
		Election e = new Election(year, month);
		setBefore(e);
		System.out.println("\n");

		int choise = 0;
		while (choise != 10) {
			System.out.println("MENU: press the number of the action:");
			System.out.println("1: Add a Polling Station");
			System.out.println("2: Add a Voter (Citizen)");
			System.out.println("3: Add a Party");
			System.out.println("4: Add a Candidate to a party (create a new Voter that is a Candidate)");
			System.out.println("5: Show all Polling Stations");
			System.out.println("6: Show all Voters");
			System.out.println("7: Show all Parties");
			System.out.println("8: START THE ELECTION");
			System.out.println("9: Show results");
			System.out.println("10: EXIT");
			choise = scan.nextInt();

			int count = 0;
			switch (choise) {
			case 1:
				createBallotBox(e, scan);
				break;

			case 2:
				createVoter(e, scan);
				break;

			case 3:
				createParty(e, scan);
				break;

			case 4:
				createCandidate(e, scan);
				break;

			case 5:
				System.out.println(e.showAllBallotBox());
				break;

			case 6:
				System.out.println(e.showAllvoters());
				break;

			case 7:
				System.out.println(e.showAllParty());
				break;

			case 8:
				e.election(scan);
				break;

			case 9:
				System.out.println(e.toString());
				break;

			case 10:
				System.out.println("you choose exit\n Goodbye !");
				break;
			}

		}
	}

	public static void setBefore(Election e) {
		Citizen c1 = new Citizen("naor", 209274165, "22/04/1998", false);
		Citizen c2 = new Citizen("avi", 209274177, "01/04/1992", false);
		Citizen c3 = new Citizen("saher", 209334165, "25/05/2000", true);
		Citizen c4 = new Citizen("linor", 309284115, "09/09/1967", false);
		Citizen c5 = new Citizen("naor", 209274165, "23/07/1988", false);

		e.addToList(c1);
		e.addToList(c2);
		e.addToList(c3);
		e.addToList(c4);
		e.addToList(c5);

		BallotBox b1 = new BallotBox("ramle");
		BallotBox b2 = new BallotBox("lod");
		CoronaBallotBox co1 = new CoronaBallotBox("ramat gan");

		e.addToList(b1);
		e.addToList(b2);
		e.addToList(co1);

		e.setAllSides(c1, b1);
		e.setAllSides(c2, b1);
		e.setAllSides(c3, b1);
		e.setAllSides(c4, b1);
		e.setAllSides(c5, b1);

		Party p1 = new Party("Lo Odd", Party.faction.Left, 1999);
		Party p2 = new Party("Kahol Lavan", Party.faction.Center, 2012);
		Party p3 = new Party("Licod", Party.faction.Right, 1981);

		e.addToList(p1);
		e.addToList(p2);
		e.addToList(p3);

		Candidate ca1 = new Candidate("bibi", 306282199, "02/06/1966", false, p1, b1);
		Candidate ca2 = new Candidate("shimi", 809271366, "29/03/1963", false, b1);
		Candidate ca3 = new Candidate("avia", 402382199, "03/06/1986", false, p1, b1);
		Candidate ca4 = new Candidate("avshalom", 206282188, "04/05/1977", false, p2, b1);
		Candidate ca5 = new Candidate("noy", 306282177, "02/09/1991", false, p3, b1);
		Candidate ca6 = new Candidate("peleg", 306282166, "09/06/1955", false, p3, b1);

		e.addToList(ca1);
		e.addToList(ca2);
		e.addToList(ca3);
		e.addToList(ca4);
		e.addToList(ca5);
		e.addToList(ca6);

		e.setAllSides(p2, ca2);
		e.setAllSides(p2, ca3);

		e.setAllSides(ca1, b1);
	}

	public static Boolean createBallotBox(Election election, Scanner scan) {
		// Make a new polling station, add it to the list of stations.
		System.out.println("What kind of a station would you like to make?");
		System.out.println("0: Back / Cancel");
		System.out.println("1: Regular station");
		System.out.println("2: Corona-friendly station");
		System.out.println("3: Military station");
		int choice = scan.nextInt();
		scan.nextLine();
		String address = "";
		if (choice == 1 || choice == 2 || choice == 3) {
			System.out.println("Enter name of location:");
			address = scan.nextLine();
		}
		switch (choice) {
		case 0:
			return false;
		case 1:
			// making regular station
			BallotBox a = new BallotBox(address);
			if (election.addToList(a)) {
				System.out.println("Regular Station added successfully");
				return true;
			}
		case 2:
			// making corona station
			CoronaBallotBox b = new CoronaBallotBox(address);
			if (election.addToList(b)) {
				System.out.println("Corona Station added successfully");
				return true;
			}
		case 3:
			// making military station
			MilitaryBallotBox c = new MilitaryBallotBox(address);
			if (election.addToList(c)) {
				System.out.println("Military Station added successfully");
				return true;
			}
		}
		System.out.println("wrong command, no such polling station.");
		return false;
	}

	public static Boolean createVoter(Election election, Scanner scan) {
		System.out.println("Please enter Citizen's name: ");
		String name = scan.next();
		System.out.println("Please enter " + name + "'s ID number: ");
		int ID = scan.nextInt();
		scan.nextLine();
		System.out.println("Please enter " + name + "'s birth year:  	--->	format (dd/mm/yyyy)");
		String birthYear = scan.next();
		scan.nextLine();
		System.out.println("Is " + name + " infected with the corona virus?");
		System.out.println("1: yes");
		System.out.println("2: no");
		int sickness = scan.nextInt();
		scan.nextLine();
		Boolean isSick;
		if (sickness == 1) {
			isSick = true;
		} else
			isSick = false;
		System.out.println("Pick a Ballot Station for " + name + " to visit:");
		System.out.println(election.showAllBallotBox());
		System.out.print("Enter Ballot box ID for " + name + ", make sure they can visit it.");
		int ballotID = scan.nextInt();
		scan.nextLine();
		BallotBox ballot = election.findStationByID(ballotID);
		if (ballot == null)
			return false;
		Citizen temp = new Citizen(name, ID, birthYear, isSick, ballot);
		if (ballot.setVoter(temp) == false) {
			System.out.println("Couldn't add this voter to that Station, make sure they're able to go there\n");
			return false;
		}
		if (election.addToList(temp))
			System.out.println("Voter added successfully\n");
		return true;
	}

	public static Boolean createParty(Election election, Scanner scan) {
		System.out.println("Enter a name for this party:");
		String name = scan.next();
		System.out.println("What's " + name + "'s tendency?");
		System.out.println("1: Right");
		System.out.println("2: Center");
		System.out.println("3: Left");
		int factiontype = scan.nextInt();
		Party.faction faction;
		switch (factiontype) {
		case 1:
			faction = Party.faction.Right;
			break;
		case 2:
			faction = Party.faction.Center;
			break;
		case 3:
			faction = Party.faction.Left;
			break;
		default:
			System.out.println("no such faction");
			return false;
		}
		System.out.println("What's " + name + "'s year of creation?");
		int year = scan.nextInt();
		scan.nextLine();
		if (election.addToList(new Party(name, faction, year)) == true) {
			System.out.println("Party added successfully");
		}
		return true;
	}

	public static Boolean createCandidate(Election election, Scanner scan) {
		System.out.println("Please enter the name of the candidate:");
		String name = scan.next();
		System.out.println("Please enter " + name + "'s ID number: ");
		int ID = scan.nextInt();
		scan.nextLine();
		System.out.println("Please enter " + name + "'s birth year: 	--->	format (dd/mm/yyyy)");
		String birthYear = scan.next();
		scan.nextLine();
		System.out.println("Is " + name + " infected with the corona virus?");
		System.out.println("1: yes");
		System.out.println("2: no");
		int sickness = scan.nextInt();
		Boolean isSick;
		if (sickness == 1) {
			isSick = true;
		} else
			isSick = false;
		System.out.println("Pick a Ballot Station for " + name + " to visit:");
		System.out.println(election.showAllBallotBox());
		System.out.println("Enter Ballot box ID for " + name + ", make sure they can visit it.");
		int ballotID = scan.nextInt();
		System.out.println();
		BallotBox ballot = election.findStationByID(ballotID);
		if (ballot == null) {
			return false;
		}
		System.out.println("Pick a Party that " + name + " would represent:");
		System.out.println(election.showAllParty());
		System.out.println("Enter the name of the party (not case-sensitive):");
		scan.nextLine();
		String partyName = scan.nextLine();
		Party party = election.findPartyByName(partyName);
		if (party == null)
			return false;
		Candidate c = new Candidate(name, ID, birthYear, isSick, party, ballot);
		if (election.addToList(c) == true && party.addMember(c) == true)
			;
		System.out.println("Candidate created successfuly");
		return true;

	}
}
