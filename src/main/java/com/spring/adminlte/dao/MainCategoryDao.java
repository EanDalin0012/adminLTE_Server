package com.spring.adminlte.dao;

import com.spring.adminlte.dto.CompanyDto;
import com.spring.adminlte.dto.MainCategoryDto;
import com.spring.adminlte.mmap.MMap;
import com.spring.adminlte.mmap.MultiMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainCategoryDao {
    List<MMap> getList(String status);
    MultiMap getListA();
    Long save(MMap mMap);
    Long delete(MainCategoryDto mainCategoryDto);
    MainCategoryDto getValueById(int id);
    Long update(MMap param);
    int count();
}
