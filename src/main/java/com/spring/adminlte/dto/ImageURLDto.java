package com.spring.adminlte.dto;

public class ImageURLDto {
    private String id;
    private String imageURL;

    public ImageURLDto(String id, String imageURL) {
        this.id = id;
        this.imageURL = imageURL;
    }

    public ImageURLDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "ImageURLDto{" +
                "id='" + id + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
