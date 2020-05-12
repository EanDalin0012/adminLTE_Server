package com.spring.adminlte.dao;

import com.spring.adminlte.dto.SupplierDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SupplierDao {
    List<SupplierDto> getList(String status);
    Long save(SupplierDto supplierDto);
    Long delete(SupplierDto supplierDto);
    SupplierDto getValueById(int id);
    Long update(SupplierDto supplierDto);
    int count();
}
