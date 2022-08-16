package ch.noseryoung.sbdemo01.domain.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Product {

    @Id
    @NotNull(message = "Needs Product ID.")
    @Positive(message = "Number has to be positive.")
    @Column(name = "product_id")
    private int productId;

    @NotNull(message = "Item needs a name.")
    @Column(name = "description")
    private String description;

    @NotNull(message = "Price has to bee put.")
    @Digits(message = "Please only put 2 digits after the fraction.", integer = 10, fraction = 2)
    @Column(name = "price")
    private double price;

    public Product(int productId, String description, double price) {
        this.productId = productId;
        this.description = description;
        this.price = price;
    }

    public Product() {

    }

    public int getProductId() {
        return productId;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
