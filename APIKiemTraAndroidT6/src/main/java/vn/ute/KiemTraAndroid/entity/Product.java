package vn.ute.KiemTraAndroid.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Tên sản phẩm

    @Column(nullable = false)
    private Double price; // Giá sản phẩm

    @Column(nullable = false)
    private LocalDateTime createdAt; // Ngày tạo sản phẩm
    
    @Column(nullable = false)
    private String images;
    

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category; // Danh mục sản phẩm

}