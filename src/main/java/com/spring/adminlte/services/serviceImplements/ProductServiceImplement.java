package com.spring.adminlte.services.serviceImplements;

import com.spring.adminlte.dao.ProductDao;
import com.spring.adminlte.dto.ProductDto;
import com.spring.adminlte.services.ProductSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImplement implements ProductSerive {
    @Autowired
    private ProductDao productDao;
    @Override
    public List<ProductDto> getList() {
        return productDao.getList();
    }

    @Override
    public Long save(ProductDto productDto) {
        return productDao.save(productDto);
    }

    @Override
    public Long delete(ProductDto productDto) {
        return productDao.delete(productDto);
    }

    @Override
    public ProductDto getProductValueById(int id) {
        return productDao.getProductValueById(id);
    }

    @Override
    public Long update(ProductDto productDto) {
        return productDao.update(productDto);
    }

    @Override
    public int count() {
        return productDao.count();
    }
}
