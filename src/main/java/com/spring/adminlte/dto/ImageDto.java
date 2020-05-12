package com.spring.adminlte.dto;

public class ImageDto {
    private int size;
    private String imgName;
    private String url;
    private String extension;

    public ImageDto(int size, String imgName, String url, String extension) {
        this.size = size;
        this.imgName = imgName;
        this.url = url;
        this.extension = extension;
    }

    public ImageDto() {
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return "ImageDto{" +
                "size=" + size +
                ", imgName='" + imgName + '\'' +
                ", url='" + url + '\'' +
                ", extension='" + extension + '\'' +
                '}';
    }
}
