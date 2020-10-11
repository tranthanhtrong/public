
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * @author Tran Thanh Trong 
 * Student code: CE130169
 * Project 1
 * Subject CSD201x
 */
public class Main {

    //contains a list of MyFile
    private static MyFile[] files;
    private final static Scanner in = new Scanner(System.in);
    //declare some ANSI colors for using in the code
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED_BACKGROUND = "\u001B[41m";
    public static final String WHITE = "\u001B[37m";
    public static final String BLUE_BACKGROUND = "\u001B[44m";
    public static final String PURPLE_BACKGROUND = "\u001B[45m";
    public static final String CYAN_BACKGROUND = "\u001B[46m";

    //ctor
    public Main() {
        files = null;
    }

    //get information of all text files under given folder name
    public static void loadFiles(String folder) {
        List<MyFile> listFiles = new ArrayList<>(); //create a listFiles contains the list of MyFile
        loadFiles(folder, listFiles); //Call the function loadFile, with 2 parameters, the String and the list.
        files = listFiles.stream().toArray(MyFile[]::new);//then take it back to the variable named files
    }

    //load all the files with the extension .docx , .doc and .txt
    public static void loadFiles(String folder, List<MyFile> listFiles) {
        boolean check = false;//this check use to make loop re-input for the NullPointerException of a false input folder.
        do {
            if (check) {//first, does not need to do this part. Just do it, when the NullPointerException was thrown out.
                System.out.print(GREEN + "Enter a folder: " + RESET);
                folder = inputString();//call the funtion for input the file;
            }
            try {
                File file = new File(folder);
                if (file.isFile()) {//this check if user input the file, not the folder
                    if (file.getName().toLowerCase().endsWith((".txt")) || file.getName().toLowerCase().endsWith((".docx"))
                            || file.getName().toLowerCase().endsWith((".doc"))) { //this check for print out all of the .doc .docx and .txt file
                        MyFile myLoadFile = new MyFile();
                        myLoadFile.setName(file.getName());//set the name of the file object, then we can take it out of the list
                        myLoadFile.setSize(file.length());//set the size of the file object, so we can get them later
                        listFiles.add(myLoadFile);//add the object into the list
                        System.out.println(BLUE_BACKGROUND + GREEN + "You just load a file! Not a folder." + RESET);//display the file, user just load
                    } else {
                        System.out.println(RED + "You cannot load that file!" + RESET);//display unsupported file cannot be loaded
                    }
                } else {//this check if user input not a file, this just can be a folder.
                    File[] fileList = file.listFiles();
                    for (File fileR : fileList) {
                        if (fileR.isFile()) {
                            if (fileR.getName().toLowerCase().endsWith((".txt")) || fileR.getName().toLowerCase().endsWith((".docx"))
                                    || fileR.getName().toLowerCase().endsWith((".doc"))) {
                                MyFile myLoadFile = new MyFile();
                                myLoadFile.setName(fileR.getName());
                                myLoadFile.setSize(fileR.length());
                                myLoadFile.setFullPath(fileR.getAbsolutePath());
                                listFiles.add(myLoadFile);//add the object into the list
                            }
                        } else if (fileR.isDirectory()) {
                            loadFiles(fileR.getAbsolutePath(), listFiles);//recursive
                        }
                    }
                }
                check = false;
            } catch (NullPointerException e) {//catch the NullPointerException for the wrong input
                System.out.println(RED + "Folder does not exist!" + RESET);
                check = true;
            }
        } while (check);//loops over again if the folder doesn't exist.
    }

    //list information of all loaded files
    public static void list(MyFile[] files) {
        //list the files out if they are have more than 0, else print out the empty mention
        if (files != null && files.length > 0) {
            //output heading
            System.out.println(String.format("%-20s%-10s", "Name", "Size(in byte)"));
            for (MyFile f : files) {//loop through the list of files
                System.out.println(f);//print out in the format which has in the MyFile class
            }
        } else {
            //print out the mention empty list
            System.out.println("List of files is empty...");
        }
    }

    //sort the list of files ascending by size (use insertion sort), though sortField bySize or byName
    public static void insertionSort(SortField sf) {
        /*You should insert code for sorting here, you are going to sort the list of
        loaded files named "files" ascending by file size.*/
        int n = files.length;//declare the variable n, then assign it to the length of "files"
        for (int i = 1; i < n; ++i) {//loops through the "files", then move items of files[0..i-1],
            //that are greater than fileV to position ahead of their position
            MyFile fileV = files[i];//assign the second item to the fileV
            int j = i - 1;//get the index of the before item fileV
            switch (sf) {//check the SortField, which get from user.
                case byName://case byName
                    while (j >= 0 && (files[j].getName().compareToIgnoreCase(fileV.getName())) > 0) {
                        //compare two string
                        //compare result: a negative integer  is greater than, zero is equal to, or a positive integer is less than and ignoring case sensitive.
                        files[j + 1] = files[j];//if the conditions matched, set the files to be back 1
                        j = j - 1;//minus 1, to compare with all the before items
                    }
                    break;
                case bySize://case bySize
                    while (j >= 0 && files[j].getSize() > fileV.getSize()) {
                        //compare two long integer
                        files[j + 1] = files[j];//if the conditions matched, set the files to be back 1
                        j = j - 1;//minus 1, to compare with all the before items
                    }
                    break;
            }
            files[j + 1] = fileV;//set files[j] to be the lower item, j can be -1. 
        }

    }

    //The function use for partition and use in the QUICKSORT processes
    public static int partitionQS(int low, int high, SortField sf) {
        MyFile fileC = files[high];
        int i = (low - 1); // index of smaller element, first run  i = -1.
        for (int j = low; j < high; j++) {
            switch (sf) {
                case byName://case byName
                    if (files[j].getName().compareToIgnoreCase(fileC.getName()) <= 0) {
                        // If current element is smaller than or equal to fileC 
                        //compare two string
                        //compare result: a negative integer  is greater than, zero is equal to, or a positive integer or less than and ignoring case sen
                        i++;
                        // swap files[i] and files[j] 
                        MyFile temp = files[i];
                        files[i] = files[j];
                        files[j] = temp;
                    }
                    break;
                case bySize://case bySize
                    if (j >= 0 && files[j].getSize() <= fileC.getSize()) {
                        // If current element is smaller than or equal to fileC 
                        // compare two long integer
                        i++;
                        // swap files[i] and files[j] 
                        MyFile temp = files[i];
                        files[i] = files[j];
                        files[j] = temp;
                    }
                    break;
            }
        }
        // swap files[i+1] and files[high] (or fileC) 
        MyFile temp = files[i + 1];
        files[i + 1] = files[high];
        files[high] = temp;
        return i + 1;
    }

    //sort the list of files ascending by size (use quick sort), though sortField bySize or byName
    public static void quickSort(int low, int high, SortField sf) {
        if (low < high) {
            //partIndex is index of the partition, files[partIndex] is now at exact place
            int partIndex = partitionQS(low, high, sf);
            quickSort(low, partIndex - 1, sf); //recurse sort elements before
            quickSort(partIndex + 1, high, sf); //recurse sort elements after then, we are done the sorted before partIndex.
        }
    }

    //sort the list of files ascending by size (use selection sort), though sortField bySize or byName
    public static void selectionSort(SortField sf) {
        /*You should insert code for sorting here, you are going to sort the list of
        loaded files named "files" ascending by file size.*/
        int n = files.length;//get the length of the files list
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;//looking for the minimum element in unsorted array.
            for (int j = i + 1; j < n; j++) {
                switch (sf) {
                    case byName:
                        if (files[j].getName().compareToIgnoreCase(files[minIndex].getName()) < 0) {
                            //compare two string
                            //compare result: a negative integer  is greater than, zero is equal to, or a positive integer or less than and ignoring case s
                            minIndex = j;
                        }
                        break;
                    case bySize:
                        if (files[j].getSize() < files[minIndex].getSize()) {
                            //compare two long integer
                            minIndex = j;
                        }
                        break;
                }
            }
            // Swap the found minimum element with the first element.
            MyFile temp = files[minIndex];
            files[minIndex] = files[i];
            files[i] = temp;
        }
    }

    //sort and output sorted list of text files
    public static void sort(SortField sf, SortType st) {
        switch (st) {//switch statement to choose the case of sort with the SortField parameter
            case INSERTIONSORT:
                insertionSort(sf);
            case SELECTIONSORT:
                selectionSort(sf);
            case QUICKSORT:
                quickSort(0, files.length - 1, sf);
        }
        //list the files out if they are have more than 0, else print out the empty mention
        list(files);//output result after sorting
    }

    //return true if given MyFile contains given keyword, otherwise return false
    public static boolean searchFile(MyFile mf, String keyword) throws IOException {
        try {
            String a = mf.getName().toLowerCase();
            String b = keyword.toLowerCase();
            if (!mf.getName().toLowerCase().endsWith(".txt")) {//avoid all other extensions, but .txt
                return false;
            }
            if (a.matches("(.*)" + b + "(.*)")) {//regex String a matches String b (keyword)
                return true;
            }
            //These statements below are use for search in file .txt
            //read the content of mf and see if keyword is in the content of mf or not
            FileReader fr = null;
            LineNumberReader lnr = null;
            String str;
            try {
                // create new reader
                fr = new FileReader(mf.getFullPath());
                lnr = new LineNumberReader(fr);
                while (((str = lnr.readLine())) != null) {// read lines till the end of the stream
                    String strC = str.toLowerCase();
                    if (strC.matches("(.*)" + b + "(.*)")) {//compare to get the result of searching
                        return true;
                    }
                }
            } catch (IOException e) {//if any error occurs
                if (fr != null) {
                    fr.close();
                }
                if (lnr != null) {
                    lnr.close();
                }
                System.out.println(RED + "Sorry, something went wrong while searching the file .txt!" + RESET);
            }
            return false;
        } catch (NullPointerException e) {//catch the exception
            System.out.println(RED + "Sorry, cannot find file which you want to open!" + RESET);
            return false;
        }
    }

    //output information of all files which content has given keyword
    public static void searchFile(String keyword) throws IOException {
        //save all files which matched given keyword to the list and output the list
        List<MyFile> listFiles = new ArrayList<>();
        for (MyFile f : files) {
            if (searchFile(f, keyword)) {
                listFiles.add(f);//add the object to the list
            }
        }
        MyFile[] foundFiles = listFiles.stream().toArray(MyFile[]::new);
        //list the files out if they are have more than 0, else print out the empty mention
        list(foundFiles);
    }

    //Open the txtFile by the fileName by the openFileInFolder() function. This function use to check such exceptions!
    public static void openTxtFile(String fileName) throws IOException, Exception {
        String fileN = fileName.toLowerCase(); //convert the input String into lowercase
        if (fileN.endsWith(".txt")) {//if end with .txt
            try {
                if (files == null) {
                    FileReader fr = null;
                    LineNumberReader lnr = null;
                    String str;
                    // create new reader
                    fr = new FileReader(fileName);
                    lnr = new LineNumberReader(fr);
                    // read lines till the end of the stream
                    System.out.println(BLUE_BACKGROUND + GREEN + "HERE IS THE CONTENT OF THE FILE :" + fileName + RESET);
                    while (((str = lnr.readLine())) != null) {
                        System.out.println(str);
                    }
                    fr.close();
                    lnr.close();
                } else {
                    openFileInFolder(fileName);
                }
            } catch (IOException e) {//exception will through out if the list files is not load yet and the Filename is not true
                if (files == null) {
                    System.out.println(RED + "Sorry, cannot find the file you want to open!" + RESET);
                    System.out.print(GREEN + "Do you want to load the folder then open the file? (Y/N)? " + RESET);
                    boolean loadCheck = checkInputYN();
                    if (loadCheck) {
                        System.out.print(GREEN + "Enter a folder: " + RESET);
                        String a = inputString();
                        loadFiles(a);
                        //list the files out if they are have more than 0, else print out the empty mention
                        list(files);
                        System.out.print(GREEN + "Enter name of the file you want to open: " + RESET);
                        openTxtFile(inputString());
                    }
                } else {
                    openFileInFolder(fileName);
                }
            }
        } else {
            System.out.println(RED + "Sorry, that is not suitable file to be opened by this program!" + RESET);
            System.out.print(GREEN + "Enter name of the file you want to open: " + RESET);
            String openAgain = inputString();
            openTxtFile(openAgain);
        }
    }

    //This is another functions, which is the core to the nearest above function. 
    public static void openFileInFolder(String fileName) throws FileNotFoundException, IOException {
        String pathOfFile = null;
        for (MyFile f : files) {//loops through the files
            if (f.getName().equals(fileName)) {//check the name, to get the path
                pathOfFile = f.getFullPath();
            }
        }
        if (pathOfFile != null) {//if the path is null
            String str;
            FileReader fr = new FileReader(pathOfFile); // create new reader
            LineNumberReader lnr = new LineNumberReader(fr);
            System.out.println(BLUE_BACKGROUND + GREEN + "HERE IS THE CONTENT OF THE FILE :" + fileName + RESET);
            while (((str = lnr.readLine())) != null) { // read lines till the end of the stream
                System.out.println(str);
            }
            fr.close();
            lnr.close();
        } else {
            //print out the error
            System.out.println(RED + "Sorry, cannot find that file which you want to open!" + RESET);
        }
    }

    //input the option from user, and catch the wrong input
    public static int inputChoice(int min, int max) {
        while (true) { //loop until user input correct
            try {
                int inputC = Integer.parseInt(in.nextLine().trim());//get input by the String, parse to Int and remove the spaces
                if (inputC < min || inputC > max) {
                    throw new NumberFormatException();
                    //throw NumberFormatException(), to re-input
                }
                return inputC;//else return the value
            } catch (NumberFormatException e) {
                System.out.println(RED + "The range of input number is [" + min + ", " + max + "]" + RESET);//mention the wrong input
                System.out.print(GREEN + "Please input again: " + RESET);//input again
            }
        }
    }

    //inputString user for input String and check the empty String
    public static String inputString() {
        while (true) {
            String inputS = in.nextLine().trim();
            if (inputS.isEmpty()) {//check if the string is empty and if so, loop 
                System.out.println(RED + "Empty String!" + RESET);
                System.out.print(GREEN + "Please input again: " + RESET);
            } else {//if not, return the result
                return inputS;
            }
        }
    }

    //This functioon use for check the yes no input from user
    public static boolean checkInputYN() {
        while (true) {//loop until user input correct
            String inputYN = inputString();
            if (inputYN.equalsIgnoreCase("Y")) {//return true if user input Y/y
                return true;
            }
            if (inputYN.equalsIgnoreCase("N")) {//return false if user input N/n
                return false;
            }
            System.out.println(RED + "Please input Y/y or N/n." + RESET);
            System.out.print(GREEN + "Please input again: " + RESET);
        }
    }

    //main function with the menu 
    public static void main(String[] args) throws IOException, Exception {
        while (true) {//loops until user input 0
            System.out.println(BLUE_BACKGROUND + WHITE + "=======  MENU  =======" + RESET);
            System.out.println(BLUE_BACKGROUND + WHITE + "     1.Load files     " + RESET);
            System.out.println(BLUE_BACKGROUND + WHITE + "     2.Sort files     " + RESET);
            System.out.println(BLUE_BACKGROUND + WHITE + "     3.Search file    " + RESET);
            System.out.println(BLUE_BACKGROUND + WHITE + "     4.Open file      " + RESET);
            System.out.println(BLUE_BACKGROUND + WHITE + "     0.Exit           " + RESET);
            System.out.println(BLUE_BACKGROUND + WHITE + "======================" + RESET);
            System.out.println("");
            System.out.print(GREEN + "Enter your choice: " + RESET);
            switch (inputChoice(0, 4)) {//call the input choice to input value of the choice
                case 1://load files
                    System.out.print(GREEN + "Enter a folder: " + RESET);//this is duplicate because some specific reason
                    String a = inputString();                            //to make loop re-input for the NullPointerException of a false input folder in the funtion loadFiles()
                    loadFiles(a);
                    list(files);//list the files out if they are have more than 0, else print out the empty mention
                    break;
                case 2:
                    if (files == null) {
                        System.out.println(RED + "Sorry, you did not load any folder yet!" + RESET);
                    } else {
                        System.out.println("");
                        System.out.println(CYAN_BACKGROUND + WHITE + "=======  SORT  =======" + RESET);
                        System.out.println(CYAN_BACKGROUND + WHITE + " 1.Sort by name       " + RESET);
                        System.out.println(CYAN_BACKGROUND + WHITE + " 2.Sort by size       " + RESET);
                        System.out.println(CYAN_BACKGROUND + WHITE + "======================" + RESET);
                        System.out.println("");
                        System.out.print(GREEN + "Enter your choice: " + RESET);
                        int sortChoiceTS = inputChoice(1, 2);
                        System.out.println("");
                        System.out.println(PURPLE_BACKGROUND + WHITE + "==== SORT BY NAME ====" + RESET);
                        System.out.println(PURPLE_BACKGROUND + WHITE + " 1.Selection sort     " + RESET);
                        System.out.println(PURPLE_BACKGROUND + WHITE + " 2.Insertion sort     " + RESET);
                        System.out.println(PURPLE_BACKGROUND + WHITE + " 3.Quick sort         " + RESET);
                        System.out.println(PURPLE_BACKGROUND + WHITE + "======================" + RESET);
                        System.out.println("");
                        System.out.print(GREEN + "Enter your choice: " + RESET);
                        int sortChoiceS = inputChoice(1, 3);
                        if (sortChoiceTS == 1) {
                            switch (sortChoiceS) {
                                case 1:
                                    sort(SortField.byName, SortType.SELECTIONSORT);
                                    break;
                                case 2:
                                    sort(SortField.byName, SortType.INSERTIONSORT);
                                    break;
                                case 3:
                                    sort(SortField.byName, SortType.QUICKSORT);
                                    break;
                            }
                        } else {
                            switch (sortChoiceS) {
                                case 1:
                                    sort(SortField.bySize, SortType.SELECTIONSORT);
                                    break;
                                case 2:
                                    sort(SortField.bySize, SortType.INSERTIONSORT);
                                    break;
                                case 3:
                                    sort(SortField.bySize, SortType.QUICKSORT);
                                    break;
                            }
                        }
                    }
                    break;
                case 3:
                    if (files == null) {
                        System.out.println(RED + "Sorry, you did not load any folder yet!" + RESET);
                    } else {
                        System.out.print(GREEN + "Enter any keyword to search: " + RESET);
                        searchFile(inputString());
                    }
                    break;
                case 4:
                    System.out.print(GREEN + "Enter name of the file you want to open: " + RESET);
                    String fileName = inputString();
                    openTxtFile(fileName);
                    break;
                case 0:
                    System.out.println(RED_BACKGROUND + WHITE + " =============================================" + RESET);
                    System.out.println(RED_BACKGROUND + WHITE + " ====== THANK YOU FOR USING THE PROGRAM ======" + RESET);
                    System.out.println(RED_BACKGROUND + WHITE + " =============================================" + RESET);
                    return;
            }
        }
    }
}
//REFERENCES:
//           https://www.geeksforgeeks.org/insertion-sort/
//           https://www.geeksforgeeks.org/quick-sort/
//           https://www.geeksforgeeks.org/selection-sort/
//           https://stackoverflow.com/questions/4716503/reading-a-plain-text-file-in-java
//           https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
