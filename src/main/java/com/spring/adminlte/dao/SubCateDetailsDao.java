package com.spring.adminlte.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.LinkedHashMap;
import java.util.List;

@Mapper
public interface SubCateDetailsDao {
    List<LinkedHashMap<String, Object>> getSubCateDetails();
}
