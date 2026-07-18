package com.deltech.backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "user_cart")
public class UserCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    @Column(name = "product_name", nullable = false, length = 255)
    private String productName;

    @Column(name = "product_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal productPrice;

    @Column(nullable = false, length = 10)
    private String currency;

    public UserCart() {}

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private Customer customer; private Product product; private Integer quantity;
        private String productName; private BigDecimal productPrice; private String currency;
        public Builder customer(Customer v) { this.customer = v; return this; }
        public Builder product(Product v) { this.product = v; return this; }
        public Builder quantity(Integer v) { this.quantity = v; return this; }
        public Builder productName(String v) { this.productName = v; return this; }
        public Builder productPrice(BigDecimal v) { this.productPrice = v; return this; }
        public Builder currency(String v) { this.currency = v; return this; }
        public UserCart build() {
            UserCart e = new UserCart();
            e.customer = customer; e.product = product; e.quantity = quantity;
            e.productName = productName; e.productPrice = productPrice; e.currency = currency;
            return e;
        }
    }

    public Long getId() { return id; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer v) { this.customer = v; }
    public Product getProduct() { return product; }
    public void setProduct(Product v) { this.product = v; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer v) { this.quantity = v; }
    public String getProductName() { return productName; }
    public void setProductName(String v) { this.productName = v; }
    public BigDecimal getProductPrice() { return productPrice; }
    public void setProductPrice(BigDecimal v) { this.productPrice = v; }
    public String getCurrency() { return currency; }
    public void setCurrency(String v) { this.currency = v; }
}
