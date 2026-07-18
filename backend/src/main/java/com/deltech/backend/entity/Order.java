package com.deltech.backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "orders_number", nullable = false, length = 20)
    private String ordersNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "product_name", nullable = false, columnDefinition = "TEXT")
    private String productName;

    @Column(name = "product_quantity", nullable = false, length = 20)
    private String productQuantity;

    @Column(name = "product_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal productPrice;

    @Column(nullable = false, length = 20)
    private String currency;

    @Column(nullable = false, length = 20)
    private String subtotal;

    @Column(name = "note_customer", nullable = false, columnDefinition = "TEXT")
    private String noteCustomer;

    @Column(name = "order_date", nullable = false, updatable = false)
    private LocalDateTime orderDate = LocalDateTime.now();

    @Column(name = "order_status", nullable = false, length = 20)
    private String orderStatus = "pending payment";

    public Order() {}

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private String ordersNumber; private Customer customer; private String productName;
        private String productQuantity; private BigDecimal productPrice; private String currency;
        private String subtotal; private String noteCustomer;
        private LocalDateTime orderDate = LocalDateTime.now();
        private String orderStatus = "pending payment";
        public Builder ordersNumber(String v) { this.ordersNumber = v; return this; }
        public Builder customer(Customer v) { this.customer = v; return this; }
        public Builder productName(String v) { this.productName = v; return this; }
        public Builder productQuantity(String v) { this.productQuantity = v; return this; }
        public Builder productPrice(BigDecimal v) { this.productPrice = v; return this; }
        public Builder currency(String v) { this.currency = v; return this; }
        public Builder subtotal(String v) { this.subtotal = v; return this; }
        public Builder noteCustomer(String v) { this.noteCustomer = v; return this; }
        public Builder orderStatus(String v) { this.orderStatus = v; return this; }
        public Order build() {
            Order e = new Order();
            e.ordersNumber = ordersNumber; e.customer = customer; e.productName = productName;
            e.productQuantity = productQuantity; e.productPrice = productPrice; e.currency = currency;
            e.subtotal = subtotal; e.noteCustomer = noteCustomer; e.orderDate = orderDate;
            e.orderStatus = orderStatus;
            return e;
        }
    }

    public Long getId() { return id; }
    public String getOrdersNumber() { return ordersNumber; }
    public void setOrdersNumber(String v) { this.ordersNumber = v; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer v) { this.customer = v; }
    public String getProductName() { return productName; }
    public void setProductName(String v) { this.productName = v; }
    public String getProductQuantity() { return productQuantity; }
    public void setProductQuantity(String v) { this.productQuantity = v; }
    public BigDecimal getProductPrice() { return productPrice; }
    public void setProductPrice(BigDecimal v) { this.productPrice = v; }
    public String getCurrency() { return currency; }
    public void setCurrency(String v) { this.currency = v; }
    public String getSubtotal() { return subtotal; }
    public void setSubtotal(String v) { this.subtotal = v; }
    public String getNoteCustomer() { return noteCustomer; }
    public void setNoteCustomer(String v) { this.noteCustomer = v; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime v) { this.orderDate = v; }
    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String v) { this.orderStatus = v; }
}
