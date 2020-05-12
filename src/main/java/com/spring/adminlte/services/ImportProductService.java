package com.spring.adminlte.services;

import com.spring.adminlte.dto.ImportProductDto;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ImportProductService {
    List<ImportProductDto> getList(String status);
    Long save(ImportProductDto companyDTO);
    Long delete(ImportProductDto companyDto);
    ImportProductDto getValueById(int id);
    Long update(ImportProductDto companyDTO);
    int count();
}
