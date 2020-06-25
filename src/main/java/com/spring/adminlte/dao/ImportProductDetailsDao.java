/*
* @author ean dalin
* @date 07-05-2020
* */
package com.spring.adminlte.dao;

import com.spring.adminlte.core.map.MMap;
import com.spring.adminlte.core.map.MultiMap;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImportProductDetailsDao {
    MultiMap getList(MMap param);
}
