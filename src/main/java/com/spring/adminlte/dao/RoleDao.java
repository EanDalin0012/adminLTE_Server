package com.spring.adminlte.dao;

import com.spring.adminlte.core.map.MMap;
import com.spring.adminlte.core.map.MultiMap;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleDao {
    MultiMap getList(MMap param);
    int save(MMap param);
    int delete(MMap param);
    MMap getValueById(MMap param);
    int update(MMap param);
    int count();
}
