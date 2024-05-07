import java.util.Scanner;
import java.util.Random;

public class Filter {
    /* Variables */
    private final static Scanner inputScanner = new Scanner(System.in);

    public static boolean invalidWholeNum(String inputText) {
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

    public static boolean invalidDouble(String inputText) {
        double inputValue;

        // checks if input is empty
        if (inputText == null) {
            return true;
        }

        // checks if input is not an integer
        try {
            inputValue = Double.parseDouble(inputText);
        }
        catch (NumberFormatException nfe) {
            return true;
        }

        return false;
    }

    public static boolean invalidCode(String inputText) {
        inputText = inputText.replaceAll("[^A-Za-z]", "");

        // checks if input is invalid
        return inputText.length() < 3;
    }

    public static boolean invalidEntry(String inputText) {
        inputText = inputText.replaceAll("[^A-Za-z&-]", "");

        if (inputText.equals("Names TBA")) {
            return false;
        }

        // checks if input is invalid
        return !inputText.contains("&") || inputText.length() < 5 ||
                inputText.indexOf("&") + 2 > inputText.length() - 1;
    }

    /* Filter Methods */
    public static int filterWholeNum(String outputQuestion) {
        String inputText = "";
        int inputValue = 0;

        // loops until the input is valid
        while (invalidWholeNum(inputText)) {
            System.out.print(outputQuestion);
            inputText = inputScanner.nextLine();

            // checks if the input is still invalid
            if (invalidWholeNum(inputText)) {

                // invalid --> asks for new input
                System.out.println("Input invalid. Please reenter.");
            }
            else {

                // valid --> assigns input
                inputValue = Integer.parseInt(inputText);
            }
        }
        return inputValue;
    }

    public static double[] filterDouble(String outputQuestion) {
        double[] inputValues = new double[Main.numTeams];
        String inputText = "";
        boolean exitTriggered = false;

        System.out.println(outputQuestion);

        // assigns every input to the array
        exit:
        for (int i = 0; i < Main.numTeams; i++) {

            // loops until the input is valid
            while (invalidDouble(inputText)) {
                inputText = inputScanner.nextLine();

                // checks if the exit condition was triggered
                if (i == 0 && inputText.equals("n/a")) {

                    // triggers the exit condition --> breaks
                    exitTriggered = true;
                    break exit;
                }
                // checks if the input is still invalid
                else if (invalidDouble(inputText)) {

                    // invalid --> asks for new input
                    System.out.println("Input " + (i + 1) + " invalid. Please reenter.");
                }
                else {

                    // valid --> assigns input
                    inputValues[i] = Double.parseDouble(inputText);
                }
            }

            // resets the input text
            inputText = "";
        }

        // loops if exit was triggered
        if (exitTriggered) {
            Random random = new Random();
            for (int i = 0; i < Main.numTeams; i++) {
                inputValues[i] = random.nextGaussian();
            }
        }

        return inputValues;
    }

    public static String[] filterCode(String outputQuestion) {
        String[] inputCodes = new String[Main.numTeams];
        String inputText = "";
        boolean exitTriggered = false;

        System.out.println(outputQuestion);

        // assigns every input to the array
        exit:
        for (int i = 0; i < Main.numTeams; i++) {

            // loops until the input is valid
            while (invalidCode(inputText)) {
                inputText = inputScanner.nextLine();

                // checks if the exit condition was triggered
                if (i == 0 && inputText.equals("n/a")) {

                    // triggers the exit condition --> breaks
                    exitTriggered = true;
                    break exit;
                }
                // checks if the input is still invalid
                else if (invalidCode(inputText)) {

                    // invalid --> asks for new input
                    System.out.println("Input " + (i + 1) + " invalid. Please reenter.");
                }
                else {

                    // valid --> assigns input
                    inputCodes[i] = Entry.parseCode(inputText);
                }
            }

            // resets the input text
            inputText = "";
        }

        // loops if exit was triggered
        if (exitTriggered) {
            for (int i = 0; i < Main.numTeams; i++) {
                inputCodes[i] = "School" + i;
            }
        }

        return inputCodes;
    }

    public static String[] filterEntry(String outputQuestion) {
        String[] inputEntries = new String[Main.numTeams];
        String inputText = "";
        boolean exitTriggered = false;

        System.out.println(outputQuestion);

        // assigns every input to the array
        exit:
        for (int i = 0; i < Main.numTeams; i++) {

            // loops until the input is valid
            while (invalidEntry(inputText)) {
                inputText = inputScanner.nextLine();

                // checks if the exit condition was triggered
                if (i == 0 && inputText.equals("n/a")) {

                    // triggers the exit condition --> breaks
                    exitTriggered = true;
                    break exit;
                }
                // checks if the input is still invalid
                else if (invalidEntry(inputText)) {

                    // invalid --> asks for new input
                    System.out.println("Input " + (i + 1) + " invalid. Please reenter.");
                }
                else {

                    // valid --> assigns input
                    if (inputText.equals("Names TBA")) {
                        inputEntries[i] = "Entry" + i;
                    }
                    else {
                        inputEntries[i] = Entry.parseEntry(inputText);
                    }

                }
            }

            // resets the input text
            inputText = "";
        }

        // loops if exit was triggered
        if (exitTriggered) {
            for (int i = 0; i < Main.numTeams; i++) {
                inputEntries[i] = "Entry" + i;
            }
        }

        return inputEntries;
    }
}