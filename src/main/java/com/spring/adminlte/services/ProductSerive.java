package com.spring.adminlte.services;

import com.spring.adminlte.dto.ProductDto;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductSerive {
    List<ProductDto> getList();
    Long save(ProductDto productDto);
    Long delete(ProductDto productDto);
    ProductDto getProductValueById(int id);
    Long update(ProductDto productDto);
    int count();
}
