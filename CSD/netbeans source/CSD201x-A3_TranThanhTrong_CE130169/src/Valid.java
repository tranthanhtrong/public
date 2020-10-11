
import java.util.Scanner;
/**
 * Class: use for validate the input information.
 * @author tranthanhtrong
 */
public class Valid {

    private final static Scanner in = new Scanner(System.in);

    /**
     * Method: input the string from the user, see if it is valid
     * Input: from user
     * Output: return the valid input
     * @return string
     */
    public static String checkInputString() {
        while (true) {
            String result = in.nextLine().trim();
            if (result.isEmpty()) {
                System.out.println("Empty input!");
                System.out.print("Please input again: ");
            } else {
                return result;
            }
        }
    }


    /**
     * Method: input the choice from the user, see if it is valid
     * Input: from user, and with @param
     * Output: return the valid input
     * @param MIN_value
     * @param MAX_value
     * @return int
     */
    public static int inputChoiceMintoMax(int MIN_value, int MAX_value) {
        while (true) {
            try {
                int result = Integer.parseInt(in.nextLine().trim());
                if (result < MIN_value || result > MAX_value) {
                    throw new NumberFormatException();

                }
                return result;
            } catch (NumberFormatException e) {
                System.out.println("Please input number in rage [" + MIN_value + ", " + MAX_value + "]");
                System.out.print("Input again: ");
            }
        }
    }

    /**
     * Method: input the double from the user, see if it is valid and greater than 0
     * Input: from user
     * Output: return the valid input double
     * @return int
     */
    public static double checkInputDoubleGreaterThanZero() {
        while (true) {
            try {
                double inputDouble = Double.parseDouble(in.nextLine());
                if (inputDouble < 0) {
                    throw new NumberFormatException();
                } else {
                    return inputDouble;
                }
            } catch (NumberFormatException e) {
                System.out.println("Must be input double greater than or equal to 0!");
                System.out.print("Enter again: ");
            }
        }
    }

    /**
     * Method: input the int from the user, see if it is valid, and greater equal to 0
     * Input: from user
     * Output: return the valid input int
     * @return int
     */
    public static int checkInputIntGreaterthanOrEqualtoZero() {
        while (true) {
            try {
                int inputInt = Integer.parseInt(in.nextLine());
                if (inputInt < 0) {
                    throw new NumberFormatException();
                } else {
                    return inputInt;
                }
            } catch (NumberFormatException e) {
                System.out.println("Must be input integer greater or equal to 0!");
                System.out.print("Enter again: ");
            }
        }
    }
}
