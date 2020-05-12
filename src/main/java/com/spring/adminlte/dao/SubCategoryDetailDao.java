package com.spring.adminlte.dao;

import com.spring.adminlte.dto.SubCategoryDetailDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubCategoryDetailDao {
    List<SubCategoryDetailDto> getList();
}
