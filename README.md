# Managing an election campaign during the Corona period


Description:
The project involves managing an election system during the Corona period in an anonymous country in the Middle East. The goal is to computerize and streamline the system for managing election rounds. The system requires storing data for each election round, including the month and year of the election, the voter register, candidate parties, and ballot boxes. Special types of ballot boxes are introduced, namely Corona polling stations and military polling stations, to handle specific voting scenarios during the pandemic.

Functionalities:
The project implements the following functionalities:

Adding a ballot: Enables the addition of a new ballot to the system.
Adding a citizen: Allows the inclusion of a new citizen in the voter register.
Adding a party: Facilitates the addition of a new political party participating in the election round.
Adding a citizen as a candidate: Enables assigning a citizen as a candidate on behalf of a specific party.
Displaying all ballot boxes: Shows the details of all the ballot boxes, including their associated citizens and voting percentages.
Presentation of all citizens: Displays information about all the citizens registered for voting.
Presentation of all parties: Shows details of all the political parties participating in the election.
Elections: Initiates the voting process, allowing each citizen to cast their vote and choose a party.
Showing election results: Presents the vote counts for each party from each ballot box and the overall total.
Subjects Used:
The project incorporates the following subjects:

Inheritance: Inheritance is utilized to establish relationships between classes such as Citizen and Candidate, and BallotBox and its subclasses (CoronaPollingStation and MilitaryPollingStation).
Polymorphism: Polymorphism is employed to treat objects of different types as instances of a common superclass, such as treating different types of ballot boxes as BallotBox objects.
Object class: The Object class serves as the superclass for all other classes and is overridden in various classes like Citizen, Party, Candidate, BallotBox, CoronaPollingStation, and MilitaryPollingStation to provide custom implementations of methods such as toString and equals.
These subjects are leveraged to establish class relationships, enable code reuse, and customize behavior in the election management system.
