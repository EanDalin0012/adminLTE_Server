package com.spring.adminlte.dto.vo;

import com.spring.adminlte.dto.UserDto;

import java.util.List;

public class UserVo {
    private List<UserDto> list;

    public UserVo(List<UserDto> list) {
        this.list = list;
    }

    public UserVo() {
    }

    public List<UserDto> getList() {
        return list;
    }

    public void setList(List<UserDto> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "list=" + list +
                '}';
    }
}
