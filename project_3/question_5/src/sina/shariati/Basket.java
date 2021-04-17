package sina.shariati;

import sina.shariati.exceptions.NoSuchProductException;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Basket.
 */
public class Basket {
    private final HashMap<Product, Integer> basketToStockMap;

    /**
     * Instantiates a new Basket.
     */
    public Basket() {
        this.basketToStockMap = new HashMap<>();
    }

    /**
     * Add product to shopping basket.
     *
     * @param product the product
     */
    public void addProduct(Product product) {
        if (this.basketToStockMap.containsKey(product)) {
//            the product has been added to basket before
            int initialCount = this.basketToStockMap.get(product);
            int count = initialCount + 1;
            this.basketToStockMap.put(product, count);
            return;
        }
//        the product have not been added to inventory yet
        this.basketToStockMap.put(product, 1);
    }

    /**
     * Remove product from shopping basket if it exist.
     *
     * @param product the product
     * @throws NoSuchProductException the no such product exception
     */
    public void removeProduct(Product product) throws NoSuchProductException {
        if (!this.basketToStockMap.containsKey(product)) {
            throw new NoSuchProductException("The specified product haven't been added to the inventory yet");
        }

        this.basketToStockMap.remove(product);
    }

    /**
     * Calculate total price of all added products with their counts.
     *
     * @return the double
     */
    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Map.Entry<Product, Integer> entry : this.basketToStockMap.entrySet()) {
            Product product = entry.getKey();
            int count = entry.getValue();

            totalPrice += product.getPrice() * count;
        }

        return totalPrice;
    }

    @Override
    public String toString() {
        if (this.basketToStockMap.size() == 0) {
            return "Basket is empty!";
        }

        String string = "";
        int counter = 1;
        for (Map.Entry<Product, Integer> entry : this.basketToStockMap.entrySet()) {
            Product product = entry.getKey();
            int supply = entry.getValue();

            string = string.concat(counter + ") " + "Product: " + product.toString() + " in Stock : " + supply + "\n");
            counter++;
        }

        return string;
    }
}
