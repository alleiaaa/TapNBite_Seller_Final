package com.example.tapnbiteseller;

public class ProductModel {
    private String name;
    private double price;
    private String category;
    private int stock;
    private boolean isAvailable;
    private int imageResourceId;

    public ProductModel(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = 0;
        this.isAvailable = true;
        this.imageResourceId = 0;
    }

    public ProductModel(String name, double price, String category, int stock, boolean isAvailable) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = stock;
        this.isAvailable = isAvailable;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public int getStock() { return stock; }
    public void setStock(int stock) {
        this.stock = stock;
        this.isAvailable = stock > 0;
    }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    public int getImageResourceId() { return imageResourceId; }
    public void setImageResourceId(int imageResourceId) { this.imageResourceId = imageResourceId; }
}