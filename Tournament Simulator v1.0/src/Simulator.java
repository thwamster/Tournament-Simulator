import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Simulator {
    /* Instance Variables */
    private int numRoundsCompleted;
    private final ArrayList<Team> simulator;

    /* Constructor Method */
    public Simulator() {
        // sets up the simulation
        this.numRoundsCompleted = 0;
        this.simulator = new ArrayList<>();

        // assigns skill rating
        Random skillGenerator = new Random();
        for (int i = 0; i < Main.numTeams; i++) {
            this.simulator.add(new Team((int) (skillGenerator.nextGaussian() * 1000)));
        }
    }

    /* Get Method */
    public ArrayList<Team> getSimulator() {
        return this.simulator;
    }

    /* Simulate Methods */
    public void simulate() {
        // simulates the tournament
        for (int i = 0; i < Main.numPrelims; i++) {
            sideLocks();
            round();
            this.numRoundsCompleted++;
        }
    }

    public void sideLocks() {
        this.simulator.sort(Comparator.comparing(Team::getNumWins));

        // resets all pairings
        for (Team tempTeam : simulator) {
            tempTeam.setPaired(false);
        }

        // checks if odd number of teams
        if (Main.numTeams % 2 == 1) {
            // worst team --> BYE
            this.simulator.get(0).setSideLock(0);
            this.simulator.get(0).setPaired(true);
            this.simulator.get(0).setResult(true);
        }

        Collections.shuffle(this.simulator);
        this.simulator.sort(Comparator.comparing(Team::getPaired));

        // even round
        if (this.numRoundsCompleted % 2 == 0) {
            // loops through all teams
            for (int i = 0; i < Main.numTeams; i++) {

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

    public void round() {
        this.simulator.sort(Comparator.comparing(Team::getNumLosses));
        Team team1;
        Team team2;

        // loops through team 1
        for (int i = 0; i < Main.numTeams; i++) {
            team1 = this.simulator.get(i);

            // loops through team 2
            for (int j = 0; j < Main.numTeams; j++) {
                team2 = this.simulator.get(j);

                // checks compatibility
                if (!team1.getPaired() && !team2.getPaired() &&
                     team1.getSideLock() == -team2.getSideLock()) {
                    // pairing
                    this.simulator.get(i).setPaired(true);
                    this.simulator.get(j).setPaired(true);

                    // result
                    if (team1.getSkill() >= team2.getSkill()) {
                        // team 1 won, team 2 lost
                        this.simulator.get(i).setResult(true);
                        this.simulator.get(j).setResult(false);
                    }
                    else {
                        // team 1 lost, team 2 won
                        this.simulator.get(i).setResult(false);
                        this.simulator.get(j).setResult(true);
                    }
                }
            }
        }
    }
}