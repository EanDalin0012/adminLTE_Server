package com.spring.adminlte.services.serviceImplements;

import com.spring.adminlte.dao.CompanyDao;
import com.spring.adminlte.dto.CompanyDto;
import com.spring.adminlte.services.Companyservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImplement implements Companyservice {
    @Autowired
    private CompanyDao companyDao;

    @Override
    public List<CompanyDto> getList(String status) {
        return companyDao.getList(status);
    }

    @Override
    public Long save(CompanyDto companyDTO) {
        return companyDao.save(companyDTO);
    }

    @Override
    public Long delete(CompanyDto companyDto) {
        return companyDao.delete(companyDto);
    }

    @Override
    public CompanyDto getValueById(int id) {
        return companyDao.getValueById(id);
    }

    @Override
    public Long update(CompanyDto companyDTO) {
        return companyDao.update(companyDTO);
    }

    @Override
    public int count() {
        return companyDao.count();
    }
}
