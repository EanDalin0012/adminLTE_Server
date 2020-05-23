package com.spring.adminlte.dao;
import com.spring.adminlte.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserDao {
    List<UserDto> getList(String status);
    int save(UserDto userDto);
    int delete(UserDto userDto);
    UserDto getValueById(int id);
    int update(UserDto userDto);
    int count();
}
