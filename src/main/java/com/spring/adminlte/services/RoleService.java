package com.spring.adminlte.services;

import com.spring.adminlte.dto.RoleDto;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoleService {
    List<RoleDto> getList(String status);
    int save(RoleDto roleDto);
    int delete(RoleDto roleDto);
    RoleDto getValueById(int id);
    int update(RoleDto roleDto);
    int count();
}
