public class Calculator {
    /* Instance Variables */
    private int numElimsAdjusted;
    private double numTeamsPositive;
    private double numTeamsNegative;
    private double numTeamsBreaking;
    private double numTeamsScrewed;
    private double numTeamsDebating;
    private double numTeamsByed;
    private double numConcurrentElims;
    private final double[] record;
    private final double[][] pullupRecord;
    private final double[] breakRecord;
    private final double[] screwRecord;
    private final double[] debateRecord;
    private final double[] byeRecord;

    /* Constructor Method */
    public Calculator() {
        this.numElimsAdjusted = 0;
        this.numTeamsPositive = 0.0;
        this.numTeamsNegative = 0.0;
        this.numTeamsBreaking = 0.0;
        this.numTeamsScrewed = 0.0;
        this.numTeamsDebating = 0.0;
        this.numTeamsByed = 0.0;
        this.numConcurrentElims = 0.0;

        this.record = new double[Statistics.numPrelims + 1];
        this.pullupRecord = new double[Statistics.numPrelims][Statistics.numPrelims];
        this.breakRecord = new double[Statistics.numPrelims + 1];
        this.screwRecord = new double[Statistics.numPrelims + 1];
        this.debateRecord = new double[Statistics.numPrelims + 1];
        this.byeRecord = new double[Statistics.numPrelims + 1];
    }

    /* Get Methods */
    public int getElimsAdjusted() { return this.numElimsAdjusted; }
    public double getTeamsPositive() { return this.numTeamsPositive; }
    public double getTeamsNegative() { return this.numTeamsNegative; }
    public double getTeamsBreaking() { return this.numTeamsBreaking; }
    public double getTeamsScrewed() { return this.numTeamsScrewed; }
    public double getTeamsDebating() { return this.numTeamsDebating; }
    public double getTeamsByed() { return this.numTeamsByed; }
    public double getConcurrentElims() { return this.numConcurrentElims; }
    public double[] getRecord() { return this.record; }
    public double[][] getPullupRecord() { return this.pullupRecord; }
    public double[] getBreakRecord() { return this.breakRecord; }
    public double[] getScrewRecord() { return this.screwRecord; }
    public double[] getDebateRecord() { return this.debateRecord; }
    public double[] getByeRecord() { return this.byeRecord; }

    /* Iterate Method */
    public void calculate() {
        // temporary values
        int currElimsAdjusted;
        int currTeamsPositive;
        int currTeamsNegative;
        int currTeamsBreaking;
        int currTeamsScrewed;
        int currTeamsDebating;
        int currTeamsByed;
        int currConcurrentElims;
        int[][] currPullupRecord;

        // loops a specified number of times
        for (int i = 0; i < Statistics.numIterations; i++) {

            // runs the simulator
            Simulator tempSimulator = new Simulator();
            tempSimulator.simulate();

            // loops through every team
            currTeamsPositive = 0;
            currTeamsNegative = 0;
            for (Team tempTeam : tempSimulator.getSimulator()) {
                // counts the number of teams that broke
                if (tempTeam.getNumWins() > tempTeam.getNumLosses()) {
                    currTeamsPositive++;
                }
                else {
                    currTeamsNegative++;
                }
                // this.records the number of teams in each bracket
                this.record[tempTeam.getNumWins()]++;
            }

            // adjusts the number of elimination rounds
            currElimsAdjusted = Statistics.numElims;
            while (currTeamsPositive < Math.pow(2, currElimsAdjusted - 1)) {
                currElimsAdjusted--;
            }
            while (currTeamsPositive > Math.pow(2, currElimsAdjusted + 1)) {
                currElimsAdjusted++;
            }

            // calculates elim statistics
            currTeamsBreaking = (int) (Math.min(currTeamsPositive, Math.pow(2, currElimsAdjusted)));
            currTeamsByed = (int) (Math.pow(2, currElimsAdjusted) - currTeamsBreaking);
            currTeamsDebating = (int) (2 * currTeamsBreaking - Math.pow(2, currElimsAdjusted));
            currTeamsScrewed = currTeamsPositive - currTeamsBreaking;
            currConcurrentElims = currTeamsDebating / 2;

            // calculates other this.records
            for (int j = currTeamsNegative; j < Statistics.numTeams; j++) {
                int tempWins = tempSimulator.getSimulator().get(j).getNumWins();

                // checks which group they are in
                if (j < currTeamsNegative + currTeamsScrewed) {
                    this.screwRecord[tempWins]++;
                }
                else if (j < currTeamsNegative + currTeamsScrewed + currTeamsDebating) {
                    this.debateRecord[tempWins]++;
                    this.breakRecord[tempWins]++;
                }
                else if (j < currTeamsNegative + currTeamsScrewed + currTeamsDebating + currTeamsByed) {
                    this.byeRecord[tempWins]++;
                    this.breakRecord[tempWins]++;
                }
            }

            // gets the pullup this.record
            currPullupRecord = tempSimulator.getNumPullups();
            for (int j = 0; j < Statistics.numPrelims; j++) {
                for (int k = 0; k < Statistics.numPrelims; k++) {
                    this.pullupRecord[j][k] += currPullupRecord[j][k];
                }
            }

            // adds it to the total
            this.numElimsAdjusted += currElimsAdjusted;
            this.numTeamsPositive += currTeamsPositive;
            this.numTeamsNegative += currTeamsNegative;
            this.numTeamsBreaking += currTeamsBreaking;
            this.numTeamsScrewed += currTeamsScrewed;
            this.numTeamsDebating += currTeamsDebating;
            this.numTeamsByed += currTeamsByed;
            this.numConcurrentElims += currConcurrentElims;
        }

        // updates class variables
        this.numElimsAdjusted /= Statistics.numIterations;
        this.numTeamsPositive /= Statistics.numIterations;
        this.numTeamsNegative /= Statistics.numIterations;
        this.numTeamsBreaking /= Statistics.numIterations;
        this.numTeamsScrewed /= Statistics.numIterations;
        this.numTeamsDebating /= Statistics.numIterations;
        this.numTeamsByed /= Statistics.numIterations;
        this.numConcurrentElims /= Statistics.numIterations;

        for (int i = 0; i < Statistics.numPrelims + 1; i++) {
            this.record[i] /= Statistics.numIterations;
            this.breakRecord[i] /= Statistics.numIterations;
            this.screwRecord[i] /= Statistics.numIterations;
            this.debateRecord[i] /= Statistics.numIterations;
            this.byeRecord[i] /= Statistics.numIterations;
        }

        for (int i = 0; i < Statistics.numPrelims; i++) {
            for (int j = 0; j < Statistics.numPrelims; j++) {
                this.pullupRecord[i][j] /= Statistics.numIterations;
            }
        }
    }
}