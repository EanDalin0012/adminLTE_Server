package com.spring.adminlte.dao;

import com.spring.adminlte.core.map.MMap;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubCategoryDao {
    Long save(MMap param);
    Long saveDetail(MMap param);
    Long updateDetail(MMap param);
    Long delete(MMap param);

    MMap getValueById(MMap param);
    Long update(MMap param);
    int count();
}
