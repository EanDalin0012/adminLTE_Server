package com.spring.adminlte.dto;

public class ProductDto extends CommonDto {
    private int proId;
    private String proName;
    private int subCateId;
    private  String description;
    private  String resourceFileInfoId;

    public ProductDto(int createBy, int modifyBy, String createDate, String modifyDate, String status, int proId, String proName, int subCateId, String description, String resourceFileInfoId) {
        super(createBy, modifyBy, createDate, modifyDate, status);
        this.proId = proId;
        this.proName = proName;
        this.subCateId = subCateId;
        this.description = description;
        this.resourceFileInfoId = resourceFileInfoId;
    }

    public ProductDto(int proId, String proName, int subCateId, String description, String resourceFileInfoId) {
        this.proId = proId;
        this.proName = proName;
        this.subCateId = subCateId;
        this.description = description;
        this.resourceFileInfoId = resourceFileInfoId;
    }

    public ProductDto(int createBy, int modifyBy, String createDate, String modifyDate, String status) {
        super(createBy, modifyBy, createDate, modifyDate, status);
    }

    public ProductDto() {

    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public int getSubCateId() {
        return subCateId;
    }

    public void setSubCateId(int subCateId) {
        this.subCateId = subCateId;
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

    @Override
    public String toString() {
        return "ProductDto{" +
                "proId=" + proId +
                ", proName='" + proName + '\'' +
                ", subCateId=" + subCateId +
                ", description='" + description + '\'' +
                ", resourceFileInfoId='" + resourceFileInfoId + '\'' +
                ", createDate='" + createDate + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
