package com.deltech.backend.dto;

public class TicketRequest {
    private Long productId;
    private String initialMessage;
    public TicketRequest() {}
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public String getInitialMessage() { return initialMessage; }
    public void setInitialMessage(String initialMessage) { this.initialMessage = initialMessage; }
}
