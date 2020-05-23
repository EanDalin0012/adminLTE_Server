package com.spring.adminlte.services.serviceImplements;

import com.spring.adminlte.dao.UserDao;
import com.spring.adminlte.dto.UserDto;
import com.spring.adminlte.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplement implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public List<UserDto> getList(String status) {
        return userDao.getList(status);
    }

    @Override
    public int save(UserDto userDto) {
        return userDao.save(userDto);
    }

    @Override
    public int delete(UserDto userDto) {
        return userDao.delete(userDto);
    }

    @Override
    public UserDto getValueById(int id) {
        return userDao.getValueById(id);
    }

    @Override
    public int update(UserDto userDto) {
        return userDao.update(userDto);
    }

    @Override
    public int count() {
        return userDao.count();
    }
}
