package com.spring.adminlte.dao;

import com.spring.adminlte.mmap.MMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.LinkedHashMap;
import java.util.List;

@Mapper
public interface SubCateDetailsDao {
    List<LinkedHashMap<String, Object>> getSubCateDetails();
    List<MMap> retrieveSubCategoryDetails(MMap para);
}
