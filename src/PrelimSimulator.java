import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PrelimSimulator {
    /* Instance Variables */
    private int numRoundsCompleted;
    private final ArrayList<Team> simulator;
    private final int[][] numPullups;

    /* Constructor Method */
    public PrelimSimulator() {
        // sets up the simulation
        this.numRoundsCompleted = 0;
        this.simulator = new ArrayList<>();
        this.numPullups = new int[Tournament.numPrelims][Tournament.numPrelims];

        // initializes teams
        for (int i = 0; i < Tournament.numTeams; i++) {
            this.simulator.add(new Team(Tournament.teamList[i], Tournament.schoolList[i],
                    Tournament.entryList[i], Tournament.skillList[i]));
        }
    }

    /* Get Method */
    public ArrayList<Team> getSimulator() { return this.simulator; }
    public int[][] getNumPullups() { return this.numPullups; }

    /* Simulate Methods */
    public void simulate() {
        // simulates the tournament
        for (int i = 0; i < Tournament.numPrelims; i++) {
            sideLocks();
            round();
            this.numRoundsCompleted++;
        }
        this.simulator.sort(Comparator.comparing(Team::getNumWins));
    }

    private void sideLocks() {
        Collections.shuffle(this.simulator);
        this.simulator.sort(Comparator.comparing(Team::getNumWins));

        // resets all pairings
        for (Team tempTeam : this.simulator) {
            tempTeam.setPaired(false);
        }

        // checks if odd number of teams
        if (Tournament.numTeams % 2 == 1) {
            // worst team --> BYE
            this.simulator.get(0).setSideLock(0);
            this.simulator.get(0).setPaired(true);
            this.simulator.get(0).setResult(true);
        }

        Collections.shuffle(this.simulator);
        this.simulator.sort(Comparator.comparing(Team::getPaired));
        this.simulator.sort(Comparator.comparing(Team::getNumLosses));

        // even round
        if (this.numRoundsCompleted % 2 == 0) {
            // loops through all teams
            for (int i = 0; i < Tournament.numTeams; i++) {

                // checks if already paired
                if (!this.simulator.get(i).getPaired()) {

                    // assigns every team different side
                    if (i % 2 == 0) {
                        // even teams --> AFF
                        this.simulator.get(i).setSideLock(1);
                    } else {
                        // odd teams --> NEG
                        this.simulator.get(i).setSideLock(-1);
                    }
                }
            }
        }

        // odd round
        else {
            // flips every lock
            for (Team tempTeam : this.simulator) {
                tempTeam.flipSideLock();
            }
        }
    }

    private void round() {
        Collections.shuffle(this.simulator);
        this.simulator.sort(Comparator.comparing(Team::getNumLosses));
        Team team1;
        Team team2;

        // loops through team 1
        for (int i = 0; i < Tournament.numTeams; i++) {
            team1 = this.simulator.get(i);

            // loops through team 2
            for (int j = 0; j < Tournament.numTeams; j++) {
                team2 = this.simulator.get(j);

                // checks compatibility
                if (!team1.getPaired() && !team2.getPaired() && i != j &&
                        (team1.getSideLock() == -team2.getSideLock() || numRoundsCompleted <= 2) &&
                        (!team1.getSchool().equals(team2.getSchool()) || Tournament.teamList[i] == null) &&
                        !team1.hasHitBefore(team2.getTeamName())) {

                    // pairing
                    this.simulator.get(i).setPaired(true);
                    this.simulator.get(i).setPairingHistory(this.numRoundsCompleted, team2.getTeamName());
                    this.simulator.get(j).setPaired(true);
                    this.simulator.get(j).setPairingHistory(this.numRoundsCompleted, team1.getTeamName());

                    // updates pullup record
                    if (team1.getNumWins() != team2.getNumWins()) {
                        numPullups[numRoundsCompleted][Math.max(team1.getNumWins(), team2.getNumWins())]++;
                    }

                    // result
                    if (team1.getSkill() > team2.getSkill()) {
                        // team 1 won, team 2 lost
                        this.simulator.get(i).setResult(true);
                        this.simulator.get(j).setResult(false);
                    }
                    else if (team1.getSkill() < team2.getSkill()) {
                        // team 1 lost, team 2 won
                        this.simulator.get(i).setResult(false);
                        this.simulator.get(j).setResult(true);
                    }
                    else {
                        // random chance who wins
                        boolean coin = Math.random() >= 0.5;
                        this.simulator.get(i).setResult(coin);
                        this.simulator.get(j).setResult(!coin);
                    }
                }
            }

            // in case there is no more matches left
            if (!team1.getPaired()) {
                this.simulator.get(i).setPaired(true);
                this.simulator.get(i).setResult(true);
            }
        }
    }
}