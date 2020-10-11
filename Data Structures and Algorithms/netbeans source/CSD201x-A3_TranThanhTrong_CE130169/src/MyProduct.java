
import entity.Product;
import util.MyBSTree;
import util.Node;
/**
 * Class: use to make instance of the MyBSTree
 * @author tranthanhtrong
 */
public class MyProduct {

    MyBSTree tree;

    /**
     * Method: Empty constructor 
     * Input: no 
     * Output: to initialize the present of the class
     */
    public MyProduct() {
        tree = new MyBSTree();
    }

    //1.1 input and insert a new product to tree
    public void insert(Product product) {
        tree.insert(product);
    }

    //1.2 in-order traverse
    public void inOrder() {
        System.out.printf("%-10s%-20s%-10s%-10s%-10s\n", "Code", "Name", "Quantity", "Saled", "Price");
        tree.inOrder(tree.root);
    }

    //1.3 BFT a tree
    public void BFT() {
        System.out.printf("%-10s%-20s%-10s%-10s%-10s\n", "Code", "Name", "Quantity", "Saled", "Price");
        tree.BFT(tree.root);
    }

    //1.4 search a product by product code
    public void search(String a) {
        Node<Product> re = tree.search(tree.root, a);
        if (re != null) {
            String resultSearch = re.info.toString();
            System.out.println("Information of product code " + a);
            System.out.println(resultSearch);
        } else {
            System.out.println("Product code is not exist! ");
        }

    }

    //1.5 delete a product by product code
    public void delete(String code) {
        tree.delete(code);
    }

    //1.6 simply balancing a tree
    public void balance() {
        tree.balance();
        System.out.println("Tree balanced successfully!");
    }

    //1.7 count the number of products in the tree
    public int size() {
        return tree.count(tree.root);
    }
    //===================== Advanced Criteria ====================

    /**
     * Method: print out the Information of all the product has the price
     * greater than or equal to a 
     * Input: a from user from the Main class 
     * Output: display out the result out the screen
     *
     * @param a
     */
    public void findPriceGreaterThanOrEqual(double a) {
        System.out.printf("%-10s%-20s%-10s%-10s%-10s\n", "Code", "Name", "Quantity", "Saled", "Price");
        tree.printAllNodeGreaterThanPrice(tree.root, a);
    }

    /**
     * Method: print out the in information of all product, including the
     * balance factor 
     * Input: non 
     * Output: print out the in information of all product, including the balance factor
     */
    public void outputInformationIncludingBalanceFactor() {
        int pBF = printBalanceFactor(tree.root);
        System.out.printf("%-10s%-20s%-10s%-10s%-10s%-10s\n", "Code", "Name", "Quantity", "Saled", "Price", "BFactor");
        tree.visitIncludingBalanceFactor(tree.root);
    }

    /**
     * Method: add the balance factor to the node 
     * Input: no, but @param 
     * Output: add directly to balance factor the node
     *
     * @param t
     * @return
     */
    public int printBalanceFactor(Node<Product> t) {
        if (t == null) {
            return 0;
        }
        if (t.left == null && t.right == null) {
            tree.root.bf = 0;
            return 1;
        }
        int heightL = printBalanceFactor(t.left);
        int heightR = printBalanceFactor(t.right);
        t.bf = heightL - heightR;
        return heightL + heightR + 1;
    }

    MyBSTree newTree = new MyBSTree();

    /**
     * Method: add the Product to the new Binary Search Tree 
     * Input: first, @param root, and price, then recursively 
     * Output: no, but insert to the new Tree
     *
     * @param p
     * @param data
     */
    public void outAllNodeGreaterThanPrice(Node<Product> p, double data) {
        if (p != null) {
            if (p.info.getPrice() >= data) {
                newTree.insert(p.info);
            }
            outAllNodeGreaterThanPrice(p.left, data);
            outAllNodeGreaterThanPrice(p.right, data);
        }
    }

    /**
     * Method: print out the new Tree 
     * Input: no, but first need to add data to the new Tree 
     * Output: display the result out of the new Tree
     *
     * @param data
     */
    public void outputNewBinaryTree(double data) {
        System.out.println("New Binary Tree with product price greater than or equal to " + data);
        System.out.printf("%-10s%-20s%-10s%-10s%-10s\n", "Code", "Name", "Quantity", "Saled", "Price");
        outAllNodeGreaterThanPrice(tree.root, data);
        visitInOrder(newTree.root);
    }

    /**
     * Method: use for print out the tree at this class, just make it clear that it is information of the new Tree
     * Input: parameter p, first it must be the root
     * Output: no, but print out the result
     * @param p
     */
    //visit a node of a tree -> output information of visited node
    public void visitInOrder(Node<Product> p) {
        if (p == null) {
            return;
        }
        visitInOrder(p.left);
        System.out.print(p.info);
        visitInOrder(p.right);
    }

    /**
     * Method: reset the size
     * Input: no
     * Output: no, but set new Tree again
     */
    public void resetNewTree() {
        newTree = new MyBSTree();
    }

}
