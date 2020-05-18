package com.spring.adminlte.dto.vo;

import com.spring.adminlte.dto.ProductDetailsDto;

import java.util.List;

public class ProductDetailsVo {
    private List<ProductDetailsDto> list;

    public ProductDetailsVo(List<ProductDetailsDto> list) {
        this.list = list;
    }

    public ProductDetailsVo() {
    }

    public List<ProductDetailsDto> getList() {
        return list;
    }

    public void setList(List<ProductDetailsDto> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ProductDetailsListDto{" +
                "list=" + list +
                '}';
    }
}
