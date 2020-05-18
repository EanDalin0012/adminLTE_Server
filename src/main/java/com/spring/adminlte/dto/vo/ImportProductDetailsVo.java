package com.spring.adminlte.dto.vo;

import com.spring.adminlte.dto.ImportProductDetailsDto;

import java.util.List;

public class ImportProductDetailsVo {
    private List<ImportProductDetailsDto> list;

    public ImportProductDetailsVo(List<ImportProductDetailsDto> list) {
        this.list = list;
    }

    public ImportProductDetailsVo() {
    }

    public List<ImportProductDetailsDto> getList() {
        return list;
    }

    public void setList(List<ImportProductDetailsDto> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ImportProductDetailsListDto{" +
                "list=" + list +
                '}';
    }
}
