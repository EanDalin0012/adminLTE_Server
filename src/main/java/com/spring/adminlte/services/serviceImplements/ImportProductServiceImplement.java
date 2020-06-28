package com.spring.adminlte.services.serviceImplements;

import com.spring.adminlte.core.map.MMap;
import com.spring.adminlte.core.map.MultiMap;
import com.spring.adminlte.dao.ImportProductDao;
import com.spring.adminlte.services.ImportProductService;
import com.spring.adminlte.utils.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImportProductServiceImplement implements ImportProductService {
    @Autowired
    private ImportProductDao importProductDao;

    @Override
    public MultiMap getList(MMap param) {
        return importProductDao.getList(param);
    }

    @Override
    public Long save(MMap param) throws Exception {
        ValidatorUtil.validate(param, "productId", "supplierId", "companyId","quantity","price","total","discount", "currencyCode");
        return importProductDao.save(param);
    }

    @Override
    public Long delete(MMap param) {
        return importProductDao.delete(param);
    }

    @Override
    public MMap getValueById(MMap param) {
        return importProductDao.getValueById(param);
    }

    @Override
    public Long update(MMap param) throws Exception {
        ValidatorUtil.validate(param, "id","productId", "supplierId", "companyId","quantity","price","total","discount", "currencyCode");
        return importProductDao.update(param);
    }

    @Override
    public int count() {
        return importProductDao.count();
    }
}
