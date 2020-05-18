package com.spring.adminlte.services.serviceImplements;

import com.spring.adminlte.dao.RoleDao;
import com.spring.adminlte.dto.RoleDto;
import com.spring.adminlte.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Service
public class RoleServiceImplement implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Override
    public List<RoleDto> getList(String status) {
        return roleDao.getList(status);
    }

    @Override
    public int save(RoleDto roleDto) {
        return roleDao.save(roleDto);
    }

    @Override
    public int delete(RoleDto roleDto) {
        return roleDao.delete(roleDto);
    }

    @Override
    public RoleDto getValueById(int id) {
        return roleDao.getValueById(id);
    }

    @Override
    public int update(RoleDto roleDto) {
        return roleDao.update(roleDto);
    }

    @Override
    public int count() {
        return roleDao.count();
    }
}
