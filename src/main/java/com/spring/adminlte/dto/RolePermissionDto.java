package com.spring.adminlte.dto;

public class RolePermissionDto extends CommonDto {
    private int id;
    private int roleId;
    private int permissionId;

    public RolePermissionDto(int createBy, int modifyBy, String createDate, String modifyDate, String status, int id, int roleId, int permissionId) {
        super(createBy, modifyBy, createDate, modifyDate, status);
        this.id = id;
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    public RolePermissionDto(int id, int roleId, int permissionId) {
        this.id = id;
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return "RolePermissionDto{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", permissionId=" + permissionId +
                ", createDate='" + createDate + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
