package com.spring.adminlte.dto;

public class RoleDto extends CommonDto {

    private int id;
    private String roleName;

    public RoleDto(int createBy, int modifyBy, String createDate, String modifyDate, String status, int id, String roleName) {
        super(createBy, modifyBy, createDate, modifyDate, status);
        this.id = id;
        this.roleName = roleName;
    }

    public RoleDto(int id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public RoleDto() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", createDate='" + createDate + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
