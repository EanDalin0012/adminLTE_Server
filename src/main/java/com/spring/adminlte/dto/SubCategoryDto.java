package com.spring.adminlte.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubCategoryDto extends CommonDto {
    private int id;
    @JsonProperty("mainCategoryId")
    private int mainCatId;
    @JsonProperty("subCategoryName")
    private  String categoryName;
    private  String description;

    public SubCategoryDto(int createBy, int modifyBy, String createDate, String modifyDate, String status, int id, int mainCatId, String categoryName, String description) {
        super(createBy, modifyBy, createDate, modifyDate, status);
        this.id = id;
        this.mainCatId = mainCatId;
        this.categoryName = categoryName;
        this.description = description;
    }

    public SubCategoryDto(int id, int mainCatId, String categoryName, String description) {
        this.id = id;
        this.mainCatId = mainCatId;
        this.categoryName = categoryName;
        this.description = description;
    }

    public SubCategoryDto() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMainCatId() {
        return mainCatId;
    }

    public void setMainCatId(int mainCatId) {
        this.mainCatId = mainCatId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SubCategoryDto{" +
                "id=" + id +
                ", mainCatId=" + mainCatId +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", createDate='" + createDate + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
