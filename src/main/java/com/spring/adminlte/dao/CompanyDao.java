package com.spring.adminlte.dao;

import com.spring.adminlte.dto.CompanyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyDao {
    List<CompanyDto> getList(String status);
    Long save(CompanyDto companyDTO);
    Long delete(CompanyDto companyDto);
    CompanyDto getValueById(int id);
    Long update(CompanyDto companyDTO);
    int count();
}
