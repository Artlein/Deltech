package com.deltech.backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_product", nullable = false, columnDefinition = "TEXT")
    private String nameProduct;

    @Column(name = "description_product", nullable = false, columnDefinition = "TEXT")
    private String descriptionProduct;

    @Column(name = "price_product", nullable = false, precision = 10, scale = 2)
    private BigDecimal priceProduct;

    @Column(nullable = false, length = 20)
    private String currency;

    @Column(name = "img_product", nullable = false, length = 255)
    private String imgProduct;

    @Column(name = "stock_product", nullable = false)
    private Integer stockProduct;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, length = 50)
    private String category = "OTHERS";

    public Product() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNameProduct() { return nameProduct; }
    public void setNameProduct(String v) { this.nameProduct = v; }
    public String getDescriptionProduct() { return descriptionProduct; }
    public void setDescriptionProduct(String v) { this.descriptionProduct = v; }
    public BigDecimal getPriceProduct() { return priceProduct; }
    public void setPriceProduct(BigDecimal v) { this.priceProduct = v; }
    public String getCurrency() { return currency; }
    public void setCurrency(String v) { this.currency = v; }
    public String getImgProduct() { return imgProduct; }
    public void setImgProduct(String v) { this.imgProduct = v; }
    public Integer getStockProduct() { return stockProduct; }
    public void setStockProduct(Integer v) { this.stockProduct = v; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime v) { this.createdAt = v; }
    public String getCategory() { return category; }
    public void setCategory(String v) { this.category = v; }
}
