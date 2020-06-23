package com.spring.adminlte.dao;

import com.spring.adminlte.core.map.MMap;
import com.spring.adminlte.core.map.MultiMap;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductDao {
    MultiMap getList();
    Long save(MMap param);
    Long delete(MMap param);
    MMap getProductValueById(int id);
    Long update(MMap param);
    int count();
}
