package com.spring.adminlte.dto;

public class ProductDto extends CommonDto {
    private int productId;
    private String productName;
    private int subCategoryId;
    private  String description;

    public ProductDto(int createBy, int modifyBy, String createDate, String modifyDate, String status, int productId, String productName, int subCategoryId, String description) {
        super(createBy, modifyBy, createDate, modifyDate, status);
        this.productId = productId;
        this.productName = productName;
        this.subCategoryId = subCategoryId;
        this.description = description;
    }

    public ProductDto(int productId, String productName, int subCategoryId, String description) {
        this.productId = productId;
        this.productName = productName;
        this.subCategoryId = subCategoryId;
        this.description = description;
    }

    public ProductDto() {}

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

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", subCategoryId=" + subCategoryId +
                ", description='" + description + '\'' +
                ", createDate='" + createDate + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
