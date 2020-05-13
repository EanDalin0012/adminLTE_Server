package com.spring.adminlte.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

public class ResourceFileInfoDto {
    private String id;
    private String userID;
    private String customerID;
    private String channelTypeCode;
    private String fileTypeCode;
    private String fileName;
    private String fileExt;
    private String fileContentType;
    private long fileSize;
    private byte[] fileData;
    private String createdBy;
    private String updatedBy;
    private String createdDate;
    private String updatedDate;

    private MultipartFile file;
    private String imgBaseUrl;
    private String fileImageURL;

    public ResourceFileInfoDto(String id, String userID, String customerID, String channelTypeCode, String fileTypeCode, String fileName, String fileExt, String fileContentType, long fileSize, byte[] fileData, String createdBy, String updatedBy, String createdDate, String updatedDate, MultipartFile file, String imgBaseUrl, String fileImageURL) {
        this.id = id;
        this.userID = userID;
        this.customerID = customerID;
        this.channelTypeCode = channelTypeCode;
        this.fileTypeCode = fileTypeCode;
        this.fileName = fileName;
        this.fileExt = fileExt;
        this.fileContentType = fileContentType;
        this.fileSize = fileSize;
        this.fileData = fileData;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.file = file;
        this.imgBaseUrl = imgBaseUrl;
        this.fileImageURL = fileImageURL;
    }

    public ResourceFileInfoDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getChannelTypeCode() {
        return channelTypeCode;
    }

    public void setChannelTypeCode(String channelTypeCode) {
        this.channelTypeCode = channelTypeCode;
    }

    public String getFileTypeCode() {
        return fileTypeCode;
    }

    public void setFileTypeCode(String fileTypeCode) {
        this.fileTypeCode = fileTypeCode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExt() {
        return fileExt;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getImgBaseUrl() {
        return imgBaseUrl;
    }

    public void setImgBaseUrl(String imgBaseUrl) {
        this.imgBaseUrl = imgBaseUrl;
    }

    public String getFileImageURL() {
        return fileImageURL;
    }

    public void setFileImageURL(String fileImageURL) {
        this.fileImageURL = fileImageURL;
    }

    @Override
    public String toString() {
        return "ResourceFileInfoDto{" +
                "id='" + id + '\'' +
                ", userID='" + userID + '\'' +
                ", customerID='" + customerID + '\'' +
                ", channelTypeCode='" + channelTypeCode + '\'' +
                ", fileTypeCode='" + fileTypeCode + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileExt='" + fileExt + '\'' +
                ", fileContentType='" + fileContentType + '\'' +
                ", fileSize=" + fileSize +
                ", fileData=" + Arrays.toString(fileData) +
                ", createdBy='" + createdBy + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", updatedDate='" + updatedDate + '\'' +
                ", file=" + file +
                ", imgBaseUrl='" + imgBaseUrl + '\'' +
                ", fileImageURL='" + fileImageURL + '\'' +
                '}';
    }
}
