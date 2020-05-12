package com.spring.adminlte.dto;

public class ImportProductDetailsDto extends CommonDto{
        private int id;
        private int productId;
        private String productName;
        private int companyId;
        private String companyName;
        private int supplierId;
        private String supplierName;
        private int price;
        private int discount;
        private int qty;
        private int total;
        private String currencyCode;
        private String description;

    public ImportProductDetailsDto(int createBy, int modifyBy, String createDate, String modifyDate, String status, int id, int productId, String productName, int companyId, String companyName, int supplierId, String supplierName, int price, int discount, int qty, int total, String currencyCode, String description) {
        super(createBy, modifyBy, createDate, modifyDate, status);
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.companyId = companyId;
        this.companyName = companyName;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.price = price;
        this.discount = discount;
        this.qty = qty;
        this.total = total;
        this.currencyCode = currencyCode;
        this.description = description;
    }

    public ImportProductDetailsDto(int id, int productId, String productName, int companyId, String companyName, int supplierId, String supplierName, int price, int discount, int qty, int total, String currencyCode, String description) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.companyId = companyId;
        this.companyName = companyName;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.price = price;
        this.discount = discount;
        this.qty = qty;
        this.total = total;
        this.currencyCode = currencyCode;
        this.description = description;
    }

    public ImportProductDetailsDto() {}

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
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
        return "ImportProductDetailsDto{" +
                "id=" + id +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", supplierId=" + supplierId +
                ", supplierName='" + supplierName + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", qty=" + qty +
                ", total=" + total +
                ", currencyCode='" + currencyCode + '\'' +
                ", description='" + description + '\'' +
                ", createDate='" + createDate + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
