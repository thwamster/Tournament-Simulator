import java.util.Arrays;

public class Bracket {
    public static int numTeamsBreaking;
    public static int numTeamsByed;
    public static int numElimsAdjusted;
    public static int numElims;
    public static String[] teamList;
    public static String[] schoolList;
    public static String[] entryList;
    public static double[] skillList;
    public static Team[][] elimBracket;

    public static void bracket() {
        // receives global variables from input
        System.out.println("—————————————————————————————————————————————————————————");
        System.out.println("Tournament values:");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        numTeamsBreaking = Filter.filterWholeNum("Number of teams breaking: ");
        numElims = Filter.filterWholeNum("Number of elim rounds: ");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        schoolList = Filter.filterCode("List of team codes by seed: ", numTeamsBreaking);
        entryList = Filter.filterEntry("List of entry names: ", numTeamsBreaking);
        skillList = Filter.filterSkill("List of team elos: ", numTeamsBreaking);
        System.out.println("—————————————————————————————————————————————————————————");

        // creates the list of teams
        teamList = new String[numTeamsBreaking];
        for (int i = 0, k = numTeamsBreaking; i < numTeamsBreaking; i++, k--) {
            teamList[i] = schoolList[i] + entryList[i];
            skillList[i] = k;
        }

        // adjusts the elim size
        numElimsAdjusted = numElims;
        while (numTeamsBreaking < Math.pow(2, numElimsAdjusted - 1)) {
            numElimsAdjusted--;
        }
        while (numTeamsBreaking > Math.pow(2, numElimsAdjusted + 1)) {
            numElimsAdjusted++;
        }

        // calculates number of byes & people debating
        numTeamsByed = (int) (Math.pow(2, numElimsAdjusted) - numTeamsBreaking);

        // initializes elim bracket
        elimBracket = new Team[numElimsAdjusted + 1][(int) Math.pow(2, numElimsAdjusted)];

        // declares location variables
        int numLocation;
        boolean[] location = new boolean[numElimsAdjusted];
        Arrays.fill(location, false);

        // places every seed
        for (int i = 0; i < numTeamsBreaking; i++) {

            // checks if it is not the first one
            if (i != 0) {
                // calculates location for all but last
                for (int j = 0; j < numElimsAdjusted - 1; j++) {

                    // conditions are met
                    if (i % (Math.pow(2, j + 1)) != 0) {

                        // flips the location
                        location[j] = !location[j];
                    }
                }

                // flips the last location
                location[location.length - 1] = !location[location.length - 1];
            }

            // calculates the numerical location
            numLocation = 0;
            for (int j = 0, k = numElimsAdjusted - 1; j < numElimsAdjusted; j++, k--) {

                // sums location if value is true
                if (location[j]) {
                    numLocation += (int) Math.pow(2, k);
                }
            }

            // adds it to the array
            elimBracket[0][numLocation] = new Team(teamList[i], schoolList[i], entryList[i], skillList[i]);
        }


        // fills out the rest of the bracket
        for (int i = 0; i < numElimsAdjusted; i++) {
            for (int j = 0; j < Math.pow(2, numElimsAdjusted); j += (int) Math.pow(2, i + 1)) {
                Team team1;
                Team team2;

                // assigns teams
                team1 = elimBracket[i][j];
                team2 = elimBracket[i][j + (int) Math.pow(2, i)];

                // checks if null values
                if (team1 != null && team2 == null) {
                    // team 2 nonexistent --> BYE for team 1
                    elimBracket[i + 1][j] = team1;
                }
                else if (team1 == null && team2 != null) {
                    // team 1 nonexistent --> BYE for team 2
                    elimBracket[i + 1][j] = team2;
                }
                else if (team1 != null){
                    // results
                    if (team1.getSkill() > team2.getSkill()) {
                        // team 1 --> WIN, team 2 --> LOSS
                        elimBracket[i + 1][j] = team1;
                    } else if (team1.getSkill() < team2.getSkill()) {
                        // team 1 --> LOSS, team 2 --> WIN
                        elimBracket[i + 1][j] = team2;
                    } else {
                        // random chance who wins
                        boolean coin = Math.random() >= 0.5;
                        if (coin) {
                            // team 1 --> WIN, team 2 --> LOSS
                            elimBracket[i + 1][j] = team1;
                        } else {
                            // team 1 --> LOSS, team 2 --> WIN
                            elimBracket[i + 1][j] = team2;
                        }
                    }
                }
            }
        }

        // prints
        print();
    }

    public static void print() {
        // prints expected elim size
        if (numElimsAdjusted != numElims) {
            // yes change --> tells the user
            System.out.println("The tournament can not break to " + Name.elimName(numElims) + ".");
            System.out.println("Reason: Number of teams debating is " + Name.elimError(numElims, numElimsAdjusted) + ".");
            System.out.println("The tournament must break to a " + Name.elimSize(numTeamsByed)
                    + " " + Name.elimName(numElimsAdjusted) + ".");
        }
        else {
            // no change --> does not tell the user
            System.out.println("The tournament will break to a " + Name.elimSize(numTeamsByed)
                    + " " + Name.elimName(numElimsAdjusted) + ".");
        }
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        printBracket();
        System.out.println("—————————————————————————————————————————————————————————");
    }

    public static void printBracket() {
        int maxTeamNameLength = 0;
        StringBuilder currWhiteSpace;
        StringBuilder emptyWhiteSpace;

        // finds the maximum length of all team names
        for (String tempTeam : teamList) {
            if (maxTeamNameLength < tempTeam.length()) {
                maxTeamNameLength = tempTeam.length();
            }
        }
        for (int i = numElimsAdjusted; i >= 0; i--) {
            if (maxTeamNameLength < Name.elimName(i).length() + 1) {
                maxTeamNameLength = Name.elimName(i).length() + 1;
            }
        }

        // initializes an empty white space
        emptyWhiteSpace = new StringBuilder("BYE");
        while (emptyWhiteSpace.length() < maxTeamNameLength) {
            emptyWhiteSpace.append(" ");
        }

        // prints every elim round
        for (int i = numElimsAdjusted; i >= 0; i--) {
            // prints a tab
            if (i != numElimsAdjusted) {
                System.out.print("\t");
            }

            // calculates whitespace
            currWhiteSpace = new StringBuilder(" ");
            for (int j = 0; j <= maxTeamNameLength; j++) {
                if (j > Name.elimName(i).length() + 1) {
                    currWhiteSpace.append(" ");
                }
            }

            // prints the elim name
            System.out.print(Name.elimName(i) + ":" + currWhiteSpace);
        }

        // prints a line
        System.out.println();

        // loops through every team
        for (int j = 0; j < elimBracket[0].length; j++) {

            // loops through every elim round
            for (int i = 0; i < numElimsAdjusted + 1; i++) {

                // checks if value is not null
                if (elimBracket[i][j] != null && elimBracket[i][j].toString() != null ) {
                    // prints a tab
                    if (i != 0) {
                        System.out.print("\t");
                    }

                    // calculates whitespace
                    currWhiteSpace = new StringBuilder(" ");
                    for (int k = 0; k <= maxTeamNameLength; k++) {
                        if (k > elimBracket[i][j].toString().length()) {
                            currWhiteSpace.append(" ");
                        }
                    }

                    // prints
                    System.out.print(elimBracket[i][j] + currWhiteSpace.toString());
                }
                else if (i == 0) {
                    System.out.print(emptyWhiteSpace);
                }
            }

            // prints a line
            System.out.println();
        }
    }
}
