package com.spring.adminlte.dao;

import com.spring.adminlte.dto.ProductDetailsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductDetailsDao {
    List<ProductDetailsDto> getList(String status);
}
