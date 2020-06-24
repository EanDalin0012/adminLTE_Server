package com.spring.adminlte.services;

import com.spring.adminlte.core.map.MMap;
import com.spring.adminlte.core.map.MultiMap;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleService {
    MultiMap getList(MMap para);
    int save(MMap param) throws Exception;
    int delete(MMap param) throws Exception;
    MMap getValueById(MMap param) throws Exception;
    int update(MMap param) throws Exception;
    int count();
}
