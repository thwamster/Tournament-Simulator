public class OpenCaselist {
    /* Print Method */
    public static void printWikis() {
        System.out.println("Expected team wikis: ");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

        // loops through every team and prints the wiki link format of it
        for (int i = 0; i < Tournament.numTeams; i++) {
            System.out.println("https://opencaselist.com/hspolicy23/" + Tournament.schoolList[i] + "/" + Tournament.entryList[i]);
        }

        System.out.println("—————————————————————————————————————————————————————————");
    }
}