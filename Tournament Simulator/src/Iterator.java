public class Iterator {
    /* Instance Variables */
    private int numTeamsPositive;
    private final int[] record;

    /* Constructor Method */
    public Iterator() {
        this.numTeamsPositive = 0;
        this.record = new int[Main.numPrelims + 1];
    }

    /* Get Methods */
    public int[] getRecord() { return this.record; }
    public int getNumTeamsPositive() { return this.numTeamsPositive; }

    /* Iterate Method */
    public void iterate() {
        int totalTeamsPositive = 0;
        int[] totalRecord = new int[Main.numPrelims + 1];

        // loops a specified number of times
        for (int i = 0; i < Main.numIterations; i++) {
            // runs the simulator
            Simulator tempSimulator = new Simulator();
            tempSimulator.simulate();

            // loops through every team
            for (Team tempTeam : tempSimulator.getSimulator()) {
                // counts the number of teams that broke
                if (tempTeam.getNumWins() > tempTeam.getNumLosses()) {
                    totalTeamsPositive++;
                }
                // records the number of teams in each bracket
                totalRecord[tempTeam.getNumWins()]++;
            }
        }

        // updates class variables
        this.numTeamsPositive = totalTeamsPositive / Main.numIterations;
        for (int i = 0; i < Main.numPrelims + 1; i++) {
            this.record[i] = totalRecord[i] / Main.numIterations;
        }
    }
}