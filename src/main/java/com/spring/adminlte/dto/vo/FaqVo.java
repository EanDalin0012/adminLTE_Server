package com.spring.adminlte.dto.vo;

import com.spring.adminlte.dto.FaqDto;

import java.util.List;

public class FaqVo {
    private List<FaqDto> list;

    public FaqVo(List<FaqDto> list) {
        this.list = list;
    }

    public FaqVo() {
    }

    public List<FaqDto> getList() {
        return list;
    }

    public void setList(List<FaqDto> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "FaqVo{" +
                "list=" + list +
                '}';
    }
}
