package com.example.crud_techv.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/products")
public class ProductController {

    private final ProductService productService;

    /*
        We could use this annotation directly to private variable.
        @Autowired
        private ProductService productService;
    */
    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> getAllProducts () {
        return productService.getAllProducts();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getById (@PathVariable Long id) {
        return this.productService.findProductById(id);
    }

    @PostMapping
    public ResponseEntity<Object> createProduct (@RequestBody Product product) {
        return this.productService.newProduct(product);
    }
}
