public class Name {
    /* Name Methods */
    public static String elimName(int inputNumElims) {
        // returns the name for the given elimination round
        return switch (inputNumElims) {
            case 0 -> "Champions";
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

    public static String elimSize(double inputNumTeamsByed) {
        // returns the size of the first elimination round
        if (inputNumTeamsByed == 0) {
            return "full";
        }
        else {
            return "partial";
        }
    }

    public static String elimError(int inputNumElims, int inputNumElimsAdjusted) {
        // returns the name for the given elimination round
        if (inputNumElimsAdjusted == inputNumElims) {
            return "accurate";
        }
        else if (inputNumElimsAdjusted > inputNumElims) {
            return "too large";
        }
        else {
            return "too small";
        }
    }
}
