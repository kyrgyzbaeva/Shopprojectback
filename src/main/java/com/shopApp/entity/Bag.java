package com.shopApp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bags")
public class Bag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private double price;

    private String production;

    private String material;

    // Конструкторы
    public Bag() {
    }

    public Bag(String brand, double price, String production, String material) {
        this.brand = brand;
        this.price = price;
        this.production = production;
        this.material = material;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Bag{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", production='" + production + '\'' +
                ", material='" + material + '\'' +
                '}';
    }
}

