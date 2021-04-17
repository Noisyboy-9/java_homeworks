package sina.shariati;

import sina.shariati.exceptions.InvalidExpiryDateException;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 * The type Product.
 */
public class Product {
    private String name;
    private String category;
    private int weight;
    private Date expiryDate;
    private Date productionDate;
    private double price;

    /**
     * Instantiate a new Product.
     *
     * @param name                the name
     * @param category            the category
     * @param weight              the weight
     * @param expiryDate          the expiry date
     * @param productionLocalDate the production date
     * @param price               the price
     * @throws InvalidExpiryDateException the invalid expiry date exception
     */
    public Product(String name, String category, int weight, Date expiryDate, Date productionDate, double price) throws InvalidExpiryDateException {
        if (expiryDate.after(productionDate)) {
            throw new InvalidExpiryDateException("A product can not have expiry date sooner then production date.");
        }

        this.name = name;
        this.category = category;
        this.weight = weight;
        this.expiryDate = expiryDate;
        this.productionDate = productionDate;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return weight == product.weight && Double.compare(product.price, price) == 0 && name.equals(product.name) && category.equals(product.category) && expiryDate.equals(product.expiryDate) && productionDate.equals(product.productionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, weight, expiryDate, productionDate, price);
    }

    /**
     * Get product name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Get product category.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Get product weight.
     *
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Get product expiry date.
     *
     * @return the expiry date
     */
    public Date getExpiryDate() {
        return expiryDate;
    }

    /**
     * Get product production date.
     *
     * @return the production date
     */
    public Date getProductionDate() {
        return productionDate;
    }

    /**
     * Get product price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "{" + "\n" +
                '\t' + "name='" + name + '\'' + ',' + '\n' +
                '\t' + "category='" + category + '\'' + ',' + '\n' +
                '\t' + "weight='" + weight + ',' + '\'' + '\n' +
                '\t' + "expiryDate='" + expiryDate + '\'' + ',' + '\n' +
                '\t' + "productionDate='" + productionDate + '\'' + ',' + '\n' +
                '\t' + "price='" + price + '\'' + ',' + '\n' +
                '}';
    }
}
