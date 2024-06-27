package com.example.crud_techv.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    //When we created in Class constructor, we avoid NullPointerException
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return this.productRepository.findAll();
    }

    public ResponseEntity<Object> newProduct(Product product) {
        Optional<Product> res = productRepository.findProductByName(product.getName());
        HashMap<String, Object> data = new HashMap<>();

        if (res.isPresent()) {
            data.put("error", true);
            data.put("message", "Product already created with the same name");
            return new ResponseEntity<>(
                    data,
                    HttpStatus.CONFLICT
            );
        }

        productRepository.save(product);
        data.put("data", product);
        data.put("message", "Product successfully created");
        return new ResponseEntity<>(
                data,
                HttpStatus.CREATED
        );
    }
}
