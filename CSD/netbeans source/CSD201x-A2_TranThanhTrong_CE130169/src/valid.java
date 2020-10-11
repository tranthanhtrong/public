
import entity.Book;
import java.util.Scanner;
import util.MyList;
import util.Node;

/*
 * Name: Tran Thanh Trong 
 * Student code: CE130169
 * Project 2
 * Subject CSD201x
 */
public class valid {

    private final static Scanner in = new Scanner(System.in);

    //check user input string
    public static String checkInputString() {
        //loop until user input correct
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

    //check user input string
    public static String checkInputStringText() {
        //loop until user input correct
        while (true) {
            System.out.print("Please set name of file .txt (do not need to type .txt): ");
            String result = in.nextLine().trim();
            if (result.isEmpty()) {
               System.out.println("Empty input!");
                System.out.print("Please input again: ");
            } else {
                return result;
            }
        }
    }

    //input choice valid from min to max
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

    //inputString user for input String and check the empty String
    public static String inputString() {
        while (true) {
            System.out.print("Input the file name you want to open: ");
            String inputS = in.nextLine().trim();
            if (inputS.isEmpty()) {//check if the string is empty and if so, loop 
                System.out.println("Empty String!");
                System.out.print("Please input again: ");
            } else {//if not, return the result
                return inputS;
            }
        }
    }

    //check id and exist
    public static boolean checkIdExist(MyList ls, String id) {
        int n = ls.size();
        for (int j = 0; j < n; j++) {
            Node<Book> pj = ls.getNode(j);
            if ((id == null ? pj.info.getbCode() == null : id.equals(pj.info.getbCode()))) {
                return false;
            }
        }
        return true;
    }
    //input number double check

    public static double checkInputDoubleGreaterthanOrEqualtoZero() {
        //loop until user input correct
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

    //input number int check
    public static int checkInputIntGreaterthanOrEqualtoZero() {
        //loop until user input correct
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
    
    //input number of position
    public static int checkInputIntPos() {
        while (true) {
            try {
                System.out.print("Enter adding position: ");
                int inputInt = Integer.parseInt(in.nextLine());
                if (inputInt < 0) {
                    throw new NumberFormatException();
                } else {
                    return inputInt;
                }
            } catch (NumberFormatException e) {
                System.out.println("Must be input integer greater or equal to 0!");
                System.out.print("Enter again! ");
            }
        }
    }
    
    //check user input yes/ no
    public static boolean checkInputYN() {
        while (true) {
            String result = checkInputString();
            if (result.equalsIgnoreCase("Y")) {//return true if user input y/Y
                return true;
            }
            if (result.equalsIgnoreCase("N")) {//return false if user input n/N
                return false;
            }
            System.out.println("Please input y/Y or n/N.");
            System.out.print("Enter again: ");
        }
    }

}
