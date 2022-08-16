package ch.noseryoung.sbdemo01.domain.product;

import ch.noseryoung.sbdemo01.domain.exceptions.IdExistsException;
import ch.noseryoung.sbdemo01.domain.exceptions.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }


    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product findById(int productId) throws NotFoundException {
        return repository.findById(productId).orElseThrow(() -> new NotFoundException("Product"));
    }

    public Product createNewProduct(Product newProduct) throws IdExistsException {
        if (!repository.existsById(newProduct.getProductId())) {
            log.debug("Item creation successful");
            return repository.save(newProduct);
        }
        else {
            log.debug("Item creation NOT successful");
            throw new IdExistsException();
        }
    }

    public String deleteProduct(int productId) throws ProductNotFoundException{
        repository.findById(productId).orElseThrow(ProductNotFoundException::new);
        repository.deleteById(productId);
        return "Has been deleted";
    }

    public Product updateProduct(Product product){
            return repository.save(product);

    }

    public List<Product> findByPriceLessThan(double price) {
        return repository.findByPriceLessThan(price);
    }
}
