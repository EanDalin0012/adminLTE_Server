package com.spring.adminlte.services;

import com.spring.adminlte.dto.SupplierDto;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SupplierService {
    List<SupplierDto> getList(String status);
    Long save(SupplierDto supplierDto);
    Long delete(SupplierDto supplierDto);
    SupplierDto getValueById(int id);
    Long update(SupplierDto supplierDto);
    int count();
}
