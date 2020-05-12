package com.spring.adminlte.dto;

public class ProductImageDto extends CommonDto {
    private int id;
    private int productId;
    private String uri;
    private String imageName;
    private String size;
    private String extension;

    public ProductImageDto(int createBy, int modifyBy, String createDate, String modifyDate, String status, int id, int productId, String uri, String imageName, String size, String extension) {
        super(createBy, modifyBy, createDate, modifyDate, status);
        this.id = id;
        this.productId = productId;
        this.uri = uri;
        this.imageName = imageName;
        this.size = size;
        this.extension = extension;
    }

    public ProductImageDto(int id, int productId, String uri, String imageName, String size, String extension) {
        this.id = id;
        this.productId = productId;
        this.uri = uri;
        this.imageName = imageName;
        this.size = size;
        this.extension = extension;
    }

    public ProductImageDto(int createBy, int modifyBy, String createDate, String modifyDate, String status) {
        super(createBy, modifyBy, createDate, modifyDate, status);
    }

    public ProductImageDto() {}

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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return "ProductImage{" +
                "id=" + id +
                ", productId=" + productId +
                ", uri='" + uri + '\'' +
                ", imageName='" + imageName + '\'' +
                ", size='" + size + '\'' +
                ", extension='" + extension + '\'' +
                ", createDate='" + createDate + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
