package com.spring.adminlte.services;

import com.spring.adminlte.dto.UserDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserService {
    List<UserDto> getList(String status);
    int save(UserDto userDto);
    int delete(UserDto userDto);
    UserDto getValueById(int id);
    int update(UserDto userDto);
    int count();
}
