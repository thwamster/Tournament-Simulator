import java.text.DecimalFormat;

public class Statistics {
    private static int numElimsAdjusted = 0;
    private static double numTeamsPositive = 0.0;
    private static double numTeamsNegative = 0.0;
    private static double numTeamsBreaking = 0.0;
    private static double numTeamsScrewed = 0.0;
    private static double numTeamsDebating = 0.0;
    private static double numTeamsByed = 0.0;
    private static double numConcurrentElims = 0.0;
    private static final double[] record = new double[Tournament.numPrelims + 1];
    private static final double[][] pullupRecord = new double[Tournament.numPrelims][Tournament.numPrelims];
    private static final double[] breakRecord = new double[Tournament.numPrelims + 1];
    private static final double[] screwRecord = new double[Tournament.numPrelims + 1];
    private static final double[] debateRecord = new double[Tournament.numPrelims + 1];
    private static final double[] byeRecord = new double[Tournament.numPrelims + 1];
    private static final DecimalFormat roundToDecimal = new DecimalFormat("0.#");
    private static final DecimalFormat roundToWhole = new DecimalFormat("0");

    /* Calculate Method */
    public static void calculate() {
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
        for (int i = 0; i < Tournament.numIterations; i++) {

            // runs the simulator
            PrelimSimulator tempSimulator = new PrelimSimulator();
            tempSimulator.simulate();

            // loops through every team
            currTeamsPositive = 0;
            currTeamsNegative = 0;
            for (Team tempTeam : tempSimulator.getSimulator()) {
                // counts the number of teams that broke
                if (2 * tempTeam.getNumWins() > 3 * tempTeam.getNumLosses()) {
                    currTeamsPositive++;
                } else {
                    currTeamsNegative++;
                }
                // records the number of teams in each bracket
                record[tempTeam.getNumWins()]++;
            }

            // adjusts the number of elimination rounds
            currElimsAdjusted = Tournament.numElims;
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

            // calculates other records
            for (int j = currTeamsNegative; j < Tournament.numTeams; j++) {
                int tempWins = tempSimulator.getSimulator().get(j).getNumWins();

                // checks which group they are in
                if (j < currTeamsNegative + currTeamsScrewed) {
                    screwRecord[tempWins]++;
                } else if (j < currTeamsNegative + currTeamsScrewed + currTeamsDebating) {
                    debateRecord[tempWins]++;
                    breakRecord[tempWins]++;
                } else if (j < currTeamsNegative + currTeamsScrewed + currTeamsDebating + currTeamsByed) {
                    byeRecord[tempWins]++;
                    breakRecord[tempWins]++;
                }
            }

            // gets the pullup record
            currPullupRecord = tempSimulator.getNumPullups();
            for (int j = 0; j < Tournament.numPrelims; j++) {
                for (int k = 0; k < Tournament.numPrelims; k++) {
                    pullupRecord[j][k] += currPullupRecord[j][k];
                }
            }

            // adds it to the total
            numElimsAdjusted += currElimsAdjusted;
            numTeamsPositive += currTeamsPositive;
            numTeamsNegative += currTeamsNegative;
            numTeamsBreaking += currTeamsBreaking;
            numTeamsScrewed += currTeamsScrewed;
            numTeamsDebating += currTeamsDebating;
            numTeamsByed += currTeamsByed;
            numConcurrentElims += currConcurrentElims;
        }

        // updates class variables
        numElimsAdjusted /= Tournament.numIterations;
        numTeamsPositive /= Tournament.numIterations;
        numTeamsNegative /= Tournament.numIterations;
        numTeamsBreaking /= Tournament.numIterations;
        numTeamsScrewed /= Tournament.numIterations;
        numTeamsDebating /= Tournament.numIterations;
        numTeamsByed /= Tournament.numIterations;
        numConcurrentElims /= Tournament.numIterations;

        for (int i = 0; i < Tournament.numPrelims + 1; i++) {
            record[i] /= Tournament.numIterations;
            breakRecord[i] /= Tournament.numIterations;
            screwRecord[i] /= Tournament.numIterations;
            debateRecord[i] /= Tournament.numIterations;
            byeRecord[i] /= Tournament.numIterations;
        }

        for (int i = 0; i < Tournament.numPrelims; i++) {
            for (int j = 0; j < Tournament.numPrelims; j++) {
                pullupRecord[i][j] /= Tournament.numIterations;
            }
        }

        // updates global tournament values
        Tournament.numElims = numElimsAdjusted;
        Tournament.numTeamsBreaking = (int) numTeamsBreaking;
    }

    /* Prelim Methods */
    public static void printPrelims() {
        // prints statistics
        System.out.println("Preliminary round statistics:");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        printPrelimRecords();
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        printPrelimPullups();
        System.out.println("—————————————————————————————————————————————————————————");
    }

    private static void printPrelimRecords() {
        // prints expected record statistics
        for (int i = 0; i < Tournament.numPrelims + 1; i ++) {
            System.out.println("Expected teams " + i + "-" +
                    (Tournament.numPrelims - i) + ": " + roundToDecimal.format(record[i]));
        }
    }

    private static void printPrelimPullups() {
        // statistics
        boolean printedRound;

        // loops through every prelim
        for (int i = 0; i < Tournament.numPrelims; i++) {
            printedRound = false;

            // loops through every bracket
            for (int j = 0; j < Tournament.numPrelims; j++) {

                // checks if there are any pullups
                if (pullupRecord[i][j] > 0) {

                    // prints only once per round
                    if (!printedRound) {
                        System.out.println("Expected pullups round " + (i + 1) + ":");
                        printedRound = true;
                    }

                    // prints the record
                    System.out.println("From " +
                            (j - 1) + "-" + (i + 1 - j) + " to " + (j) + "-" + (i - j)
                            + ": " + roundToDecimal.format(pullupRecord[i][j]));
                }
            }
        }
    }

    /* Elim Methods */
    public static void printElims() {
        // prints statistics
        System.out.println("Elimination round statistics:");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        printElimAdjust();
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        printElimBasic();
        if (numTeamsBreaking > 0) {
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            printElimBreak();
        }
        if (numTeamsScrewed > 0) {
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            printElimScrews();
        }
        if (numTeamsDebating > 0) {
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            printElimDebates();
        }
        if (numTeamsByed > 0) {
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            printElimByes();
        }
        System.out.println("—————————————————————————————————————————————————————————");
    }

    private static void printElimAdjust() {
        // prints expected elim size
        if (numElimsAdjusted != Tournament.numElims) {
            // yes change --> tells the user
            System.out.println("The tournament can not break to " + ElimName.elimName(Tournament.numElims) + ".");
            System.out.println("Reason: Number of teams debating is " + ElimName.elimError(Tournament.numElims, numElimsAdjusted) + ".");
            System.out.println("The tournament must break to a " + ElimName.elimSize(numTeamsByed)
                    + " " + ElimName.elimName(numElimsAdjusted) + ".");
        }
        else {
            // no change --> does not tell the user
            System.out.println("The tournament will break to a " + ElimName.elimSize(numTeamsByed)
                    + " " + ElimName.elimName(numElimsAdjusted) + ".");
        }
    }

    private static void printElimBasic() {
        // prints expected elim information
        System.out.println("Expected teams positive: " + roundToDecimal.format(numTeamsPositive));
        System.out.println("Expected teams negative: " + roundToDecimal.format(numTeamsNegative));
        System.out.println("Expected teams breaking: " + roundToDecimal.format(numTeamsBreaking));
        System.out.println("Expected teams screwed: " + roundToDecimal.format(numTeamsScrewed));
        System.out.println("Expected teams debating: " + roundToDecimal.format(numTeamsDebating));
        System.out.println("Expected teams byed: " + roundToDecimal.format(numTeamsByed));
        System.out.println("Expected number of elim debates: " + roundToWhole.format(numConcurrentElims));
    }

    private static void printElimBreak() {

        // loops through every bracket
        for (int i = 0; i < Tournament.numPrelims + 1; i++) {

            // if team in that bracket breaking --> print them
            if (breakRecord[i] > 0) {
                System.out.println("Expected teams " + i + "-" + (Tournament.numPrelims - i) +
                        " that break: " + roundToDecimal.format(breakRecord[i]));
            }
        }
    }

    private static void printElimScrews() {

        // loops through every bracket
        for (int i = 0; i < Tournament.numPrelims + 1; i++) {

            // if team in that bracket screwed --> print them
            if (screwRecord[i] > 0) {
                System.out.println("Expected teams " + i + "-" + (Tournament.numPrelims - i) +
                        " that are screwed: " + roundToDecimal.format(screwRecord[i]));
            }
        }
    }

    private static void printElimDebates() {

        // loops through every bracket
        for (int i = 0; i < Tournament.numPrelims + 1; i++) {

            // if team in that bracket debating --> print them
            if (debateRecord[i] > 0) {
                System.out.println("Expected teams " + i + "-" + (Tournament.numPrelims - i) +
                        " that are debating: " + roundToDecimal.format(debateRecord[i]));
            }
        }
    }

    private static void printElimByes() {
        // loops through every bracket
        for (int i = 0; i < Tournament.numPrelims + 1; i++) {

            // if team in that bracket byed --> print them
            if (byeRecord[i] > 0) {
                System.out.println("Expected teams " + i + "-" + (Tournament.numPrelims - i) +
                        " that are byed: " + roundToDecimal.format(byeRecord[i]));
            }
        }
    }
}