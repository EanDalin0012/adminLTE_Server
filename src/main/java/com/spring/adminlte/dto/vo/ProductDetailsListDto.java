package com.spring.adminlte.dto.vo;

import com.spring.adminlte.dto.ProductDetailsDto;

import java.util.List;

public class ProductDetailsListDto {
    private List<ProductDetailsDto> list;

    public ProductDetailsListDto(List<ProductDetailsDto> list) {
        this.list = list;
    }

    public ProductDetailsListDto() {
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
