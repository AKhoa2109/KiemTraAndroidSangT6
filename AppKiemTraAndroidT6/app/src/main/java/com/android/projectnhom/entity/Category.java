//Nguyễn Lý Hùng - 22110337

package com.android.projectnhom.entity;
import java.io.Serializable;

public class Category implements Serializable {
    private long categoryId;
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String categoryName;
    private String image;
    public Category(long categoryId, String categoryName, String image) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.image = image;
    }
}
