package com.spring.adminlte.dao;

import com.spring.adminlte.core.map.MMap;
import com.spring.adminlte.core.map.MultiMap;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface ProductDetailsDao {
    MultiMap getList(MMap param);
}
