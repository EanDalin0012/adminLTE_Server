package com.spring.adminlte.services.serviceImplements;

import com.spring.adminlte.dao.SupplierDao;
import com.spring.adminlte.core.map.MMap;
import com.spring.adminlte.services.SupplierService;
import com.spring.adminlte.utils.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SupplierServiceImplement implements SupplierService {
    @Autowired
    private SupplierDao supplierDao;

    @Override
    public List<MMap> getList(MMap param) {
        return supplierDao.getList(param);
    }

    @Override
    public Long save(MMap param) throws Exception {
        ValidatorUtil.validate(param, "supName", "supContact","supEmail", "userID", "status");
        return supplierDao.save(param);
    }

    @Override
    public Long delete(MMap param) {
        return supplierDao.delete(param);
    }

    @Override
    public MMap getValueById(MMap param) {
        return supplierDao.getValueById(param);
    }

    @Override
    public Long update(MMap param) throws Exception {
        ValidatorUtil.validate(param, "id","supName", "supContact","supEmail", "userID", "status");
        return supplierDao.update(param);
    }

    @Override
    public int count() {
        return supplierDao.count();
    }
}
