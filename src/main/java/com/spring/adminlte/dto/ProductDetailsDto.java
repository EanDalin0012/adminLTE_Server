package com.spring.adminlte.dto;

import com.spring.adminlte.dto.CommonDto;

public class ProductDetailsDto extends CommonDto {
    private int productId;
    private String productName;
    private int subCategoryId;
    private String subCategoryName;
    private  String description;
    private String  resourceFileInfoId;
    private int mainCategoryId;
    private String mainCategoryName;

    public ProductDetailsDto(int createBy, int modifyBy, String createDate, String modifyDate, String status, int productId, String productName, int subCategoryId, String subCategoryName, String description, String resourceFileInfoId, int mainCategoryId, String mainCategoryName) {
        super(createBy, modifyBy, createDate, modifyDate, status);
        this.productId = productId;
        this.productName = productName;
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
        this.description = description;
        this.resourceFileInfoId = resourceFileInfoId;
        this.mainCategoryId = mainCategoryId;
        this.mainCategoryName = mainCategoryName;
    }

    public ProductDetailsDto(int productId, String productName, int subCategoryId, String subCategoryName, String description, String resourceFileInfoId, int mainCategoryId, String mainCategoryName) {
        this.productId = productId;
        this.productName = productName;
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
        this.description = description;
        this.resourceFileInfoId = resourceFileInfoId;
        this.mainCategoryId = mainCategoryId;
        this.mainCategoryName = mainCategoryName;
    }

    public ProductDetailsDto(int createBy, int modifyBy, String createDate, String modifyDate, String status) {
        super(createBy, modifyBy, createDate, modifyDate, status);
    }

    public ProductDetailsDto() {

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

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResourceFileInfoId() {
        return resourceFileInfoId;
    }

    public void setResourceFileInfoId(String resourceFileInfoId) {
        this.resourceFileInfoId = resourceFileInfoId;
    }

    public int getMainCategoryId() {
        return mainCategoryId;
    }

    public void setMainCategoryId(int mainCategoryId) {
        this.mainCategoryId = mainCategoryId;
    }

    public String getMainCategoryName() {
        return mainCategoryName;
    }

    public void setMainCategoryName(String mainCategoryName) {
        this.mainCategoryName = mainCategoryName;
    }

    @Override
    public String toString() {
        return "ProductDetailsDto{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", subCategoryId=" + subCategoryId +
                ", subCategoryName='" + subCategoryName + '\'' +
                ", description='" + description + '\'' +
                ", resourceFileInfoId='" + resourceFileInfoId + '\'' +
                ", mainCategoryId=" + mainCategoryId +
                ", mainCategoryName='" + mainCategoryName + '\'' +
                ", createDate='" + createDate + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
