package com.spring.adminlte.dto;

public class FaqDto extends CommonDto {
    private int id;
    private String EnTitle;
    private String KHRTitle;
    private String CHTitle;

    private String EnText;
    private String KHRText;
    private String CHText;

    public FaqDto(int createBy, int modifyBy, String createDate, String modifyDate, String status, int id, String enTitle, String KHRTitle, String CHTitle, String enText, String KHRText, String CHText) {
        super(createBy, modifyBy, createDate, modifyDate, status);
        this.id = id;
        EnTitle = enTitle;
        this.KHRTitle = KHRTitle;
        this.CHTitle = CHTitle;
        EnText = enText;
        this.KHRText = KHRText;
        this.CHText = CHText;
    }

    public FaqDto(int id, String enTitle, String KHRTitle, String CHTitle, String enText, String KHRText, String CHText) {
        this.id = id;
        EnTitle = enTitle;
        this.KHRTitle = KHRTitle;
        this.CHTitle = CHTitle;
        EnText = enText;
        this.KHRText = KHRText;
        this.CHText = CHText;
    }

    public FaqDto(int createBy, int modifyBy, String createDate, String modifyDate, String status) {
        super(createBy, modifyBy, createDate, modifyDate, status);
    }

    public FaqDto() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnTitle() {
        return EnTitle;
    }

    public void setEnTitle(String enTitle) {
        EnTitle = enTitle;
    }

    public String getKHRTitle() {
        return KHRTitle;
    }

    public void setKHRTitle(String KHRTitle) {
        this.KHRTitle = KHRTitle;
    }

    public String getCHTitle() {
        return CHTitle;
    }

    public void setCHTitle(String CHTitle) {
        this.CHTitle = CHTitle;
    }

    public String getEnText() {
        return EnText;
    }

    public void setEnText(String enText) {
        EnText = enText;
    }

    public String getKHRText() {
        return KHRText;
    }

    public void setKHRText(String KHRText) {
        this.KHRText = KHRText;
    }

    public String getCHText() {
        return CHText;
    }

    public void setCHText(String CHtText) {
        this.CHText = CHtText;
    }

    @Override
    public String toString() {
        return "FaqDto{" +
                "id=" + id +
                ", EnTitle='" + EnTitle + '\'' +
                ", KHRTitle='" + KHRTitle + '\'' +
                ", CHTitle='" + CHTitle + '\'' +
                ", EnText='" + EnText + '\'' +
                ", KHRText='" + KHRText + '\'' +
                ", CHtText='" + CHText + '\'' +
                ", createDate='" + createDate + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
