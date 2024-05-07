public class Iterator {
    /* Instance Variables */
    private int numTeamsPositive;
    private final int[] record;

    /* Constructor Method */
    public Iterator() {
        numTeamsPositive = 0;
        record = new int[Main.numPrelims + 1];
    }

    /* Iterate Method */
    public void iterate() {
        int totalTeamsPositive = 0;
        int[] totalRecord = new int[Main.numPrelims + 1];

        // loops a specified number of times
        for (int i = 0; i < Main.numIterations; i++) {
            // runs the simulator
            Simulator tempSimulator = new Simulator();
            tempSimulator.simulate();

            // counts the number of teams that broke
            for (Team tempTeam : tempSimulator.getSimulator()) {
                if (tempTeam.getNumWins() > tempTeam.getNumLosses()) {
                    totalTeamsPositive++;
                }
            }
            // records the number of teams n each bracket
            for (Team tempTeam : tempSimulator.getSimulator()) {
                totalRecord[tempTeam.getNumWins()]++;
            }
        }

        // updates class variables
        numTeamsPositive = totalTeamsPositive / Main.numIterations;
        for (int i = 0; i < Main.numPrelims + 1; i++) {
            record[i] = totalRecord[i] / Main.numIterations;
        }
    }

    /* Get Methods */
    public int[] getRecord() {
        return this.record;
    }
    public int getNumTeamsPositive() {
        return this.numTeamsPositive;
    }
}