package com.spring.adminlte.dao;

import com.spring.adminlte.core.map.MMap;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResourceFileInfoDao {
    long getLastId();
    int fileUpload(MMap param);
    MMap getResourceById(MMap param);

    MMap getResource(MMap param);

    int deleteById(MMap param);


}
