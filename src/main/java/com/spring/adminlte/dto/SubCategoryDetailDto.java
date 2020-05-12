package com.spring.adminlte.dto;

public class SubCategoryDetailDto extends CommonDto {
    private int subCatId;
    private int mainCatId;
    private String subCatName;
    private String mainCatName;
    private String description;

    public SubCategoryDetailDto(int createBy, int modifyBy, String createDate, String modifyDate, String status, int subCatId, int mainCatId, String subCatName, String mainCatName, String description) {
        super(createBy, modifyBy, createDate, modifyDate, status);
        this.subCatId = subCatId;
        this.mainCatId = mainCatId;
        this.subCatName = subCatName;
        this.mainCatName = mainCatName;
        this.description = description;
    }

    public SubCategoryDetailDto(int subCatId, int mainCatId, String subCatName, String mainCatName, String description) {
        this.subCatId = subCatId;
        this.mainCatId = mainCatId;
        this.subCatName = subCatName;
        this.mainCatName = mainCatName;
        this.description = description;
    }

    public SubCategoryDetailDto() {}

    public int getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(int subCatId) {
        this.subCatId = subCatId;
    }

    public int getMainCatId() {
        return mainCatId;
    }

    public void setMainCatId(int mainCatId) {
        this.mainCatId = mainCatId;
    }

    public String getSubCatName() {
        return subCatName;
    }

    public void setSubCatName(String subCatName) {
        this.subCatName = subCatName;
    }

    public String getMainCatName() {
        return mainCatName;
    }

    public void setMainCatName(String mainCatName) {
        this.mainCatName = mainCatName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SubCategoryDetials{" +
                "subCatId=" + subCatId +
                ", mainCatId=" + mainCatId +
                ", subCatName='" + subCatName + '\'' +
                ", mainCatName='" + mainCatName + '\'' +
                ", description='" + description + '\'' +
                ", createDate='" + createDate + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
