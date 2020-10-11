
package entity;

import java.io.Serializable;

/**
 * This class is given:. holding information of Product, like product code, product name,
 * quantity, sales and price.
 *
 * @author tranthanhtrong
 */
public class Product implements Serializable {

    private String code, name;
    private int quantity, saled;
    private double price;

   /**
     * Method: Empty constructor
     * Input: no
     * Output: to initialize the present of the class
     */
    public Product() {
    }

    /**
     * Method: Constructor to create a new product 
     * Input: no input, through @param 
     * Output: set the information of the product
     *
     * @param code
     * @param name
     * @param quantity
     * @param saled
     * @param price
     */
    public Product(String code, String name, int quantity, int saled, double price) {
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.saled = saled;
        this.price = price;
    }

    /**
     * Method: to get the code of the Product 
     * Input: No 
     * Output: return the code of the Product
     *
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * Method: to set the code of the Product 
     * Input: no input, through @param
     * Output: no, but set the code of the Product
     *
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Method: to get the name of the Product 
     * Input: No 
     * Output: return the name of the Product
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Method: to set the name of the Product 
     * Input: no input, through @param
     * Output: no, but set the name of the Product
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method: to get the quantity of the Product 
     * Input: No 
     * Output: return the quantity of the Product
     *
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Method: to set the quantity of the Product 
     * Input: no input, through @param 
     * Output: no, but set the quantity of the Product
     *
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Method: to get the sales number of the Product 
     * Input: No 
     * Output: return the sales number of the Product
     *
     * @return saled
     */
    public int getSaled() {
        return saled;
    }

    /**
     * Method: to set the sales number of the Product 
     * Input: no input, through @param 
     * Output: no, but set the sales number of the Product
     *
     * @param saled
     */
    public void setSaled(int saled) {
        this.saled = saled;
    }

    /**
     * Method: to get the price of the Product 
     * Input: No 
     * Output: return the price of the Product
     *
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Method: to set the price number of the Product 
     * Input: no input, through @param 
     * Output: no, but set the price number of the Product
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Method: override the toString method 
     * Input: no 
     * Output: return the String
     *
     * @return the format String
     */
    @Override
    public String toString() {
        return String.format("%-10s%-20s%-10d%-10d%-10.2f\n", code, name, quantity, saled, price);
    }
    public String toStringInfor(){
        return String.format("%-10s%-20s%-10d%-10d%-10.2f", code, name, quantity, saled, price);
    }

    /**
     * Method: compare the object obj with the current Product 
     * Input: @param
     * Output: boolean value, true if it is equal the code, otherwise
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Product) {
            Product p = (Product) obj;
            return p.code.equalsIgnoreCase(this.code);
        }
        return false;
    }
}
