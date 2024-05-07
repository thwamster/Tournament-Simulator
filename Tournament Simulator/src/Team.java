public class Team {
    /* Instance Variables */
    private final String teamName;
    private final String school;
    private final String entry;
    private final double skill;
    private int numWins;
    private int numLosses;
    private boolean paired;
    private final String[] pairingHistory;
    private int sideLock;

    /* Constructor */
    public Team() {
        this.teamName = "";
        this.school = "";
        this.entry = "";
        this.skill = 0.0;
        this.numWins = 0;
        this.numLosses = 0;
        this.pairingHistory = new String[Main.numPrelims];
    }

    public Team(String inputName, String inputSchool, String inputEntry, double inputSkill) {
        this.teamName = inputName;
        this.school = inputSchool;
        this.entry = inputEntry;
        this.skill = inputSkill;
        this.numWins = 0;
        this.numLosses = 0;
        this.pairingHistory = new String[Main.numPrelims];
    }

    /* Get Methods */
    public String getTeamName() { return this.teamName; }
    public String getSchool() { return this.school; }
    public String getEntry() { return this.entry; }
    public double getSkill() { return this.skill; }
    public int getNumWins() { return this.numWins; }
    public int getNumLosses() { return this.numLosses; }
    public boolean getPaired() { return this.paired; }
    public int getSideLock() { return this.sideLock; }

    /* Set Methods */
    public void setResult(boolean value) {
        // checks if round won or lost
        if (value) {
            this.numWins++;
        }
        else {
            this.numLosses++;
        }
    }
    public void setPairingHistory(int numRound, String opponentName) {
        this.pairingHistory[numRound] = opponentName;
    }
    public void setPaired(boolean value) { this.paired = value; }
    public void setSideLock(int value) { this.sideLock = value; }

    /* Other Methods */
    public boolean hasHitBefore(String opponentName) {
        // loops through history and checks if team has hit opponent yet
        for (int i = 0; i < this.numWins + this.numLosses; i++) {
            if (this.pairingHistory[i] != null && this.pairingHistory[i].equals(opponentName)) {
                return true;
            }
        }
        return false;
    }

    public void flipSideLock() {
        // checks if AFF --> NEF
        if (this.sideLock == 1) {
            this.sideLock = -1;
        }
        // else if NEG --> AFF
        else if (this.sideLock == -1) {
            this.sideLock = 1;
        }
        // else BYE
        else {
            // flips coin --> AFF/NEG
            if (Math.random() <= 0.5) {
                this.sideLock = 1;
            }
            else {
                this.sideLock = -1;
            }
        }
    }
}