public class ElimBracket {
    private static int numTeamsByed;
    private static int numElimsAdjusted;
    private static Team[][] elimBracket;

    /* Calculate Method */
    public static void calculate() {
        // loops until the size is accurate
        numElimsAdjusted = Tournament.numElims;
        while (Tournament.numTeamsBreaking < Math.pow(2, numElimsAdjusted - 1)) {
            // given size is too large
            numElimsAdjusted--;
        }
        while (Tournament.numTeamsBreaking > Math.pow(2, numElimsAdjusted + 1)) {
            // given size is too small
            numElimsAdjusted++;
        }

        // calculates number of byes & people debating
        numTeamsByed = (int) (Math.pow(2, numElimsAdjusted) - Tournament.numTeamsBreaking);

        // initializes elim bracket
        elimBracket = new Team[numElimsAdjusted + 1][(int) Math.pow(2, numElimsAdjusted)];
        fillSeeds();
        fillBracket();
    }

    /* Bracket Methods */
    private static void fillSeeds() {
        // loops through every seed
        for (int i = 0, c = 0; i < Tournament.numTeamsBreaking; i++) {
            // assigns the seed
            elimBracket[0][c] = new Team(Tournament.teamList[i], Tournament.schoolList[i],
                                          Tournament.entryList[i], Tournament.skillList[i]);

            // adjusts the current location
            for (int j = 0; j < numElimsAdjusted; j++) {

                // shifts the bits
                if ((i + 1) % Math.pow(2, j + 1) != 0) {
                    c ^= 1 << (numElimsAdjusted - 1 - j);
                }
            }
        }
    }

    private static void fillBracket() {
        // loops through every elim level
        for (int i = 0; i < numElimsAdjusted; i++) {
            // loops through every individual round
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
                } else if (team1 == null && team2 != null) {
                    // team 1 nonexistent --> BYE for team 2
                    elimBracket[i + 1][j] = team2;
                } else if (team1 != null) {
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
    }

    /* Print Methods */
    public static void printElims() {
        // prints
        printBreak();
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        printBracket();
        System.out.println("—————————————————————————————————————————————————————————");
    }

    private static void printBreak() {
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

    private static void printBracket() {
        int maxTeamNameLength = 0;
        StringBuilder currWhiteSpace;
        StringBuilder emptyWhiteSpace;

        // finds the maximum length of all team names
        for (String tempTeam : Tournament.teamList) {
            if (maxTeamNameLength < tempTeam.length()) {
                maxTeamNameLength = tempTeam.length();
            }
        }
        for (int i = numElimsAdjusted; i >= 0; i--) {
            if (maxTeamNameLength < ElimName.elimName(i).length() + 1) {
                maxTeamNameLength = ElimName.elimName(i).length() + 1;
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
                if (j > ElimName.elimName(i).length() + 1) {
                    currWhiteSpace.append(" ");
                }
            }

            // prints the elim name
            System.out.print(ElimName.elimName(i) + ":" + currWhiteSpace);
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
