package com.example.crud_techv.product;

import jakarta.persistence.*;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private float price;

    private LocalDate date_creation;

    //age = campo calculado
    @Transient
    private int age;

    public Product() {
    }

    public Product(Long id, String name, float price, LocalDate date_creation) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.date_creation = date_creation;
    }

    public Product(String name, float price, LocalDate date_creation) {
        this.name = name;
        this.price = price;
        this.date_creation = date_creation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalDate getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(LocalDate date_creation) {
        this.date_creation = date_creation;
    }

    public int getAge() {
        return Period.between(this.date_creation, LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }
}
