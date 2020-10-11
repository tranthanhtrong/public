package util;

import entity.Book;

/*
 * Name: Tran Thanh Trong 
 * Student code: CE130169
 * Project 2
 * Subject CSD201x
 */
public class MyList {

    Node<Book> head, tail;

    //ctor
    public MyList() {
        head = tail = null;
    }

    //check if the list is empty or not
    public boolean isEmpty() {
        return head == null;
    }

    //add a new Book to the end of list
    public void addLast(Book b) {
        Node<Book> p = new Node(b);
        if (isEmpty()) {
            head = tail = p;
        } else {
            tail.next = p;
            tail = p;
        }
    }

    //add a new Book to the begining of list
    public void addFirst(Book b) {
        Node<Book> node = new Node(b);
        if (isEmpty()) {
            head = tail = node;
        } else {
            node.next = head;
            head = node;
        }
    }

    //output information of all books in the list
    public void list() {
        Node<Book> node = head;
        while (node != null) {
            System.out.print(node.info);
            node = node.next;
        }
    }

    //return number of nodes/elements in the list
    public int size() {
        Node<Book> node = head;
        int c = 0;
        while (node != null) {
            c++;
            node = node.next;
        }
        return c;
    }

    //return a Node at position k, starting position is 0
    public Node<Book> getNode(int k) {
        Node<Book> node = head;
        int c = 0;
        while (node != null && c < k) {
            c++;
            node = node.next;
        }
        return node;
    }

    //add a new book after a position k
    public boolean addAfter(Book b, int k) {
        Node<Book> p = new Node(b);
        Node<Book> pr = getNode(k);
        if (pr == null) {
            System.out.println("Given previous node cannot be null!");
            return false;
        } else {
            p.next = pr.next;
            pr.next = p;
            return true;
        }
    }

    //delete a book at position k
    public boolean deleteAt(int k) {
        Node<Book> needDelete = getNode(k);
        if (needDelete == null) {
            System.out.println("The book at " + k + " is null.");
            return false;
        }
        //look for pr where pr.next = needDelete
        Node<Book> f = head, pr = null;
        while (f != null && f != needDelete) {//loops until f = needDelete
            pr = f;
            f = f.next;
        }

        if (pr == null) {  //when q = null, means head = null or head = needDelete
            head = head.next;
            if (head == null) {//check whether the head is null
                tail = null;
            }
        } else {//this happens when the user does not want to delete the head or head not null
            pr.next = needDelete.next;//make needDelete disappear
            if (needDelete == tail) {
                tail = pr;
            }
        }
        needDelete.next = null; //set outofRange (needDelete.next) be null, because it is no longer exist
        System.out.println("Successfully delete book at position: " + k);
        return true;
    }

    //search a Node by a given book code
    public Node<Book> search(String bCode) {
        Node<Book> node = head;
        while (node.info != null && !node.info.getbCode().equals(bCode)) {
            node = node.next;
        }
        return node;
    }

    //=================================================== Advanced Criteria 
    //sort By Price, accending
    public void sortByPrice() {
        int n = size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                Node<Book> pi = getNode(i), pj = getNode(j);
                if (pi.info.getPrice() > pj.info.getPrice()) {
                    Book t = pi.info;
                    pi.info = pj.info;
                    pj.info = t;
                }
            }
        }
    }

    //sort By BookCode, compare two String of Book Code,sorting accending, therefore B01 then B02, but B09 then B2. So user must input Book Code in suitable format B01,B02,...
    public void sortByBookCode() {
        int n = size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                Node<Book> pi = getNode(i), pj = getNode(j);
                if (pi.info.getbCode().compareTo(pj.info.getbCode()) > 0) {//compare two Strings, and consider which one is greater
                    Book t = pi.info;
                    pi.info = pj.info;
                    pj.info = t;
                }
            }
        }
    }
}
