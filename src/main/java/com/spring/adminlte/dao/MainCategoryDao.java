package com.spring.adminlte.dao;

import com.spring.adminlte.dto.CompanyDto;
import com.spring.adminlte.dto.MainCategoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainCategoryDao {
    List<MainCategoryDto> getList(String status);
    Long save(MainCategoryDto mainCategoryDto);
    Long delete(MainCategoryDto mainCategoryDto);
    MainCategoryDto getValueById(int id);
    Long update(MainCategoryDto mainCategoryDto);
    int count();
}
