package com.spring.adminlte.dto;

public class SupplyingDto extends CommonDto {
    private int id;
    private int supId;
    private int quantity;
    private String orderAt;
    private String arriveAt;
    private String arrived;
    private String description;

    public SupplyingDto(int createBy, int modifyBy, String createDate, String modifyDate, String status, int id, int supId, int quantity, String orderAt, String arriveAt, String arrived, String description) {
        super(createBy, modifyBy, createDate, modifyDate, status);
        this.id = id;
        this.supId = supId;
        this.quantity = quantity;
        this.orderAt = orderAt;
        this.arriveAt = arriveAt;
        this.arrived = arrived;
        this.description = description;
    }

    public SupplyingDto(int id, int supId, int quantity, String orderAt, String arriveAt, String arrived, String description) {
        this.id = id;
        this.supId = supId;
        this.quantity = quantity;
        this.orderAt = orderAt;
        this.arriveAt = arriveAt;
        this.arrived = arrived;
        this.description = description;
    }

    public SupplyingDto() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSupId() {
        return supId;
    }

    public void setSupId(int supId) {
        this.supId = supId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderAt() {
        return orderAt;
    }

    public void setOrderAt(String orderAt) {
        this.orderAt = orderAt;
    }

    public String getArriveAt() {
        return arriveAt;
    }

    public void setArriveAt(String arriveAt) {
        this.arriveAt = arriveAt;
    }

    public String getArrived() {
        return arrived;
    }

    public void setArrived(String arrived) {
        this.arrived = arrived;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SupplyingDto{" +
                "id=" + id +
                ", supId=" + supId +
                ", quantity=" + quantity +
                ", orderAt='" + orderAt + '\'' +
                ", arriveAt='" + arriveAt + '\'' +
                ", arrived='" + arrived + '\'' +
                ", description='" + description + '\'' +
                ", createDate='" + createDate + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
