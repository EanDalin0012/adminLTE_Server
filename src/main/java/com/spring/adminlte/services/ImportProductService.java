package com.spring.adminlte.services;

import com.spring.adminlte.core.map.MMap;
import com.spring.adminlte.core.map.MultiMap;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportProductService {
    MultiMap getList(MMap param);
    Long save(MMap param) throws Exception;
    Long delete(MMap param) throws Exception;
    MMap getValueById(MMap param);
    Long update(MMap param) throws Exception;
    int count();
}
