//Nguyễn Tuấn Thành - 22110418
package com.android.projectnhom.entity;


import com.google.gson.annotations.SerializedName;


public class Product {
    private int id;
    private String name;
    private double price;
    private String images;
    private String createdAt;

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getImages() { return images; }
    public String getCreatedAt() { return createdAt; }
}
