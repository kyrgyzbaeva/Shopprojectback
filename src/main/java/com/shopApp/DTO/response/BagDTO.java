package com.shopApp.DTO.response;

public class BagDTO {

    private Long id;
    private String brand;
    private double price;
    private String production;
    private String material;

    // Конструкторы
    public BagDTO() {
    }

    public BagDTO(Long id, String brand, double price, String production, String material) {
        this.id = id;
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

    // Метод toString() для удобства отображения объекта в виде строки
    @Override
    public String toString() {
        return "BagDTO{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", production='" + production + '\'' +
                ", material='" + material + '\'' +
                '}';
    }
}

