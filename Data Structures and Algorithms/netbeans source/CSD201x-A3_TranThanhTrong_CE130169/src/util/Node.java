
package util;

/**
 * This class is given:. contains information of a Node, including the info 
 * (data of a node) left and right pointers which reference to left node and 
 * right node in the Binary Search Tree.
 * @author tranthanhtrong
 * @param <T> 
 */
public final class Node<T> {
    
    public T info;
    public Node<T> left, right;
    public int bf; 

    /**
     * Method: Empty constructor
     * Input: no
     * Output: to initialize the present of the class
     */
    public Node() {
    }

    /**
     * Method:. constructor
     * Input: no, but @param
     * Output: set up the information for the Node of object T
     * @param info
     * @param left
     * @param right 
     */
    public Node(T info, Node<T> left, Node<T> right) {
        this.info = info;
        this.left = left;
        this.right = right;
       
    }
    /**
     * Method:. constructor
     * Input: no, but @param
     * Output: set the info for the Node of object T, but no left no right
     * @param info 
     */
    public Node(T info) {
        this(info,null,null);
    }
   
}
