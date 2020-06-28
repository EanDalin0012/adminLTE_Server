package com.spring.adminlte.dao;

import com.spring.adminlte.core.map.MMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SupplierDao {
    List<MMap> getList(MMap param);
    Long save(MMap param);
    Long delete(MMap param);
    MMap getValueById(MMap param);
    Long update(MMap param);
    int count();
}
