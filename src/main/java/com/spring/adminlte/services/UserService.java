package com.spring.adminlte.services;

import com.spring.adminlte.core.map.MMap;
import com.spring.adminlte.core.map.MultiMap;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService {
    MultiMap getList(MMap param);
    int save(MMap param) throws Exception;
    int delete(MMap parma);
    MMap getValueById(MMap param);
    int update(MMap param) throws Exception;
    int count();
}
