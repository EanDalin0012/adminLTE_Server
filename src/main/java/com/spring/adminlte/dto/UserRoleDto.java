package com.spring.adminlte.dto;

public class UserRoleDto extends CommonDto {
    private int id;
    private int roleId;
    private int userId;

    public UserRoleDto(int createBy, int modifyBy, String createDate, String modifyDate, String status, int id, int roleId, int userId) {
        super(createBy, modifyBy, createDate, modifyDate, status);
        this.id = id;
        this.roleId = roleId;
        this.userId = userId;
    }

    public UserRoleDto(int id, int roleId, int userId) {
        this.id = id;
        this.roleId = roleId;
        this.userId = userId;
    }

    public UserRoleDto() {}

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserRoleDto{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", userId=" + userId +
                ", createDate='" + createDate + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
