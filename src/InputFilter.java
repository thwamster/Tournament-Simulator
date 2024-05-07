import java.util.Random;
import java.util.Scanner;

public class InputFilter {
    /* Variables */
    private final static Scanner inputScanner = new Scanner(System.in);

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

    public static double[] filterSkill(String outputQuestion, int inputNum, int directory) {
        double[] inputSkills = new double[inputNum];
        String inputText = "";
        boolean exitTriggered = false;

        System.out.println(outputQuestion);

        // assigns every input to the array
        exit:
        for (int i = 0; i < inputNum; i++) {

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
                    inputSkills[i] = Double.parseDouble(inputText);
                }
            }

            // resets the input text
            inputText = "";
        }

        // loops if exit was triggered
        if (exitTriggered) {

            // if statistic calculator
            if (directory == 1 || directory == 2) {

                // randomize them
                Random random = new Random();
                for (int i = 0; i < inputNum; i++) {
                    inputSkills[i] = random.nextGaussian();
                }
            }
            // else if elimination bracket
            else if (directory == 3) {

                // put in seed order
                for (int i = 0, k = Tournament.numTeamsBreaking; i < inputNum; i++, k--) {
                    inputSkills[i] = k;
                }
            }
        }

        return inputSkills;
    }

    public static String[] filterCode(String outputQuestion, int inputNum) {
        String[] inputCodes = new String[inputNum];
        String inputText = "";
        boolean exitTriggered = false;

        System.out.println(outputQuestion);

        // assigns every input to the array
        exit:
        for (int i = 0; i < inputNum; i++) {

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
                    inputCodes[i] = InputParse.parseCode(inputText);
                }
            }

            // resets the input text
            inputText = "";
        }

        // loops if exit was triggered
        if (exitTriggered) {
            for (int i = 0; i < inputNum; i++) {
                inputCodes[i] = "School" + i;
            }
        }

        return inputCodes;
    }

    public static String[] filterEntry(String outputQuestion, int inputNum) {
        String[] inputEntries = new String[inputNum];
        String inputText = "";
        boolean exitTriggered = false;

        System.out.println(outputQuestion);

        // assigns every input to the array
        exit:
        for (int i = 0; i < inputNum; i++) {

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
                        inputEntries[i] = InputParse.parseEntry(inputText);
                    }

                }
            }

            // resets the input text
            inputText = "";
        }

        // loops if exit was triggered
        if (exitTriggered) {
            for (int i = 0; i < inputNum; i++) {
                inputEntries[i] = "Entry" + i;
            }
        }

        return inputEntries;
    }

    public static int filterDirectory(String outputQuestion) {
        String inputText = "";
        int inputValue = 0;

        // loops until the input is valid
        while (invalidDirectory(inputText)) {
            System.out.print(outputQuestion);
            inputText = inputScanner.nextLine();

            // checks if the input is still invalid
            if (invalidDirectory(inputText)) {

                // invalid --> asks for new input
                System.out.println("Input invalid. Please reenter. Type '0' for help.");
            }
            else {

                // valid --> assigns input
                inputValue = Integer.parseInt(inputText);
            }
        }
        return inputValue;
    }

    /* Validation Methods */
    private static boolean invalidWholeNum(String inputText) {
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

    private static boolean invalidDouble(String inputText) {
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

    private static boolean invalidCode(String inputText) {
        inputText = inputText.replaceAll("[^A-Za-z]", "");

        // checks if input is invalid
        return inputText.length() < 3;
    }

    private static boolean invalidEntry(String inputText) {
        inputText = inputText.replaceAll("[^A-Za-z&-]", "");

        if (inputText.equals("Names TBA")) {
            return false;
        }

        // checks if input is invalid
        return !inputText.contains("&") || inputText.length() < 5 ||
                inputText.indexOf("&") + 2 > inputText.length() - 1;
    }

    private static boolean invalidDirectory(String inputText) {
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

        // checks if input is a valid directory
        return inputValue < -1 || inputValue > 5;
    }
}