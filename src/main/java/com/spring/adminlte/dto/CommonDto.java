package com.spring.adminlte.dto;

public class CommonDto {

    private int createBy;
    private int modifyBy;
    String createDate;
    String modifyDate;
    String status;

    public CommonDto(int createBy, int modifyBy, String createDate, String modifyDate, String status) {
        this.createBy = createBy;
        this.modifyBy = modifyBy;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.status = status;
    }

    public CommonDto() {}

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public int getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(int modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CommonDto{" +
                "createBy=" + createBy +
                ", modifyBy=" + modifyBy +
                ", createDate='" + createDate + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
