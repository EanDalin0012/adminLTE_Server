package com.spring.adminlte.services;

import com.spring.adminlte.mmap.MMap;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MainCategoryService {
    Long save(MMap mMap) throws Exception;
    List<MMap> getList(String status);
    Long delete(MMap param) throws Exception;
    MMap getValueById(MMap param) throws Exception;
    Long update(MMap param) throws Exception;
    int count();
}
