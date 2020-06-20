package com.spring.adminlte.services.serviceImplements;

import com.spring.adminlte.dao.MainCategoryDao;
import com.spring.adminlte.mmap.MMap;
import com.spring.adminlte.services.MainCategoryService;
import com.spring.adminlte.utils.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MainCategoryServiceImplement implements MainCategoryService {
    @Autowired
    private MainCategoryDao mainCategoryDao;

    @Override
    public List<MMap> getList(String status) {
        return mainCategoryDao.getList(status);
    }

    @Override
    public Long save(MMap param) throws Exception {
        try {
            ValidatorUtil.validate(param, "id", "mainCategoryName", "userID", "status");
            return mainCategoryDao.save(param);
        }catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Long delete(MMap param) throws Exception{
        ValidatorUtil.validate(param, "id", "status");
        return mainCategoryDao.delete(param);
    }

    @Override
    public MMap getValueById(MMap param) throws Exception {
        ValidatorUtil.validate(param, "id");
        return mainCategoryDao.getValueById(param);
    }

    @Override
    public Long update(MMap param) throws Exception {
        ValidatorUtil.validate(param, "id", "mainCategoryName", "userID", "status");
        return mainCategoryDao.update(param);
    }

    @Override
    public int count() {
        return mainCategoryDao.count();
    }

}
