package com.spring.adminlte.dto;

public class SupplierDto extends CommonDto {
    private int id;
    private String supName;
    private String supContact;
    private  String supContactTwo;
    private String supEmail;
    private String description;

    public SupplierDto(int createBy, int modifyBy, String createDate, String modifyDate, String status, int id, String supName, String supContact, String supContactTwo, String supEmail, String description) {
        super(createBy, modifyBy, createDate, modifyDate, status);
        this.id = id;
        this.supName = supName;
        this.supContact = supContact;
        this.supContactTwo = supContactTwo;
        this.supEmail = supEmail;
        this.description = description;
    }

    public SupplierDto(int id, String supName, String supContact, String supContactTwo, String supEmail, String description) {
        this.id = id;
        this.supName = supName;
        this.supContact = supContact;
        this.supContactTwo = supContactTwo;
        this.supEmail = supEmail;
        this.description = description;
    }

    public SupplierDto(int createBy, int modifyBy, String createDate, String modifyDate, String status) {
        super(createBy, modifyBy, createDate, modifyDate, status);
    }

    public SupplierDto() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public String getSupContact() {
        return supContact;
    }

    public void setSupContact(String supContact) {
        this.supContact = supContact;
    }

    public String getSupContactTwo() {
        return supContactTwo;
    }

    public void setSupContactTwo(String supContactTwo) {
        this.supContactTwo = supContactTwo;
    }

    public String getSupEmail() {
        return supEmail;
    }

    public void setSupEmail(String supEmail) {
        this.supEmail = supEmail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SupplierDto{" +
                "id=" + id +
                ", supName='" + supName + '\'' +
                ", supContact='" + supContact + '\'' +
                ", supContactTwo='" + supContactTwo + '\'' +
                ", supEmail='" + supEmail + '\'' +
                ", description='" + description + '\'' +
                ", createDate='" + createDate + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

