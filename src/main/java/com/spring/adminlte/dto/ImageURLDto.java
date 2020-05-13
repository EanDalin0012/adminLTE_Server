package com.spring.adminlte.dto;

public class ImageURLDto {
    private String imageURL;

    public ImageURLDto(String imageURL) {
        this.imageURL = imageURL;
    }

    public ImageURLDto() {
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
                "imageURL='" + imageURL + '\'' +
                '}';
    }
}
