/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entity.Product;
import java.util.ArrayList;
import java.util.List;

/**
 * CLASS use for create tree with some operations
 * @author tranthanhtrong
 */
public class MyBSTree {

    //a root of tree
    public Node<Product> root;

    /**
     * Method: Empty constructor 
     * Input: no 
     * Output: to initialize the present of the class,, set root is null
     */
    public MyBSTree() {
        root = null;
    }

    /**
     * Method: visit a node of a tree
     * Input: no @param
     * Output: display the information of product p
     * @param p 
     */
    public void visit(Node<Product> p) {
        if (p == null) {
            return;
        }
        System.out.print(p.info);
    }

    /**
     * Method:. visit a node of a tree -> output information of visited node, including balance factor
     * Input: no, but @param
     * Output: display the result
     * @param p 
     */
    public void visitIncludingBalanceFactor(Node<Product> p) {
        if (p == null) {
        } else {
            visitIncludingBalanceFactor(p.left);
            System.out.print(p.info.toStringInfor() + String.valueOf(p.bf) + "\n");
            visitIncludingBalanceFactor(p.right);
        }
    }

    /**
     * Method:. return true if tree is empty otherwise return false
     * Input:no
     * Output: boolean value
     * @return 
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Method:. traverse in-order a tree
     * Input: no, but @param
     * Output: display out the result, by calling the visit function.
     * @param p 
     */
    public void inOrder(Node<Product> p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        visit(p);
        inOrder(p.right);
    }

    /**
     * Method:. count the number of the node
     * Input: no, but @param
     * Output: return the number of 
     * @param node
     * @return 
     */
    public int count(Node<Product> node) {
        if (null == node) {
            return 0;
        }
        return 1 + count(node.left) + count(node.right);
    }

    /**
     * Method:. traverse with Breadth first traverse
     * Input: no, but @param
     * Output: the Breadth first traverse of the tree, by calling the Queue Class
     * @param p 
     */
    public void BFT(Node<Product> p) {
        if (p == null) {
            return;
        }
        MyQueue mq = new MyQueue();
        mq.enqueue(p);//add last
        while (!mq.isEmpty()) {
            Node q = (Node) mq.dequeue();//remove first, mean remove the right leaf
            visit(q);//print out the node q
            if (q.left != null) {
                mq.enqueue(q.left);//add last
            }
            if (q.right != null) {
                mq.enqueue(q.right);//add last
            }
        }
    }

    /**
     * Method:. insert a new Product to a tree
     * Input: no, but @param Product object
     * Output: no, but insert new product to the tree.
     * @param product 
     */
    public void insert(Product product) {
        Node<Product> newNode = new Node(product);
        Node<Product> father = null;
        Node<Product> q = root;
        while (q != null) {
            if (q.info.getCode() == null ? product.getCode() == null : q.info.getCode().equalsIgnoreCase(product.getCode())) {
                System.out.println("Product code cannot be duplicated...");
                return;
            }
            if (q.info.getCode().compareToIgnoreCase(product.getCode()) < 0) {
                father = q;
                q = q.right;
            } else {
                father = q;
                q = q.left;
            }
        }
        if (father == null) {
            root = newNode;
        } else if (newNode.info.getCode().compareToIgnoreCase(father.info.getCode()) > 0) {
            father.right = newNode;
        } else {
            father.left = newNode;
        }
    }

    //balance a tree
    //step 1: traverse inorder tree and copy all item on tree to an arraylist
    //step 2: insert all items of list to a tree
    /**
     * Method: these code below is given in LMS
     * Input: no, but @param
     * Output: re-constructor a new balance tree
     * @param list
     * @param p 
     */
    private void buildArray(List<Node<Product>> list, Node<Product> p) {
        if (p == null) {
            return;
        }
        buildArray(list, p.left);
        list.add(p);
        buildArray(list, p.right);
    }

    //step 2:
    private void balance(List<Node<Product>> list, int f, int l) {
        if (f > l) {
            return;
        }
        int m = (f + l) / 2;
        Node<Product> p = (Node<Product>) list.get(m);
        insert(p.info);
        balance(list, f, m - 1);
        balance(list, m + 1, l);

    }

    /**
     * Method: use for balance the current tree
     * Input: no
     * Output: calling the two above steps to balance the tree.
     */
    public void balance() {
        ArrayList<Node<Product>> pl = new ArrayList();
        buildArray(pl, root);
        int l = pl.size();
        int f = 0;
        MyBSTree tree = new MyBSTree();//create a new Binary Searchtree and insert all items
        tree.balance(pl, f, l - 1);
        root = tree.root;
    }

   /**
    * Method: search a Node of tree by product code 
    * Input: no, but use @param
    * Output: return null if given code does not exists, else print out
    * @param p
    * @param code
    * @return 
    */
    public Node<Product> search(Node<Product> p, String code) {
        if (p == null) {
            return null;
        } else if (p.info.getCode() == null ? code == null : p.info.getCode().equalsIgnoreCase(code)) {
            return p;
        } else if (p.info.getCode().compareToIgnoreCase(code) > 0) {
            return search(p.left, code);
        } else {
            return search(p.right, code);
        }
    }

    /**
     * Method: print All Nodes Greater Than Price
     * Input: price from @param
     * Output: print out the information
     * @param p
     * @param data 
     */
    public void printAllNodeGreaterThanPrice(Node<Product> p, double data) {
        if (p != null) {
            if (p.info.getPrice() >= data) {
                System.out.println(p.info.toString());
            }
            printAllNodeGreaterThanPrice(p.left, data);
            printAllNodeGreaterThanPrice(p.right, data);
        }
    }

    /**
     * Method:. delete a node by a given product code (delete copy) (Teacher only taught me that.)
     * Input: no, but @param code
     * Output: no, but delete the product with Product code
     * @param code 
     */
    public void delete(String code) {
        Node<Product> p = search(root, code);//search the code in the tree
        if (p == null) {//if not found
            System.out.println("Product code " + code + " does not exists, deletion failed");
            return;
        } else {//if found
            System.out.println("Product code " + code + " has been deleted");
        }
        // find father of p
        Node<Product> f = null, q = root;
        while (q != p) {
            f = q;
            if (q.info.getCode().compareTo(p.info.getCode()) > 0) {
                q = q.left;
            } else {
                q = q.right;
            }
        }
       
        if (p.left == null && p.right == null) { // Case 1: p has no child
            if (f == null) {
                root = null;
            } else if (f.left == p) {
                f.left = null;
            } else {
                f.right = null;
            }
        } 
        else if (p.left != null && p.right == null) {// Case 2: p has left child only
            if (f == null) {
                root = p.left;
            } else if (f.left == p) {
                f.left = p.left;
            } else {
                f.right = p.left;
            }
        } 
        else if (p.left == null && p.right != null) {// Case 3: p has right child only
            if (f == null) {
                root = p.right;
            } else if (f.left == p) {
                f.left = p.right;
            } else {
                f.right = p.right;
            }
        } 
        else if (p.left != null && p.right != null) {// Case 4. p has left and right child
            // look for q is biggest in the left child of p , then q is the rightest child of the left child of p
            q = p.left;
            f = null;
            while (q.right != null) {
                f = q;
                q = q.right;
            }
            p.info = q.info;
            // delete q
            if (f == null) {
                p.left = q.left;
            } else {
                f.right = q.left;
            }
        }

    }

}
