import java.text.DecimalFormat;

public class Main {
    /* Global Variables */
    public static int numTeams;
    public static int numPrelims;
    public static int numElims;
    public static int numIterations;
    public static String[] teamList;
    public static String[] schoolList;
    public static String[] entryList;
    public static double[] skillList;
    public static Calculator calculator;
    private static final DecimalFormat roundToDecimal = new DecimalFormat("0.#");
    private static final DecimalFormat roundToWhole = new DecimalFormat("0");

    /* Main Method */
    public static void main(String[] args) {
        // initializes starting values
        numIterations = 100;

        // receives global variables from input
        System.out.println("—————————————————————————————————————————————————————————");
        System.out.println("Tournament values:");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        numTeams = Filter.filterWholeNum("Number of teams debating: ");
        numPrelims = Filter.filterWholeNum("Number of prelim rounds: ");
        numElims = Filter.filterWholeNum("Number of elim rounds: ");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        schoolList = Filter.filterCode("List of team codes: ");
        entryList = Filter.filterEntry("List of entry names: ");
        skillList = Filter.filterDouble("List of team elos: ");
        System.out.println("—————————————————————————————————————————————————————————");

        // initializes the list of teams
        teamList = new String[numTeams];
        for (int i = 0; i < numTeams; i++) {
            teamList[i] = schoolList[i] + entryList[i];
        }

        // iterates
        calculator = new Calculator();
        calculator.calculate();

        // runs the tournament simulator
        prelimStatistics();
        elimStatistics();
        if (!teamList[0].equals("School0Entry0")) {
            wikis();
        }
    }

    /* Statistics Methods */
    public static void prelimStatistics() {
        // statistics
        double[] record = calculator.getRecord();
        System.out.println("Preliminary round statistics:");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

        // prints expected record statistics
        for (int i = 0; i < numPrelims + 1; i ++) {
            System.out.println("Expected teams " + i + "-" +
                    (numPrelims - i) + ": " + roundToDecimal.format(record[i]));
        }

        prelimPullupStatistics();

        System.out.println("—————————————————————————————————————————————————————————");
    }

    public static void prelimPullupStatistics() {
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        boolean printedRound;

        for (int i = 0; i < numPrelims; i++) {
            printedRound = false;

            for (int j = 0; j < numPrelims; j++) {
                if (calculator.getPullupRecord()[i][j] > 0) {
                    if (!printedRound) {
                        System.out.println("Expected pullups round " + (i + 1) + ":");
                        printedRound = true;
                    }

                    System.out.println("From " +
                            (j - 1) + "-" + (i + 1 - j) + " to " + (j) + "-" + (i - j)
                            + ": " + calculator.getPullupRecord()[i][j]);
                }
            }
        }
    }

    public static void elimStatistics() {

        System.out.println("Elimination round statistics:");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

        // prints expected elim size
        if (calculator.getElimsAdjusted() != numElims) {
            // yes change --> tells the user
            System.out.println("The tournament can not break to " + Name.elimName(numElims) + ".");
            System.out.println("Reason: Number of teams debating is " + Name.elimError(calculator.getElimsAdjusted()) + ".");
            System.out.println("The tournament must break to a " + Name.elimSize(calculator.getTeamsByed())
                    + " " + Name.elimName(calculator.getElimsAdjusted()) + ".");
        }
        else {
            // no change --> does not tell the user
            System.out.println("The tournament will break to a " + Name.elimSize(calculator.getTeamsByed())
                    + " " + Name.elimName(calculator.getElimsAdjusted()) + ".");
        }
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

        // prints expected elim statistics
        System.out.println("Expected teams positive: " + roundToDecimal.format(calculator.getTeamsPositive()));
        System.out.println("Expected teams negative: " + roundToDecimal.format(calculator.getTeamsNegative()));
        System.out.println("Expected teams breaking: " + roundToDecimal.format(calculator.getTeamsBreaking()));
        System.out.println("Expected teams screwed: " + roundToDecimal.format(calculator.getTeamsScrewed()));
        System.out.println("Expected teams debating: " + roundToDecimal.format(calculator.getTeamsDebating()));
        System.out.println("Expected teams byed: " + roundToDecimal.format(calculator.getTeamsByed()));
        System.out.println("Expected number of elim debates: " + roundToWhole.format(calculator.getConcurrentElims()));
        elimBreakStatistics();
        elimScrewStatistics();
        elimDebateStatistics();
        elimByeStatistics();
        System.out.println("—————————————————————————————————————————————————————————");
    }

    public static void elimBreakStatistics() {
        // loops through every bracket
        if (calculator.getTeamsBreaking() > 0) {
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

            for (int i = 0; i < numPrelims + 1; i++) {

                // if team in that bracket breaking --> print them
                if (calculator.getBreakRecord()[i] > 0) {
                    System.out.println("Expected teams " + i + "-" + (numPrelims - i) +
                            " that break: " + roundToDecimal.format(calculator.getBreakRecord()[i]));
                }
            }
        }
    }

    public static void elimScrewStatistics() {
        // loops through every bracket
        if (calculator.getTeamsScrewed() > 0) {
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

            for (int i = 0; i < numPrelims + 1; i++) {

                // if team in that bracket screwed --> print them
                if (calculator.getScrewRecord()[i] > 0) {
                    System.out.println("Expected teams " + i + "-" + (numPrelims - i) +
                            " that are screwed: " + roundToDecimal.format(calculator.getScrewRecord()[i]));
                }
            }
        }
    }

    public static void elimDebateStatistics() {
        // loops through every bracket
        if (calculator.getTeamsDebating() > 0) {
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

            for (int i = 0; i < numPrelims + 1; i++) {

                // if team in that bracket debating --> print them
                if (calculator.getDebateRecord()[i] > 0) {
                    System.out.println("Expected teams " + i + "-" + (numPrelims - i) +
                            " that are debating: " + roundToDecimal.format(calculator.getDebateRecord()[i]));
                }
            }
        }
    }

    public static void elimByeStatistics() {
        // loops through every bracket
        if (calculator.getTeamsByed() > 0) {
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

            for (int i = 0; i < numPrelims + 1; i++) {

                // if team in that bracket byed --> print them
                if (calculator.getByeRecord()[i] > 0) {
                    System.out.println("Expected teams " + i + "-" + (numPrelims - i) +
                            " that are byed: " + roundToDecimal.format(calculator.getByeRecord()[i]));
                }
            }
        }
    }

    public static void wikis() {
        System.out.println("Expected team wikis: ");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

        // loops through every team and prints the wiki link format of it
        for (int i = 0; i < numTeams; i++) {
            System.out.println("https://opencaselist.com/hspolicy23/" + schoolList[i] + "/" + entryList[i]);
        }

        System.out.println("—————————————————————————————————————————————————————————");
    }
}