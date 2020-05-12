package com.spring.adminlte.dto;

public class ProductOrderDto extends CommonDto{

    private int id;
    private int productId;
    private int orderId;
    private int quantity;
    private float price;
    private float total;
    private String orderAt;

    public ProductOrderDto(int createBy, int modifyBy, String createDate, String modifyDate, String status, int id, int productId, int orderId, int quantity, float price, float total, String orderAt) {
        super(createBy, modifyBy, createDate, modifyDate, status);
        this.id = id;
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.orderAt = orderAt;
    }

    public ProductOrderDto(int id, int productId, int orderId, int quantity, float price, float total, String orderAt) {
        this.id = id;
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.orderAt = orderAt;
    }

    public ProductOrderDto() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getOrderAt() {
        return orderAt;
    }

    public void setOrderAt(String orderAt) {
        this.orderAt = orderAt;
    }

    @Override
    public String toString() {
        return "ProductOrderDto{" +
                "id=" + id +
                ", productId=" + productId +
                ", orderId=" + orderId +
                ", quantity=" + quantity +
                ", price=" + price +
                ", total=" + total +
                ", orderAt='" + orderAt + '\'' +
                ", createDate='" + createDate + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
