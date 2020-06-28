package com.spring.adminlte.services.serviceImplements;

import com.spring.adminlte.dao.ProductDao;
import com.spring.adminlte.core.map.MMap;
import com.spring.adminlte.core.map.MultiMap;
import com.spring.adminlte.services.ProductService;
import com.spring.adminlte.utils.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImplement implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Override
    public MultiMap getList() {
        return productDao.getList();
    }

    @Override
    public Long save(MMap param) throws Exception {
        ValidatorUtil.validate(param, "proName", "subCateId", "resourceFileInfoId", "status", "userID");
        return productDao.save(param);
    }

    @Override
    public Long delete(MMap param) throws Exception {
        ValidatorUtil.validate(param, "");
        return productDao.delete(param);
    }

    @Override
    public MMap getProductValueById(int id) {
        return productDao.getProductValueById(id);
    }

    @Override
    public Long update(MMap param) throws Exception {
        ValidatorUtil.validate(param, "proId", "proName", "subCateId", "resourceFileInfoId", "status", "userID");
        return productDao.update(param);
    }

    @Override
    public int count() {
        return productDao.count();
    }
}
