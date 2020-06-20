package com.spring.adminlte.dao;

import com.spring.adminlte.mmap.MMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubCategoryDetailDao {
    List<MMap> getList(MMap param);
}
