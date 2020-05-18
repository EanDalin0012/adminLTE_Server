package com.spring.adminlte.dto.vo;

import com.spring.adminlte.dto.ImportProductDto;

import java.util.List;

public class ImportProductVo {
    private List<ImportProductDto> list;

    public ImportProductVo(List<ImportProductDto> list) {
        this.list = list;
    }

    public ImportProductVo() {}

    public List<ImportProductDto> getList() {
        return list;
    }

    public void setList(List<ImportProductDto> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ImportProductList{" +
                "list=" + list +
                '}';
    }
}
