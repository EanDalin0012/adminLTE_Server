package com.spring.adminlte.dao;

import com.spring.adminlte.mmap.MMap;
import com.spring.adminlte.mmap.MultiMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainCategoryDao {
    List<MMap> getList(String status);
    MultiMap getListA();
    Long save(MMap mMap);
    Long delete(MMap param);
    MMap getValueById(MMap param);
    Long update(MMap param);
    int count();
}
