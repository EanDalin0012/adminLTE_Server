package com.spring.adminlte.dto.vo;

import com.spring.adminlte.dto.ImportProductDto;

import java.util.List;

public class ImportProductListDto {
    private List<ImportProductDto> list;

    public ImportProductListDto(List<ImportProductDto> list) {
        this.list = list;
    }

    public ImportProductListDto() {}

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
