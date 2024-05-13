package bigbowl.product;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product saveOrUpdate(Product product) {
        if (product.getId() == null) {
            return repository.save(product);
        } else {
            return repository.save(product);
        }
    }

    public void deleteById(Long id) {
        repository.deleteById(Math.toIntExact(id));
    }

    public Product findById(Long id) {
        return repository.findById(Math.toIntExact(id)).orElse(null);
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

}

