public class Main {

    /* Main Method */
    public static void main(String[] args) {
        System.out.println("—————————————————————————————————————————————————————————");
        System.out.println("Tournament Simulator");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("Starting guide:");
        System.out.println("0 --> Help Menu");
        System.out.println("1 --> Statistic Calculator");
        System.out.println("2 --> Elimination Bracket Generator");
        System.out.println("-1 -> Terminate Program");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        directory();
    }

    public static void directory() {
        int directory;

        directory = Filter.filterDirectory("Enter directory: ");
        if (directory == 0) {
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println("Help menu:");
            System.out.println(" This program will simulate a tournament in two scenarios.");
            System.out.println(" The 'Statistics Calculator' will calculate statistics");
            System.out.println(" for a tournament with the given starting values inputted.");
            System.out.println(" The 'Elimination Bracket Generator' will generate a ");
            System.out.println(" projected finished bracket for a tournament with the ");
            System.out.println(" given starting values and seeds inputted.");
            System.out.println(" The 'Terminate Program' command will end this program.");
            System.out.println(" The 'Help Menu' command will repeat this information box.");
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            directory();
        }
        else if (directory == 1) {
            Statistics.statistics();
            directory();
        }
        else if (directory == 2) {
            Bracket.bracket();
            directory();
        }
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("Program terminated. Thank you for your participation.");
        System.out.println("—————————————————————————————————————————————————————————");
        System.exit(-1);
    }
}