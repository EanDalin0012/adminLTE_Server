package com.spring.adminlte.dao;

import com.spring.adminlte.dto.ImportProductDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImportProductDao {
    List<ImportProductDto> getList(String status);
    Long save(ImportProductDto companyDTO);
    Long delete(ImportProductDto companyDto);
    ImportProductDto getValueById(int id);
    Long update(ImportProductDto companyDTO);
    int count();
}
