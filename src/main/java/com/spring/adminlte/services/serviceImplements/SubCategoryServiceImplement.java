package com.spring.adminlte.services.serviceImplements;

import com.spring.adminlte.dao.SubCategoryDao;
import com.spring.adminlte.dto.SubCategoryDto;
import com.spring.adminlte.services.SubCategorySerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SubCategoryServiceImplement implements SubCategorySerive {
    @Autowired
    private SubCategoryDao subCategoryDao;

    @Override
    public List<SubCategoryDto> getList(String status) {
        return subCategoryDao.getList(status);
    }

    @Override
    public Long save(SubCategoryDto subCategoryDto) {
        return subCategoryDao.save(subCategoryDto);
    }

    @Override
    public Long delete(SubCategoryDto subCategoryDto) {
        return subCategoryDao.delete(subCategoryDto);
    }

    @Override
    public SubCategoryDto getValueById(int id) {
        return subCategoryDao.getValueById(id);
    }

    @Override
    public Long update(SubCategoryDto subCategoryDto) {
        return subCategoryDao.update(subCategoryDto);
    }

    @Override
    public int count() {
        return subCategoryDao.count();
    }
}
