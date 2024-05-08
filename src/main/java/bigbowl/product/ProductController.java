package bigbowl.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public Iterable<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(Long id) {
        return productService.findById(id);
    }

    @PostMapping("")
    public Product createProduct(Product product) {
        return productService.saveOrUpdate(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(Long id, Product product) {
        product.setId(id);
        return productService.saveOrUpdate(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(Long id) {
        productService.deleteById(id);
    }


}
