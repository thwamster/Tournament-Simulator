public class Team {
    /* Instance Variables */
    private final int skill;
    private int numWins;
    private int numLosses;
    private boolean paired;
    private int sideLock;

    /* Constructor */
    public Team(int inputSkill) {
        // initializes characteristics
        this.skill = inputSkill;

        // initializes record
        this.numWins = 0;
        this.numLosses = 0;
    }

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
    public void setPaired(boolean value) {
        this.paired = value;
    }
    public void setSideLock(int value) {
        this.sideLock = value;
    }

    // Get Methods
    public int getSkill() {
        return this.skill;
    }
    public int getNumWins() {
        return this.numWins;
    }
    public int getNumLosses() {
        return this.numLosses;
    }
    public boolean getPaired() {
        return this.paired;
    }
    public int getSideLock() {
        return this.sideLock;
    }

    /* Other Methods */
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