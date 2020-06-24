package com.spring.adminlte.services.serviceImplements;

import com.spring.adminlte.core.map.MMap;
import com.spring.adminlte.core.map.MultiMap;
import com.spring.adminlte.dao.UserDao;
import com.spring.adminlte.services.UserService;
import com.spring.adminlte.utils.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplement implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public MultiMap getList(MMap param) {
        return userDao.getList(param);
    }

    @Override
    public int save(MMap param) throws Exception{
        ValidatorUtil.validate(param, "", "");
        return userDao.save(param);
    }

    @Override
    public int delete(MMap param) {
        return userDao.delete(param);
    }

    @Override
    public MMap  getValueById(MMap param) {
        return userDao.getValueById(param);
    }

    @Override
    public int update(MMap param) throws Exception{
        ValidatorUtil.validate(param, "", "");
        return userDao.update(param);
    }

    @Override
    public int count() {
        return userDao.count();
    }
}
