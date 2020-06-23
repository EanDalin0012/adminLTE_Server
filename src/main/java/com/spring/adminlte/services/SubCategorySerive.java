package com.spring.adminlte.services;

import com.spring.adminlte.core.map.MMap;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategorySerive {
    Long save(MMap param) throws Exception;
    Long delete(MMap param) throws  Exception;
    MMap getValueById(MMap param);
    Long update(MMap param) throws Exception;
    int count();
}
