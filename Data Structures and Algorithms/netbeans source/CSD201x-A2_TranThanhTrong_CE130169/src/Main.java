import entity.Book;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;
import util.MyList;
import util.Node;
/*
 * Name: Tran Thanh Trong 
 * Student code: CE130169
 * Project 2
 * Subject CSD201x
 */
public class Main {

    private final static Scanner in = new Scanner(System.in);
    public static BookList m = new BookList();

    //main function for running the program
    public static void main(String[] args) throws IOException {

        //these option 7,8,9 come from Advanced Criteria
        while (true) {
            System.out.println("Book List");
            System.out.println("1. Input Book and add to the end");
            System.out.println("2. Display books");
            System.out.println("3. Search by code");
            System.out.println("4. Input Book and add to beginning");
            System.out.println("5. Add Book after position k");
            System.out.println("6. Delete Book at positon k");
            System.out.println("7. Sort book list(Ad)");//advanced criteria
            System.out.println("8. Load all books from text File(Ad)");//advanced criteria
            System.out.println("9. Export all books to the text File(Ad)");//advanced criteria
            System.out.println("0. Exit");
            System.out.print("Your choice: ");
            int choice = valid.inputChoiceMintoMax(0, 9);
            switch (choice) {
                case 1:
                    m.addLast(createNewBook(m.getBooks()));
                    break;
                case 2:
                    m.list();
                    break;
                case 3:
                    System.out.print("Enter book code: ");
                    m.search(valid.checkInputString());
                    break;
                case 4:
                    m.addFirst(createNewBook(m.getBooks()));
                    break;
                case 5:
                    System.out.println("Enter information of a Book");
                    Book newBook = createNewBook(m.getBooks());
                    int pos = valid.checkInputIntPos();
                    if (m.addAfter(newBook, pos)) {
                        System.out.println("A new book has been added after position " + pos);
                    }
                    break;
                case 6:
                    System.out.print("Enter position you want to delete : ");
                    if (m.deleteAt(valid.checkInputIntGreaterthanOrEqualtoZero())) {
                        if (m.getBooks().size() > 0) {
                            System.out.println("List after deleting ----------------");
                            m.list();
                        } else {
                            System.out.println("After deleting, your list has no book left.");
                        }
                    }
                    break;
                case 7:
                    sortMenu();
                    break;
                case 8:
                    loadFile();
                    break;
                case 9:
                    exportToFile();
                    break;
                default:
                    return;
            }
        }

    }

    //function to display the sorting menu and manage two method of sorting.
    public static void sortMenu() {
        if (m.getBooks().size() > 1) {
            System.out.println("====== Ascending Sorting ======");
            System.out.println("1. Sort by Price");
            System.out.println("2. Sort by BookCode");
            System.out.println("0. Go back to the main menu");
            System.out.println("===============================");
            System.out.print("Your choice: ");
            int c = valid.inputChoiceMintoMax(0, 2);
            switch (c) {
                case 1:
                    m.sortByPrice();
                    System.out.println("After sorting -------------------------");
                    m.list();
                    break;
                case 2:
                    m.sortByBC();
                    System.out.println("After sorting -------------------------");
                    m.list();
                    break;
                default:
                    break;
            }
        } else {
            System.out.println("Your list does not have more than 1 item to sort!");
        }
    }
    public static String fileName = new String();

    //function to load list from file text into the list on the program
    public static void loadFile() throws IOException {
        fileName = valid.inputString();
        String fileN = fileName.toLowerCase();
        if (!fileN.endsWith(".txt")) {
            fileN += ".txt";
        }
        try {
            FileReader fr = null;
            LineNumberReader lnr = null;
            String line;
            boolean checkSuccess = true;
            // create new reader
            fr = new FileReader(fileN);
            lnr = new LineNumberReader(fr);
            // read lines till the end of the stream

            while (((line = lnr.readLine())) != null) {
                String[] information = line.split("\t");
                String id = information[0];
                String title = information[1];
                int quanity = Integer.parseInt(information[2]);
                int lended = Integer.parseInt(information[3]);
                double price = Double.parseDouble(information[4]);
                if (checkSuccess) {
                    if (m.getBooks().size() > 0) {
                        System.out.print("Now, you had some books on list. \nDo you want to load a new list from the file ? (Y/N): ");
                        if (valid.checkInputYN()) {
                            m.setEmptyForList();
                            checkSuccess = false;
                        } else {
                            System.out.println("You choose not to load a new list.");
                            return;
                        }
                    } else {
                        m.setEmptyForList();
                        checkSuccess = false;
                    }

                }
                m.addLast(new Book(id, title, quanity, lended, price));
            }
            fr.close();
            lnr.close();
            System.out.println("Load file to the List successfully!");
        } catch (IOException e) {
            System.out.println("Load file unsucessfull, please try again later!");
        }

    }
    private static String FILENAME = new String();
    //function to export the current list to the file text, with input name or path
    public static void exportToFile() {
        if (m.getBooks().size() > 0) {
            BufferedWriter bw = null;
            FileWriter fw = null;
            FILENAME = valid.checkInputStringText();
            if (!FILENAME.endsWith(".txt")) {
                FILENAME += ".txt";
            }

            try {

                Node<Book> p = m.getBooks().getNode(0);
                fw = new FileWriter(FILENAME);
                bw = new BufferedWriter(fw);
                while (p != null) {
                    String content = p.info.getbCode() + "\t" + p.info.getTitle() + "\t" + p.info.getQuantity() + "\t" + p.info.getLended() + "\t" + p.info.getPrice() + "\n";
                    bw.write(content);
                    p = p.next;
                }
                System.out.println("Export the current list to " + FILENAME + " successfully!");

            } catch (IOException e) {
                System.out.println("Export unsuccessfully! Please try again later!");
            } finally {
                try {

                    if (bw != null) {
                        bw.close();
                    }

                    if (fw != null) {
                        fw.close();
                    }

                } catch (IOException ex) {
                    System.out.println("Export unsuccessfully! Please try again later!");
                }

            }
        } else {
            System.out.println("Sorry! Have no book to export to file");
        }
    }

    //function to create a new book.
    public static Book createNewBook(MyList ls) {
        //loop until user input not duplicate
        while (true) {
            System.out.print("Book code: ");
            String id = valid.checkInputString();
            if (valid.checkIdExist(ls, id)) {
                System.out.print("Book Title: ");
                String title = valid.checkInputString();
                System.out.print("Book Quanity: ");
                int quantity = valid.checkInputIntGreaterthanOrEqualtoZero();
                System.out.print("Book Lended: ");
                int lended = valid.checkInputIntGreaterthanOrEqualtoZero();
                System.out.print("Book Price: ");
                double price = valid.checkInputDoubleGreaterthanOrEqualtoZero();
                return new Book(id, title, quantity, lended, price);
            } else {
                System.out.println("Book code must be unique.");
            }

        }

    }

}
