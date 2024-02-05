package com.adjoda.product;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return ResponseEntity.ok(productRepository.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable String id,@RequestBody Product product) {
        Optional<Product> productById = productRepository.findById(id);
        if(productById.isPresent()) {
            Product updatedProduct = Product.builder()
                    .id(id)
                    .productName(product.getProductName())
                    .price(product.getPrice())
                    .attributes(product.getAttributes())
                    .build();

            return ResponseEntity.ok(productRepository.save(updatedProduct));
        } else {
            throw new ProductNotFound();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") String id) {
        Optional<Product> productById = productRepository.findById(id);
        return productById.map(ResponseEntity::ok).orElseThrow(ProductNotFound::new);
    }
}
