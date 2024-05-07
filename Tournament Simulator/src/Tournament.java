public class Tournament {
    /* Program Characteristics */
    public static final int numIterations = 1000;

    /* Tournament Characteristics */
    public static int numTeams = -1;
    public static int numTeamsBreaking = -1;
    public static int numPrelims = -1;
    public static int numElims = -1;
    public static String[] teamList = new String[0];
    public static String[] schoolList = new String[0];
    public static String[] entryList = new String[0];
    public static double[] skillList = new double[0];

    /* Generator Methods */
    private static boolean firstLine = false;
    private static boolean secondLine = false;

    /* Generator Methods */
    public static void resetTournament() {
        // resets numerical values to -1
        numTeams = -1;
        numTeamsBreaking = -1;
        numPrelims = -1;
        numElims = -1;
        // resets arrays to size 0
        teamList = new String[0];
        schoolList = new String[0];
        entryList = new String[0];
        skillList = new double[0];
    }

    public static void generateNewTournament(int directory) {
        // resets the tournament
        resetTournament();

        // asks for and receives values
        if (directory == 1 || directory == 2 || directory == 3 || directory == 4) {
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        }
        if (directory == 1 || directory == 2 || directory == 4) {
            numTeams = InputFilter.filterWholeNum("Number of teams debating: ");
        }
        if (directory == 3) {
            numTeamsBreaking = InputFilter.filterWholeNum("Number of teams breaking: ");
        }
        if (directory == 1 || directory == 2) {
            numPrelims = InputFilter.filterWholeNum("Number of prelim rounds: ");
        }
        if (directory == 1 || directory == 2 || directory == 3) {
            numElims = InputFilter.filterWholeNum("Number of elim rounds: ");
        }
        if (directory == 1 || directory == 2 || directory == 3 || directory == 4) {
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        }
        if (directory == 1 || directory == 2 || directory == 4) {
            schoolList = InputFilter.filterCode("List of team codes: ", numTeams);
            entryList = InputFilter.filterEntry("List of entry names: ", numTeams);
        }
        if (directory == 1 || directory == 2) {
            skillList = InputFilter.filterSkill("List of team elos: ", numTeams, directory);
        }
        if (directory == 3) {
            schoolList = InputFilter.filterCode("List of team codes: ", numTeamsBreaking);
            entryList = InputFilter.filterEntry("List of entry names: ", numTeamsBreaking);
            skillList = InputFilter.filterSkill("List of team elos: ", numTeamsBreaking, directory);
        }

        // initializes teams
        if (directory == 1 || directory == 2 || directory == 4) {
            teamList = new String[numTeams];
            for (int i = 0; i < numTeams; i++) {
                teamList[i] = schoolList[i] + entryList[i];
            }
        }
        if (directory == 3) {
            teamList = new String[numTeamsBreaking];
            for (int i = 0; i < numTeamsBreaking; i++) {
                teamList[i] = schoolList[i] + entryList[i];
            }
        }
    }

    public static void regenerateTournament(int directory) {
        // reset line printer
        checkLine(0);

        // asks for and receives values
        switch (directory) {
            case 1, 2 -> {
                if (numTeams == -1) {
                    checkLine(1);
                    numTeams = InputFilter.filterWholeNum("Number of teams debating: ");
                }
                if (numPrelims == -1) {
                    checkLine(1);
                    numPrelims = InputFilter.filterWholeNum("Number of prelim rounds: ");
                }
                if (schoolList.length != numTeams || entryList.length != numTeams) {
                    checkLine(2);
                    schoolList = InputFilter.filterCode("List of team codes: ", numTeams);
                    entryList = InputFilter.filterEntry("List of entry names: ", numTeams);
                }
                if (skillList.length != numTeams) {
                    checkLine(2);
                    skillList = InputFilter.filterSkill("List of team elos: ", numTeams, directory);
                }
            }
            case 3 -> {
                if (numTeamsBreaking == -1) {
                    checkLine(1);
                    numTeamsBreaking = InputFilter.filterWholeNum("Number of teams breaking: ");
                }
                if (numElims == -1) {
                    checkLine(1);
                    numElims = InputFilter.filterWholeNum("Number of elim rounds: ");
                }
                if (schoolList.length != numTeamsBreaking || entryList.length != numTeamsBreaking ||
                        skillList.length != numTeamsBreaking) {
                    checkLine(2);
                    schoolList = InputFilter.filterCode("List of team codes: ", numTeamsBreaking);
                    entryList = InputFilter.filterEntry("List of entry names: ", numTeamsBreaking);
                    skillList = InputFilter.filterSkill("List of team elos: ", numTeamsBreaking, directory);
                }
            }
            case 4 -> {
                if (numTeams == -1) {
                    checkLine(1);
                    numTeams = InputFilter.filterWholeNum("Number of teams debating: ");
                }
                if (schoolList.length != numTeams || entryList.length != numTeams) {
                    checkLine(2);
                    schoolList = InputFilter.filterCode("List of team codes: ", numTeams);
                    entryList = InputFilter.filterEntry("List of entry names: ", numTeams);
                }
            }
            default -> {
            }
        }

        // initializes teams
        if (directory == 1 || directory == 2 || directory == 4) {
            teamList = new String[numTeams];
            for (int i = 0; i < numTeams; i++) {
                teamList[i] = schoolList[i] + entryList[i];
            }
        }
        if (directory == 3) {
            teamList = new String[numTeamsBreaking];
            for (int i = 0; i < numTeamsBreaking; i++) {
                teamList[i] = schoolList[i] + entryList[i];
            }
        }
    }

    /* Check Methods */
    public static void checkLine(int value) {
        // if printed first line yet
        if (value == 1 && !firstLine) {
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            firstLine = true;
        }
        // else if printed second line yet
        else if (value == 2 && !secondLine) {
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            secondLine = true;
        }
        // resets both lines
        else if (value == 0) {
            firstLine = false;
            secondLine = false;
        }
    }

    public static boolean checkDirectory(int directory) {
        if (directory == 1 || directory == 2) {
            // numTeams, numPrelims, schoolList, entryList, skillList
            return schoolList.length != numTeams || entryList.length != numTeams ||
                    skillList.length != numTeams || numPrelims == -1 || numElims == -1;
        }
        else if (directory == 3) {
            // numElims, schoolList, entryList
            return schoolList.length != numTeamsBreaking || entryList.length != numTeamsBreaking ||
                    skillList.length != numTeamsBreaking || numElims == -1;
        }
        else if (directory == 4) {
            // numTeams, schoolList, entryList
            return schoolList.length != numTeams || entryList.length != numTeams;
        }
        else {
            // invalid directory
            return false;
        }
    }
}