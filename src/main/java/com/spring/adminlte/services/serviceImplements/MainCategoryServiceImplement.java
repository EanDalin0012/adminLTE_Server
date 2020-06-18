package com.spring.adminlte.services.serviceImplements;

import com.spring.adminlte.dao.MainCategoryDao;
import com.spring.adminlte.dto.MainCategoryDto;
import com.spring.adminlte.mmap.MMap;
import com.spring.adminlte.services.MainCategoryService;
import com.spring.adminlte.utils.ValidatorUtil;
import org.apache.tomcat.jni.Mmap;
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
    public Long save(MMap param) throws Exception {
        try {
            ValidatorUtil.validate(param, "mainCategoryName", "userID", "status");
            return mainCategoryDao.save(param);
        }catch (Exception e) {
            throw e;
        }
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
