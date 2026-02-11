package SpringBoot_Practice.RestAPI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * REST Controller demonstrating CRUD operations
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    private final List<Product> products = new ArrayList<>();
    private Long idCounter = 1L;
    
    // GET all products
    @GetMapping
    public List<Product> getAllProducts() {
        return products;
    }
    
    // GET product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return products.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    // POST create product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        product.setId(idCounter++);
        products.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
    
    // PUT update product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
        @PathVariable Long id,
        @RequestBody Product productDetails
    ) {
        return products.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .map(product -> {
                product.setName(productDetails.getName());
                product.setPrice(productDetails.getPrice());
                return ResponseEntity.ok(product);
            })
            .orElse(ResponseEntity.notFound().build());
    }
    
    // DELETE product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        boolean removed = products.removeIf(p -> p.getId().equals(id));
        return removed 
            ? ResponseEntity.noContent().build() 
            : ResponseEntity.notFound().build();
    }
    
    // Search by name
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String name) {
        return products.stream()
            .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
            .toList();
    }
}
