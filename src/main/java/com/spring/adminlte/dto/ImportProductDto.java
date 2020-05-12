package com.spring.adminlte.dto;

public class ImportProductDto extends CommonDto{
    private int id;
    private int productId;
    private int supplierId;
    private int companyId;
    private int quantity;
    private int price;
    private int  discount;
    private float total;
    private String currencyCode;
    private String description;

    public ImportProductDto(int createBy, int modifyBy, String createDate, String modifyDate, String status, int id, int productId, int supplierId, int companyId, int quantity, int price, int discount, float total, String currencyCode, String description) {
        super(createBy, modifyBy, createDate, modifyDate, status);
        this.id = id;
        this.productId = productId;
        this.supplierId = supplierId;
        this.companyId = companyId;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
        this.total = total;
        this.currencyCode = currencyCode;
        this.description = description;
    }

    public ImportProductDto(int id, int productId, int supplierId, int companyId, int quantity, int price, int discount, float total, String currencyCode, String description) {
        this.id = id;
        this.productId = productId;
        this.supplierId = supplierId;
        this.companyId = companyId;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
        this.total = total;
        this.currencyCode = currencyCode;
        this.description = description;
    }

    public ImportProductDto() {}

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

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ImportProduct{" +
                "id=" + id +
                ", productId=" + productId +
                ", supplierId=" + supplierId +
                ", companyId=" + companyId +
                ", quantity=" + quantity +
                ", price=" + price +
                ", discount=" + discount +
                ", total=" + total +
                ", currencyCode='" + currencyCode + '\'' +
                ", description='" + description + '\'' +
                ", createDate='" + createDate + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
