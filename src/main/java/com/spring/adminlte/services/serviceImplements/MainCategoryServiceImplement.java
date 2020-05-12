package com.spring.adminlte.services.serviceImplements;

import com.spring.adminlte.dao.MainCategoryDao;
import com.spring.adminlte.dto.MainCategoryDto;
import com.spring.adminlte.services.MainCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MainCategoryServiceImplement implements MainCategoryService {
    @Autowired
    private MainCategoryDao mainCategoryDao;

    @Override
    public List<MainCategoryDto> getList(String status) {
        return mainCategoryDao.getList(status);
    }

    @Override
    public Long save(MainCategoryDto mainCategoryDto) {
        return mainCategoryDao.save(mainCategoryDto);
    }

    @Override
    public Long delete(MainCategoryDto mainCategoryDto) {
        return mainCategoryDao.delete(mainCategoryDto);
    }

    @Override
    public MainCategoryDto getValueById(int id) {
        return mainCategoryDao.getValueById(id);
    }

    @Override
    public Long update(MainCategoryDto mainCategoryDto) {
        return mainCategoryDao.update(mainCategoryDto);
    }

    @Override
    public int count() {
        return mainCategoryDao.count();
    }
}
