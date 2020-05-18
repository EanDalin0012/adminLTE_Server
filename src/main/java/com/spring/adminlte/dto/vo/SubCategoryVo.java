package com.spring.adminlte.dto.vo;

import com.spring.adminlte.dto.SubCategoryDto;

import java.util.List;

public class SubCategoryVo {
    private List<SubCategoryDto> list;

    public SubCategoryVo(List<SubCategoryDto> list) {
        this.list = list;
    }

    public SubCategoryVo() {
    }

    public List<SubCategoryDto> getList() {
        return list;
    }

    public void setList(List<SubCategoryDto> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "SubCategoryListDto{" +
                "list=" + list +
                '}';
    }
}
