package ch.noseryoung.sbdemo01.domain.product;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException() {
        super("Product not found.");
    }

}
