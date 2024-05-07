import java.util.Scanner;

public class Main {
    /* Global Variables */
    public static int numTeams;
    public static int numPrelims;
    public static int numElims;
    public static int numIterations;

    /* Main Method */
    public static void main(String[] args) {
        // characteristics
        Scanner input = new Scanner(System.in);
        numIterations = 100;

        // statistics
        int[] record;
        int numTeamsPositive;
        int numTeamsBreaking;
        int numTeamsByed;
        int numTeamsDebating;
        int numTeamsScrewed;
        int numConcurrentElims;

        // receives global variables from input
        System.out.println("_______________________________________________");
        System.out.print("Number of teams debating: ");
        numTeams = Math.abs(input.nextInt());
        System.out.print("Number of prelim rounds: ");
        numPrelims = Math.abs(input.nextInt());
        System.out.print("Number of elim rounds: ");
        numElims = Math.abs(input.nextInt());

        // iterates
        Iterator tempIterator = new Iterator();
        tempIterator.iterate();
        record = tempIterator.getRecord();
        numTeamsPositive = tempIterator.getNumTeamsPositive();

        // prints expected record
        System.out.println("_______________________________________________");
        for (int i = 0; i < numPrelims + 1; i ++) {
            System.out.println("Expected teams " + i + "-" + (numPrelims - i) + ": " + record[i]);
        }

        // checks if there are too many teams positive
        if (numTeamsPositive < 2 * Math.pow(2, numElims)) {
            numTeamsBreaking = numTeamsPositive;
        }
        else {
            numTeamsBreaking = (int) (2 * Math.pow(2, numElims));
        }

        // calculates statistics
        numTeamsByed = (int) (2 * Math.pow(2, numElims)) - numTeamsBreaking;
        numTeamsDebating = numTeamsBreaking - numTeamsByed;
        numTeamsScrewed = numTeamsPositive - numTeamsBreaking;
        numConcurrentElims = (numTeamsBreaking - numTeamsByed) / 2;

        // prints expected statistics
        System.out.println("_______________________________________________");
        System.out.println("The tournament will break to " + elimName(numElims) + ".");
        System.out.println("_______________________________________________");
        System.out.println("Expected teams in elims: " + numTeamsBreaking);
        System.out.println("Expected teams byed: " + numTeamsByed);
        System.out.println("Expected teams debating: " + numTeamsDebating);
        System.out.println("Expected teams screwed: " + numTeamsScrewed);
        System.out.println("Expected number of elim debates: " + numConcurrentElims);
        System.out.println("_______________________________________________");

        input.close();
    }

    /* Name Method */
    public static String elimName(int inputNumElims) {
        // returns the name for the given elimination round
        return switch (inputNumElims) {
            case 0 -> "Champions";
            case 1 -> "Finals";
            case 2 -> "Semifinals";
            case 3 -> "Quarterfinals";
            case 4 -> "Octafinals";
            case 5 -> "Doubleoctafinals";
            case 6 -> "Tripleoctafinals";
            case 7 -> "Quarteroctafinals";
            default -> ("[n/a]");
        };
    }
}