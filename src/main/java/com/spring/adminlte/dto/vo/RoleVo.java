package com.spring.adminlte.dto.vo;

import com.spring.adminlte.dto.RoleDto;

import java.util.List;

public class RoleVo {
    private List<RoleDto> list;

    public RoleVo(List<RoleDto> list) {
        this.list = list;
    }

    public RoleVo() {
    }

    public List<RoleDto> getList() {
        return list;
    }

    public void setList(List<RoleDto> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "RoleVo{" +
                "list=" + list +
                '}';
    }
}
