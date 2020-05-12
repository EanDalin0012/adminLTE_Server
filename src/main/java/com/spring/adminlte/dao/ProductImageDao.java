package com.spring.adminlte.dao;

import com.spring.adminlte.dto.ProductImageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductImageDao {
    List<ProductImageDto> getList(String status);
    Long save(ProductImageDto productImageDto);
    Long delete(ProductImageDto productImageDto);
    ProductImageDto getValueById(int id);
    Long update(ProductImageDto productImageDto);
    String getPathImage(int pro_id);
    int count();
}
