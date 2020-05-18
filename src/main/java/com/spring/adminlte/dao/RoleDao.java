package com.spring.adminlte.dao;

import com.spring.adminlte.dto.RoleDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface RoleDao {
    List<RoleDto> getList(String status);
    int save(RoleDto roleDto);
    int delete(RoleDto roleDto);
    RoleDto getValueById(int id);
    int update(RoleDto roleDto);
    int count();
}
