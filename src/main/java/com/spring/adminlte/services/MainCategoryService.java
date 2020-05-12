package com.spring.adminlte.services;

import com.spring.adminlte.dto.MainCategoryDto;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MainCategoryService {
    List<MainCategoryDto> getList(String status);
    Long save(MainCategoryDto mainCategoryDto);
    Long delete(MainCategoryDto mainCategoryDto);
    MainCategoryDto getValueById(int id);
    Long update(MainCategoryDto mainCategoryDto);
    int count();
}
