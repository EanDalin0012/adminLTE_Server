package com.spring.adminlte.dao;

import com.spring.adminlte.core.map.MMap;
import com.spring.adminlte.core.map.MultiMap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.tomcat.jni.Mmap;

import java.util.LinkedHashMap;
import java.util.List;

@Mapper
public interface SubCateDetailsDao {
    List<LinkedHashMap<String, Object>> getSubCateDetails();
    List<MMap> retrieveSubCategoryDetails(MMap para);
    MultiMap retrieveSubCategoryList(MMap param);
}
