import java.util.Arrays;
import java.util.Scanner;

public class Main {
    /* Global Variables */
    public static int numTeams;
    public static int numPrelims;
    public static int numElims;
    public static int numIterations;
    public static Iterator iterator;

    /* Main Method */
    public static void main(String[] args) {
        // initializes starting values
        numIterations = 1000;

        // receives global variables from input
        System.out.println("—————————————————————————————————————————————————————————");
        numTeams = inputRecieve("Number of teams debating: ");
        numPrelims = inputRecieve("Number of prelim rounds: ");
        numElims = inputRecieve("Number of elim rounds: ");
        System.out.println("—————————————————————————————————————————————————————————");

        // iterates
        iterator = new Iterator();
        iterator.iterate();

        // runs the tournament simulator
        prelimStatistics();
        elimStatistics();
    }

    /* Statistics Methods */
    public static void prelimStatistics() {
        // statistics
        int[] record = iterator.getRecord();

        // prints expected record statistics
        for (int i = 0; i < numPrelims + 1; i ++) {
            System.out.println("Expected teams " + i + "-" + (numPrelims - i) + ": " + record[i]);
        }
        System.out.println("—————————————————————————————————————————————————————————");
    }

    public static void elimStatistics() {
        // statistics
        int numElimsAdjusted = numElims;
        int numTeamsPositive = iterator.getNumTeamsPositive();
        int numTeamsBreaking;
        int numTeamsByed;
        int numTeamsDebating;
        int numTeamsScrewed;
        int numConcurrentElims;

        // adjusts elim break
        while (numTeamsPositive < (int) Math.pow(2, numElimsAdjusted - 1)) {
            numElimsAdjusted--;
        }
        while (numTeamsPositive > (int) Math.pow(2, numElimsAdjusted + 1)) {
            numElimsAdjusted++;
        }

        // calculates elim statistics
        numTeamsBreaking = Math.min(numTeamsPositive, (int) Math.pow(2, numElimsAdjusted));
        numTeamsByed = (int) (Math.pow(2, numElimsAdjusted)) - numTeamsBreaking;
        numTeamsDebating = numTeamsBreaking - numTeamsByed;
        numTeamsScrewed = numTeamsPositive - numTeamsBreaking;
        numConcurrentElims = (numTeamsBreaking - numTeamsByed) / 2;

        // prints expected elim size
        if (numElimsAdjusted != numElims) {
            // yes change --> tells the user
            System.out.println("The tournament can not break to " + elimName(numElims) + ".");
            System.out.println("Reason: Number of teams debating is " + elimError(numElimsAdjusted) + ".");
            System.out.println("Number of elims adjusted accordingly.");
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println("The tournament must break to a " + elimSize(numTeamsByed)
                    + " " + elimName(numElimsAdjusted) + ".");
        }
        else {
            // no change --> does not tell the user
            System.out.println("The tournament will break to a " + elimSize(numTeamsByed)
                    + " " + elimName(numElimsAdjusted) + ".");
        }

        // prints expected elim statistics
        System.out.println("Expected teams positive: " + numTeamsPositive);
        System.out.println("Expected teams in elims: " + numTeamsBreaking);
        System.out.println("Expected teams byed: " + numTeamsByed);
        System.out.println("Expected teams debating: " + numTeamsDebating);
        System.out.println("Expected teams screwed: " + numTeamsScrewed);
        System.out.println("Expected number of elim debates: " + numConcurrentElims);
        System.out.println("—————————————————————————————————————————————————————————");
        elimByeStatistics(numTeamsByed);
        elimScrewStatistics(numTeamsScrewed);
    }

    public static void elimByeStatistics(int inputNumTeamsByed) {
        // checks if there are any byes
        if (inputNumTeamsByed > 0) {
            int[] totalRecord = iterator.getRecord();
            int[] byeRecord = new int[numPrelims + 1];
            int currBracket = numPrelims;

            // loops through every bye
            for (int i = 0; i < inputNumTeamsByed; i++) {
                // if team found in totalRecord --> move it to byeRecord
                if (totalRecord[currBracket] > 0) {
                    byeRecord[currBracket]++;
                    totalRecord[currBracket]--;
                }
                // if no teams left
                else {
                    // if finished already
                    if (currBracket != 0) {
                        currBracket--;
                        byeRecord[currBracket]++;
                        totalRecord[currBracket]--;
                    }
                }
            }
            // loops through every bracket
            for (int i = 0; i < numPrelims + 1; i++) {
                // if team in that bracket byed --> print them
                if (byeRecord[i] > 0) {
                    System.out.println("Expected teams " + i + "-" + (numPrelims - i) + " that bye: " + byeRecord[i]);
                }
            }
            System.out.println("—————————————————————————————————————————————————————————");
        }
    }

    public static void elimScrewStatistics(int inputNumTeamsScrewed) {
        // checks if there are any screws
        if (inputNumTeamsScrewed > 0) {
            int[] totalRecord = iterator.getRecord();
            int[] screwRecord = new int[numPrelims + 1];
            int currBracket = (numPrelims / 2) + 1;

            // loops through every screw
            for (int i = 0; i < inputNumTeamsScrewed; i++) {
                // if team found in totalRecord --> move it to screwRecord
                if (totalRecord[currBracket] > 0) {
                    screwRecord[currBracket]++;
                    totalRecord[currBracket]--;
                }
                // if no teams left
                else {
                    // if finished already
                    if (currBracket != numPrelims) {
                        currBracket++;
                        screwRecord[currBracket]++;
                        totalRecord[currBracket]--;
                    }
                }
            }

            // loops through every bracket
            for (int i = 0; i < numPrelims + 1; i++) {
                // if team in that bracket screwed --> print them
                if (screwRecord[i] > 0) {
                    System.out.println("Expected teams " + i + "-" + (numPrelims - i) + " that are screwed: " + screwRecord[i]);
                }
            }
            System.out.println("—————————————————————————————————————————————————————————");
        }
    }

    /* Input Methods */
    public static int inputRecieve(String outputQuestion) {
        Scanner inputScanner = new Scanner(System.in);
        String inputText = "";
        int inputValue = 0;

        // loops until the input is valid
        while (inputInvalid(inputText)) {
            System.out.print(outputQuestion);
            inputText = inputScanner.nextLine();

            // checks if the input is still invalid
            if (inputInvalid(inputText)) {
                // invalid --> asks for new input
                System.out.println("Input invalid. Please reenter.");
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            }
            else {
                // valid --> assigns input
                inputValue = Integer.parseInt(inputText);
            }
        }
        return inputValue;
    }

    public static boolean inputInvalid(String inputText) {
        int inputValue;
        // checks if input is empty
        if (inputText == null) {
            return true;
        }

        // checks if input is not an integer
        try {
            inputValue = Integer.parseInt(inputText);
        }
        catch (NumberFormatException nfe) {
            return true;
        }

        // checks if input is positive
        return inputValue <= 0;
    }

    /* Name Methods */
    public static String elimName(int inputNumElims) {
        // returns the name for the given elimination round
        return switch (inputNumElims) {
            case 1 -> "Finals";
            case 2 -> "Semifinals";
            case 3 -> "Quarterfinals";
            case 4 -> "Octofinals";
            case 5 -> "Doubleoctofinals";
            case 6 -> "Tripleoctofinals";
            case 7 -> "Quarteroctofinals";
            default -> ("the requested size");
        };
    }

    public static String elimSize(int inputNumTeamsByed) {
        // returns the size of the first elimination round
        if (inputNumTeamsByed == 0) {
            return "full";
        }
        else {
            return "partial";
        }
    }

    public static String elimError(int inputNumElimsAdjusted) {
        // returns the name for the given elimination round
        if (inputNumElimsAdjusted == numElims) {
            return "accurate";
        }
        else if (inputNumElimsAdjusted > numElims) {
            return "too large";
        }
        else {
            return "too small";
        }
    }
}