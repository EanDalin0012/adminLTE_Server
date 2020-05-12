package com.spring.adminlte.services;

import com.spring.adminlte.dto.CompanyDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Companyservice {
    List<CompanyDto> getList(String status);
    Long save(CompanyDto companyDTO);
    Long delete(CompanyDto companyDto);
    CompanyDto getValueById(int id);
    Long update(CompanyDto companyDTO);
    int count();
}
