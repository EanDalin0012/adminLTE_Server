package com.spring.adminlte.dao;

import com.spring.adminlte.dto.ProductDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductDao {
    List<ProductDto> getList();
    Long save(ProductDto productDto);
    Long delete(ProductDto productDto);
    ProductDto getProductValueById(int id);
    Long update(ProductDto productDto);
    int count();
}
