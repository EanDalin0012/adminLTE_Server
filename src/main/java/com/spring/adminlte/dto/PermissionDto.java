package com.spring.adminlte.dto;

public class PermissionDto extends CommonDto {
    private int id;
    private String permissionName;

    public PermissionDto(int createBy, int modifyBy, String createDate, String modifyDate, String status, int id, String permissionName) {
        super(createBy, modifyBy, createDate, modifyDate, status);
        this.id = id;
        this.permissionName = permissionName;
    }

    public PermissionDto(int id, String permissionName) {
        this.id = id;
        this.permissionName = permissionName;
    }

    public PermissionDto() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    @Override
    public String toString() {
        return "PermissionDto{" +
                "id=" + id +
                ", permissionName='" + permissionName + '\'' +
                ", createDate='" + createDate + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
