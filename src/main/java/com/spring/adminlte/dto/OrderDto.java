package com.spring.adminlte.dto;

public class OrderDto extends CommonDto{
    private int id;
    private int customerId;
    private String orderAt;
    private int quantity;

    public OrderDto(int createBy, int modifyBy, String createDate, String modifyDate, String status, int id, int customerId, String orderAt, int quantity) {
        super(createBy, modifyBy, createDate, modifyDate, status);
        this.id = id;
        this.customerId = customerId;
        this.orderAt = orderAt;
        this.quantity = quantity;
    }

    public OrderDto(int id, int customerId, String orderAt, int quantity) {
        this.id = id;
        this.customerId = customerId;
        this.orderAt = orderAt;
        this.quantity = quantity;
    }

    public OrderDto() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getOrderAt() {
        return orderAt;
    }

    public void setOrderAt(String orderAt) {
        this.orderAt = orderAt;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", orderAt='" + orderAt + '\'' +
                ", quantity=" + quantity +
                ", createDate='" + createDate + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
