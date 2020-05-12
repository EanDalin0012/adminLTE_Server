package com.spring.adminlte.services.serviceImplements;

import com.spring.adminlte.dao.ImportProductDao;
import com.spring.adminlte.dto.ImportProductDto;
import com.spring.adminlte.services.ImportProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportProductServiceImplement implements ImportProductService {
    @Autowired
    private ImportProductDao importProductDao;

    @Override
    public List<ImportProductDto> getList(String status) {
        return importProductDao.getList(status);
    }

    @Override
    public Long save(ImportProductDto importProductDto) {
        return importProductDao.save(importProductDto);
    }

    @Override
    public Long delete(ImportProductDto importProductDto) {
        return importProductDao.delete(importProductDto);
    }

    @Override
    public ImportProductDto getValueById(int id) {
        return importProductDao.getValueById(id);
    }

    @Override
    public Long update(ImportProductDto importProductDto) {
        return importProductDao.update(importProductDto);
    }

    @Override
    public int count() {
        return importProductDao.count();
    }
}
