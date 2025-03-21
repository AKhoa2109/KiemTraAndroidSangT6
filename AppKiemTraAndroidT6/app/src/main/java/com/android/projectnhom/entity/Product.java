package com.android.projectnhom.entity;



import com.google.gson.annotations.SerializedName;


public class Product {
    private int id;
    private String name;
    private double price;

    public Product(String name, int id, double price, String images, String createdAt) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.images = images;
        this.createdAt = createdAt;
    }

    private String images;
    private String createdAt;

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getImages() { return images; }
    public String getCreatedAt() { return createdAt; }
}
