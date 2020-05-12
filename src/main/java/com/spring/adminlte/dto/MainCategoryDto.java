package com.spring.adminlte.dto;

public class MainCategoryDto extends CommonDto {
    private int id;
    private String mainCategoryName;
    private String description;

    public MainCategoryDto(int createBy, int modifyBy, String createDate, String modifyDate, String status, int id, String mainCategoryName, String description) {
        super(createBy, modifyBy, createDate, modifyDate, status);
        this.id = id;
        this.mainCategoryName = mainCategoryName;
        this.description = description;
    }

    public MainCategoryDto(int id, String mainCategoryName, String description) {
        this.id = id;
        this.mainCategoryName = mainCategoryName;
        this.description = description;
    }

    public MainCategoryDto() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMainCategoryName() {
        return mainCategoryName;
    }

    public void setMainCategoryName(String mainCategoryName) {
        this.mainCategoryName = mainCategoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MainCategoryDto{" +
                "id=" + id +
                ", mainCategoryName='" + mainCategoryName + '\'' +
                ", description='" + description + '\'' +
                ", createDate='" + createDate + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
