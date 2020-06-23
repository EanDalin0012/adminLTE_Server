package com.spring.adminlte.services.serviceImplements;

import com.spring.adminlte.dao.SubCategoryDao;
import com.spring.adminlte.core.map.MMap;
import com.spring.adminlte.services.SubCategorySerive;
import com.spring.adminlte.utils.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubCategoryServiceImplement implements SubCategorySerive {
    @Autowired
    private SubCategoryDao subCategoryDao;

    @Override
    public Long save(MMap param) throws Exception {
        ValidatorUtil.validate(param, "id", "subCategoryName", "status");
        return subCategoryDao.save(param);
    }

    @Override
    public Long delete(MMap param) throws Exception {
        ValidatorUtil.validate(param, "id", "status");
        return subCategoryDao.delete(param);
    }

    @Override
    public MMap getValueById(MMap param) {
        return subCategoryDao.getValueById(param);
    }

    @Override
    public Long update(MMap param) throws Exception {
        ValidatorUtil.validate(param, "id", "subCategoryName", "status");
        return subCategoryDao.update(param);
    }

    @Override
    public int count() {
        return subCategoryDao.count();
    }
}
