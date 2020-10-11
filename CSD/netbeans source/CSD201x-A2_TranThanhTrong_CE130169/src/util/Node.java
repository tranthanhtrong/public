package util;

/*
 * Name: Tran Thanh Trong 
 * Student code: CE130169
 * Project 2
 * Subject CSD201x
 */
public class Node<T> {

    public T info;
    public Node<T> next;

    public Node() {
    }

    public Node(T info, Node<T> next) {
        this.info = info;
        this.next = next;
    }

    public Node(T info) {
        this(info, null);
    }
}
