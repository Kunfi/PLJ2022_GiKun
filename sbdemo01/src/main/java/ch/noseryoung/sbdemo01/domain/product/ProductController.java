package ch.noseryoung.sbdemo01.domain.product;

import ch.noseryoung.sbdemo01.domain.exceptions.IdExistsException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok().body(service.getAllProducts());
    }

    @GetMapping ("/{productId}")
    public ResponseEntity<Product> findById
        (@PathVariable("productId") Integer productId) {
        return ResponseEntity.ok().body(service.findById(productId));
    }

    @PostMapping("/")
    public ResponseEntity<Product> createNewProduct(@Valid @RequestBody Product newProduct) {
        log.info("An item will be added.");
        return ResponseEntity.status(201).body(service.createNewProduct(newProduct));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct
        (@PathVariable("productId") Integer productId) {
        log.info("An item will be deleted.");
        return ResponseEntity.ok().body(service.deleteProduct(productId));
    }

   @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct
        (@PathVariable("productId") Integer productId, @Valid @RequestBody Product product) {
        return ResponseEntity.ok().body(service.updateProduct(product));
    }


    @GetMapping("/search")
        public ResponseEntity<List<Product>> findByPriceLessThan
        (@RequestParam(required = false, name = "priceLess") double price) {
        return ResponseEntity.ok().body(service.findByPriceLessThan(price));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException pnfe) {
        return ResponseEntity.status(404).body(pnfe.getMessage());
    }

    @ExceptionHandler(IdExistsException.class)
    public ResponseEntity<String> handleIdExistException(IdExistsException iee) {
        return ResponseEntity.status(400).body(iee.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
        MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
