package sina.shariati;

import sina.shariati.exceptions.NoSuchProductException;

import java.util.HashMap;
import java.util.Map;

/**
 * An inventory of products.
 */
public class Inventory {
    private final HashMap<Product, Integer> productSupply;

    /**
     * Instantiate a new Inventory.
     */
    public Inventory() {
        this.productSupply = new HashMap<>();
    }

    /**
     * Gets product supply.
     *
     * @return the product supply
     */
    public HashMap<Product, Integer> getProductSupply() {
        return productSupply;
    }

    /**
     * Add a product to the inventory.
     *
     * @param product the product
     * @param stock   the stock
     */
    public void addProduct(Product product, int stock) {
        this.productSupply.put(product, stock);
    }

    /**
     * Remove a product from inventory if it already exist.
     *
     * @param product the product
     * @throws NoSuchProductException the no such product exception
     */
    public void removeProduct(Product product) throws NoSuchProductException {
        if (!this.productSupply.containsKey(product)) {
            throw new NoSuchProductException("The specified product haven't been added to the inventory yet");
        }

        this.productSupply.remove(product);
    }

    /**
     * Change supply of a product.
     *
     * @param product   the product
     * @param newSupply the new supply
     * @throws NoSuchProductException the no such product exception
     */
    public void changeProductSupply(Product product, int newSupply) throws NoSuchProductException {
        if (!this.productSupply.containsKey(product)) {
            throw new NoSuchProductException("The specified product haven't been added to the inventory yet");
        }

        this.productSupply.put(product, newSupply);
    }

    @Override
    public String toString() {
//        checking if all items are out of stock or not
        if (this.supplyCount() == 0) {
            return "We are out of stock!";
        }

        String string = "";
        int counter = 1;
        for (Map.Entry<Product, Integer> entry : this.productSupply.entrySet()) {
            Product product = entry.getKey();
            int supply = entry.getValue();

            string = string.concat(counter + ") " + "Product: " + product.toString() + " in Stock : " + supply + "\n");
            counter++;
        }

        return string;
    }

    private int supplyCount() {
        int sum = 0;

        for (int supply : this.productSupply.values()) {
            sum += supply;
        }

        return sum;
    }
}
