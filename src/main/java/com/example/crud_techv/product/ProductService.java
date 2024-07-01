package com.example.crud_techv.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public ResponseEntity<Object> findProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        HashMap<String, Object> dataRes = new HashMap<>();

        if (!product.isPresent()) {
            dataRes.put("error", true);
            dataRes.put("message", "Product not found");
            return new ResponseEntity<>(
                    dataRes,
                    HttpStatus.BAD_REQUEST
            );
        }

        dataRes.put("data", product);
        dataRes.put("message", "Product found");

        return new ResponseEntity<>(
                dataRes,
                HttpStatus.OK
        );

    }

    public ResponseEntity<Object> newProduct(Product product) {
        Optional<Product> res = productRepository.findProductByName(product.getName());
        HashMap<String, Object> data = new HashMap<>();

        if (res.isPresent() && product.getId() == null) {
            data.put("error", true);
            data.put("message", "Product already created with the same name");
            return new ResponseEntity<>(
                    data,
                    HttpStatus.CONFLICT
            );
        }

        data.put("message", "Product successfully created");

        if (product.getId() != null) {
            data.put("message", "Product successfully updated");
        }

        productRepository.save(product);
        data.put("data", product);
        return new ResponseEntity<>(
                data,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> removeProduct(Long id)   {

        Optional<Product> product = productRepository.findById(id);
        HashMap<String, Object> data = new HashMap<>();

        if (!product.isPresent()) {
            data.put("error", true);
            data.put("message", "Product not found");
            return new ResponseEntity<>(
                    data,
                    HttpStatus.BAD_REQUEST
            );
        }

        productRepository.deleteById(id);
        data.put("data", product);
        data.put("message", "Product successfully deleted");

        return new ResponseEntity<>(
                data,
                HttpStatus.OK
        );
    }
}
