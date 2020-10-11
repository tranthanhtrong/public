import entity.Book;
import util.MyList;
import util.Node;
/*
 * Name: Tran Thanh Trong 
 * Student code: CE130169
 * Project 2
 * Subject CSD201x
 */
public class BookList {

    //a list of book
    private MyList books;

    public MyList getBooks() {
        return books;
    }

    public BookList() {
        books = new MyList();
    }

    //1.0 accept information of a Book
    private Book getBook(int k) {
        return books.getNode(k).info;
    }

    //1.1 accept and add a new Book to the end of book list
    public void addLast(Book p) {
        books.addLast(p);
    }

    //1.2 output information of book list
    public void list() {
        if (books.size() > 0) {
            System.out.printf("%-10s%-20s%-10s%-10s%-10s%-10s\n", "Code", "Title", "Quantity", "Lender", "Price", "Value");
        }else{
            System.out.println("Have no book to show!");
        }
        books.list();
    }

    //1.3 search book by book code
    public void search(String bCode) {
       Node<Book> resultOfSearching = books.search(bCode);
        System.out.println("Information of book code " + resultOfSearching.info.getbCode());
        System.out.println(resultOfSearching.info.toString());
    }

    //1.4 accept and add a new Book to the begining of book list
    public void addFirst(Book p) {
        books.addFirst(p);
    }

    //1.5 Add a new Book after a position k
    public boolean addAfter(Book b, int k) {
        return books.addAfter(b, k);
    }

    //1.6 Delete a Book at position k
    public boolean deleteAt(int k) {
       return books.deleteAt(k);
    }

    //====================================================Advanced criteria
    public void sortByBC() {
        books.sortByBookCode();
    }
    public void sortByPrice(){
        books.sortByPrice();
    }
    void setEmptyForList() {
        books = new MyList();
    }
}
