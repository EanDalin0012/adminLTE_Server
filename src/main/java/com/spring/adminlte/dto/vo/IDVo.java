package com.spring.adminlte.dto.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.adminlte.dto.HeaderDto;
import com.spring.adminlte.dto.IdDto;

import java.util.List;

public class IDVo {
    private List<IdDto> list;

    public IDVo(List<IdDto> list) {
        this.list = list;
    }

    public IDVo() {
    }

    public List<IdDto> getList() {
        return list;
    }

    public void setList(List<IdDto> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ListIDDto{" +
                "list=" + list +
                '}';
    }
}
