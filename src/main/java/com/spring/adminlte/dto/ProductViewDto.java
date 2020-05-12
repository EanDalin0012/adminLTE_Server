package com.spring.adminlte.dto;

public class ProductViewDto extends CommonDto{

    private int id;
    private int productId;
    private String text;

    public ProductViewDto(int createBy, int modifyBy, String createDate, String modifyDate, String status, int id, int productId, String text) {
        super(createBy, modifyBy, createDate, modifyDate, status);
        this.id = id;
        this.productId = productId;
        this.text = text;
    }

    public ProductViewDto(int id, int productId, String text) {
        this.id = id;
        this.productId = productId;
        this.text = text;
    }

    public ProductViewDto() {}

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ProductViewDto{" +
                "id=" + id +
                ", productId=" + productId +
                ", text='" + text + '\'' +
                ", createDate='" + createDate + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
