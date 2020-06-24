package com.spring.adminlte.services.serviceImplements;

import com.spring.adminlte.core.map.MMap;
import com.spring.adminlte.core.map.MultiMap;
import com.spring.adminlte.dao.RoleDao;
import com.spring.adminlte.services.RoleService;
import com.spring.adminlte.utils.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImplement implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Override
    public MultiMap getList(MMap param) {
        return roleDao.getList(param);
    }

    @Override
    public int save(MMap param) throws Exception {
        ValidatorUtil.validate(param, "roleName");
        return roleDao.save(param);
    }

    @Override
    public int delete(MMap param) throws Exception {
        ValidatorUtil.validate(param, "id");
        return roleDao.delete(param);
    }

    @Override
    public MMap getValueById(MMap param) throws Exception {
        ValidatorUtil.validate(param, "id");
        return roleDao.getValueById(param);
    }

    @Override
    public int update(MMap param) throws Exception {
        ValidatorUtil.validate(param, "id", "roleName");
        return roleDao.update(param);
    }

    @Override
    public int count() {
        return roleDao.count();
    }
}
