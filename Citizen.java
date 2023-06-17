
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.format.DateTimeFormatter;

//import javafx.util.converter.LocalDateStringConverter;

public class Citizen extends Election {
	private String name;
	private int id;
	private String bornDate;
	private int age;
	protected boolean situation;
	protected boolean hasVoted = false;
	protected Party voteFor;
	protected BallotBox ballotBoxBelong;
	protected static int citizenCounter = 0;

	public Citizen(String name, int id, String bornDate, boolean situation, BallotBox ballotBelong) {
		this.name = name;
		setId(id);
		this.bornDate = bornDate;
		this.situation = situation;
		ageForNow(bornDate);
		this.ballotBoxBelong = ballotBelong;
		citizenCounter++;
	}

	public Citizen(String name, int id, String bornDate, boolean situation) {
		this.name = name;
		setId(id);
		this.bornDate = bornDate;
		this.situation = situation;
		ageForNow(bornDate);
		citizenCounter++;
	}

	public boolean setBallotBox(BallotBox ballot) {
		if (ballotBoxBelong == null) {
			ballotBoxBelong = ballot;
			return true;
		}
		return false;
	}

	public boolean setSituation(boolean situation) {
		this.situation = situation;
		return true;
	}

	public Boolean setName(String name) {
		if (name == "")
			return false;
		this.name = name;
		return true;
	}

	public boolean setBornDate(String bornDate) {
		this.bornDate = bornDate;
		return true;
	}

	private boolean setId(int id) {
		int length = (int) (Math.log10(id) + 1);
		if (length == 9) {
			this.id = id;
			return true;
		}
		return false;
	}

	public boolean setHasVoted(boolean hasVoted) {
		this.hasVoted = hasVoted;
		return true;
	}

	public boolean getHasVoted() {
		return hasVoted;
	}

	public boolean setVoteFor(Party voteFor) {
		if (hasVoted == false) {
			this.voteFor = voteFor;
			return true;
		}
		return false;
	}

	public BallotBox getBallotBox() {
		return ballotBoxBelong;
	}

	public Party getVoteFor() {
		return voteFor;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public boolean getSituation() {
		return situation;
	}

	public String getBornDate() {
		return bornDate;
	}

	public int getAge() {
		return age;
	}

	public String isQuarantine() {
		String s;
		if (this.situation == true) {
			s = "Yes ----> important!!! ";
		} else
			s = "No";
		return s;
	}

	public void ageForNow(String bornDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate birthDate = LocalDate.parse(bornDate, formatter);
		LocalDate now = LocalDate.now();
		this.age = Period.between(birthDate, now).getYears();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Citizen && this.id == ((Citizen) obj).id) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "name: " + name + "\nid: " + id + "\nborn date: " + bornDate + "\nin Quarantine: " + isQuarantine();
	}

}
