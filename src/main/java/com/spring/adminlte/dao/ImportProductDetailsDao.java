/*
* @author ean dalin
* @date 07-05-2020
* */
package com.spring.adminlte.dao;

import com.spring.adminlte.dto.ImportProductDetailsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImportProductDetailsDao {
    List<ImportProductDetailsDto> getList(String status);
}
