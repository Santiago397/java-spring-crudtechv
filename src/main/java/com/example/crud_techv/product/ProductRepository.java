package com.example.crud_techv.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // @Query("SELECT * FROM Product p WHERE p.name =  ")
    Optional<Product> findProductByName(String name);
}
