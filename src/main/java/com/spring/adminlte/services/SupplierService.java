package com.spring.adminlte.services;

import com.spring.adminlte.core.map.MMap;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SupplierService {
    List<MMap> getList(MMap param);
    Long save(MMap param) throws Exception;
    Long delete(MMap param) throws  Exception;
    MMap getValueById(MMap param);
    Long update(MMap param) throws Exception;
    int count();
}
