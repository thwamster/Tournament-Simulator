public class Main {
    /* Program Characteristics */
    private static boolean existing;
    private static int directory;

    /* Main Method */
    public static void main(String[] args) {
        // prints table of contents
        System.out.println("—————————————————————————————————————————————————————————");
        System.out.println("Tournament Simulator");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("Starting guide:");
        System.out.println("0 --> Help Menu");
        System.out.println("1 --> Preliminary Round Statistics");
        System.out.println("2 --> Elimination Round Statistics");
        System.out.println("3 --> Elimination Bracket Generator");
        System.out.println("4 --> OpenCaselist Wiki Generator");
        System.out.println("5 --> Reset Tournament");
        System.out.println("-1 -> Terminate Program");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

        // resets the tournament
        existing = false;

        // runs the program until terminate directory is entered
        int program;
        do {
            program = directory();
        }
        while (program == 0);
    }

    /* Program Methods */
    private static int directory() {
        // initializes directory
        directory = InputFilter.filterDirectory("Enter directory: ");

        // runs desired program
        switch (directory) {
            case 0 -> { // --> Help Menu
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                System.out.println("Help menu:");
                System.out.println("0 --> Regenerates this menu in its entirety.");
                System.out.println("1 --> Receives tournament values and calculates expected ");
                System.out.println("      statistics for all preliminary rounds. These stats ");
                System.out.println("      include records and pullups.");
                System.out.println("2 --> Receives tournament values and calculates expected ");
                System.out.println("      statistics for all elimination rounds. These stats ");
                System.out.println("      include the size of the first elimination round ");
                System.out.println("      and teams breaking, screwed, debating and byed.");
                System.out.println("3 --> Receives tournament values and calculates the full ");
                System.out.println("      elimination bracket and expected elimination size.");
                System.out.println("4 --> Receives team and entry names and will return ");
                System.out.println("      OpenCaselist links to all team wikis, by ");
                System.out.println("      automatically matching school aliases and debater ");
                System.out.println("      names by initials.");
                System.out.println("5 --> Resets the current tournament values previously ");
                System.out.println("      inputted and saved in the program. Run this ");
                System.out.println("      before entering new data. Automatically ran ");
                System.out.println("      when booting the program.");
                System.out.println("-1 -> Ends the current program immediately. Your data ");
                System.out.println("      is still viewable and copyable in the terminal.");
                System.out.println("Note: Type 'n/a' when prompted to input team data to ");
                System.out.println("      auto-fill team codes, entry names, and skills.");
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                return 0;
            }
            case 1 -> { // --> Preliminary Round Statistics
                generate();
                Statistics.calculate();
                Statistics.printPrelims();
                return 0;
            }
            case 2 -> { // --> Elimination Round Statistics
                generate();
                Statistics.calculate();
                Statistics.printElims();
                return 0;
            }
            case 3 -> { // --> Elimination Bracket Generator
                generate();
                ElimBracket.calculate();
                ElimBracket.printElims();
                return 0;
            }
            case 4 -> { // --> OpenCaselist Wiki Generator
                generate();
                OpenCaselist.printWikis();
                return 0;
            }
            case 5 -> { // --> Reset Tournament
                Tournament.resetTournament();
                existing = false;
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                System.out.println("Tournament has been reset.");
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                return 0;
            }
            default -> { // -> Terminate Program
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                System.out.println("Program terminated. Thank you for your participation.");
                System.out.println("—————————————————————————————————————————————————————————");
                return 1;
            }
        }
    }

    private static void generate() {
        System.out.println("—————————————————————————————————————————————————————————");

        // checks if there is a pre-existing tournament
        if (existing) {

            // if new inputs necessary
            if (Tournament.checkDirectory(directory)) {

                // regenerates the tournament
                System.out.println("Update tournament values:");
                Tournament.regenerateTournament(directory);
                System.out.println("—————————————————————————————————————————————————————————");
            }
        }
        // must generate new tournament
        else {

            // generates a new tournament
            System.out.println("New tournament values:");
            Tournament.generateNewTournament(directory);
            System.out.println("—————————————————————————————————————————————————————————");
            existing = true;
        }
    }
}