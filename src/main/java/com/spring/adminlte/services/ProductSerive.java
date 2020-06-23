package com.spring.adminlte.services;

import com.spring.adminlte.core.map.MMap;
import com.spring.adminlte.core.map.MultiMap;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSerive {
    MultiMap getList();
    Long save(MMap param) throws Exception;
    Long delete(MMap param) throws Exception;
    MMap getProductValueById(int id);
    Long update(MMap param) throws Exception;
    int count();
}
