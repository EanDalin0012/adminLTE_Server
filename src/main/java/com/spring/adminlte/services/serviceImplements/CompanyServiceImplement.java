package com.spring.adminlte.services.serviceImplements;

import com.spring.adminlte.core.map.MMap;
import com.spring.adminlte.core.map.MultiMap;
import com.spring.adminlte.dao.CompanyDao;
import com.spring.adminlte.services.CompanyService;
import com.spring.adminlte.utils.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImplement implements CompanyService {
    @Autowired
    private CompanyDao companyDao;

    @Override
    public MultiMap getList(MMap param) {
        return companyDao.getList(param);
    }

    @Override
    public Long save(MMap param) throws Exception {
        ValidatorUtil.validate(param, "", "");
        return companyDao.save(param);
    }

    @Override
    public Long delete(MMap param) {
        return companyDao.delete(param);
    }

    @Override
    public MMap getValueById(MMap param) {
        return companyDao.getValueById(param);
    }

    @Override
    public Long update(MMap param) throws Exception {
        ValidatorUtil.validate(param, "", "");
        return companyDao.update(param);
    }

    @Override
    public int count() {
        return companyDao.count();
    }
}
