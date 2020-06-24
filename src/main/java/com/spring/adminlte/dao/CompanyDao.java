package com.spring.adminlte.dao;

import com.spring.adminlte.core.map.MMap;
import com.spring.adminlte.core.map.MultiMap;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyDao {
    MultiMap getList(MMap param);
    Long save(MMap param);
    Long delete(MMap param);
    MMap getValueById(MMap param);
    Long update(MMap param);
    int count();
}
