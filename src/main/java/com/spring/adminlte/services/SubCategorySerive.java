package com.spring.adminlte.services;

import com.spring.adminlte.dto.SubCategoryDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategorySerive {
    List<SubCategoryDto> getList(String status);
    Long save(SubCategoryDto subCategoryDto);
    Long delete(SubCategoryDto subCategoryDto);
    SubCategoryDto getValueById(int id);
    Long update(SubCategoryDto subCategoryDto);
    int count();
}
