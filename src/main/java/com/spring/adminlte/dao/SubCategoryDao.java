package com.spring.adminlte.dao;

import com.spring.adminlte.dto.SubCategoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SubCategoryDao {
    List<SubCategoryDto> getList(String status);
    Long save(SubCategoryDto subCategoryDto);
    Long delete(SubCategoryDto subCategoryDto);
    SubCategoryDto getValueById(int id);
    Long update(SubCategoryDto subCategoryDto);
    int count();
}
