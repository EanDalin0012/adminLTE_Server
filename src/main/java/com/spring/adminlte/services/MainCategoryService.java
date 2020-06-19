package com.spring.adminlte.services;

import com.spring.adminlte.dto.MainCategoryDto;
import com.spring.adminlte.mmap.MMap;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MainCategoryService {
    Long save(MMap mMap) throws Exception;
    List<MMap> getList(String status);
    Long delete(MainCategoryDto mainCategoryDto);
    MainCategoryDto getValueById(int id);
    Long update(MMap param) throws Exception;
    int count();
}
