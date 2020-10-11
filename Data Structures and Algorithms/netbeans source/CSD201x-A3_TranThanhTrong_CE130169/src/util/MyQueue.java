
package util;

import java.util.LinkedList;

/**
 * This class is given:. contains code for implementation a queue and some 
 * operations on the queue
 * @author tranthanhtrong
 */
public class MyQueue {
    
    LinkedList a = null;
    /**
     * Method:. Empty constructor
     * Input: no
     * Output:. to initialize the present of the class, and new LinkedList will
     * be initialize as well.
     */
    public MyQueue(){
        a = new LinkedList();
    }
    /**
     * Method:. place the object into a queue, add it to the tail of a queue.
     * Input: No, but @param
     * Output: NO, but add a object to the queue
     * @param obj 
     */
    public void enqueue(Object obj){
        a.addLast(obj);
    }
    /**
     * Method:. check the empty of the LinkedList of object
     * Input: no
     * Output: return boolean value
     * @return boolean value
     */
    public boolean isEmpty(){
        return a.isEmpty();
    }
    /**
     * Method:. take object out of a queue, remove the first available object 
     * from the head of a queue
     * @return Object
     */
    public Object dequeue(){
        if(isEmpty()) return null;
        else return a.removeFirst();
    }
    /**
     * Method:. get the object at first
     * Input: no
     * Output: return the front Object, the first one.
     * @return Object
     */
    public Object front(){
        if(isEmpty()) return null;
        else return a.getFirst();
    }
}
