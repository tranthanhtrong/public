
import entity.Product;

/**
 * Main class for running the Project Inventory management system
 *
 * @author tranthanhtrong
 */
public class Main {

    /**
     * Main function for running the program
     *
     * @param args
     */
    public static void main(String[] args) {
        MyProduct mp = new MyProduct();
        //These insert for fast testing
//        mp.insert(new Product("P04", "CD", 10, 2, 0.7));
//        mp.insert(new Product("P01", "DCD", 7, 8, 1.2));
//        mp.insert(new Product("P05", "Book", 21, 9, 2.9));
//        mp.insert(new Product("P02", "Tape", 11, 16, 0.3));

        while (true) {
            //these option 8,9,10 come from Advanced Criteria
            System.out.println("Product List");
            System.out.println("1. Insert a new product");
            System.out.println("2. In-order traverse");
            System.out.println("3. Breadth first traverse");
            System.out.println("4. Search by a product code");
            System.out.println("5. Delete by a product code");
            System.out.println("6. Simple balancing");
            System.out.println("7. Count number of products");
            System.out.println("8. Output all products which have price greater than or equal to the given price(ad)");//advanced criteria
            System.out.println("9. Create a new tree, have price greater than or equal to the given price(Ad)");//advanced criteria
            System.out.println("10.Calculate and output information of all node, including balance factor of Node(ad)");//advanced criteria
            System.out.println("0. Exit");
            System.out.print("Your choice: ");
            int choice = Valid.inputChoiceMintoMax(0, 10);
            switch (choice) {
                case 1:
                    mp.insert(createNewProduct());
                    break;
                case 2:
                    mp.inOrder();
                    break;
                case 3:
                    mp.BFT();
                    break;
                case 4:
                    System.out.print("Enter a product code to search: ");
                    String se = Valid.checkInputString();
                    mp.search(se);
                    break;
                case 5:
                    System.out.print("Enter a product code to delete: ");
                    String de = Valid.checkInputString();
                    mp.delete(de);
                    break;
                case 6:
                    mp.balance();
                    break;
                case 7:
                    int num = mp.size();
                    System.out.println("Number of products " + num);
                    break;
                case 8:
                    System.out.print("Enter a product price: ");
                    double m = Valid.checkInputDoubleGreaterThanZero();
                    mp.findPriceGreaterThanOrEqual(m);
                    break;
                case 9:
                    System.out.print("Enter a product price: ");
                    double mnt = Valid.checkInputDoubleGreaterThanZero();
                    mp.outputNewBinaryTree(mnt);
                    mp.resetNewTree();
                    break;
                case 10:
                    System.out.println("Here is the information of all nodes, including balance factor:");
                    mp.outputInformationIncludingBalanceFactor();
                    break;
                default:
                    return;
            }
        }

    }

    /**
    * Method: create  a new product
    * Input: get input from user
    * Output: return the Product
    * @return Product
    */
    public static Product createNewProduct() {
        //loop until user input not duplicate
        while (true) {
            System.out.print("Product code: ");
            String id = Valid.checkInputString();
            System.out.print("Product name: ");
            String title = Valid.checkInputString();
            System.out.print("Product quanity: ");
            int quantity = Valid.checkInputIntGreaterthanOrEqualtoZero();
            System.out.print("Number of product has saled: ");
            int lended = Valid.checkInputIntGreaterthanOrEqualtoZero();
            System.out.print("Product Price: ");
            double price = Valid.checkInputDoubleGreaterThanZero();
            return new Product(id, title, quantity, lended, price);
        }
    }
}
