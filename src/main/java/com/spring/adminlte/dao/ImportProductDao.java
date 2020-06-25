package com.spring.adminlte.dao;

import com.spring.adminlte.core.map.MMap;
import com.spring.adminlte.core.map.MultiMap;
import com.spring.adminlte.dto.ImportProductDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImportProductDao {
    MultiMap getList(MMap param);
    Long save(MMap param);
    Long delete(MMap param);
    MMap getValueById(MMap param);
    Long update(MMap param);
    int count();
}
