package com.spring.adminlte.services.serviceImplements;

import com.spring.adminlte.dao.SupplierDao;
import com.spring.adminlte.dto.SupplierDto;
import com.spring.adminlte.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SupplierServiceImplement implements SupplierService {
    @Autowired
    private SupplierDao supplierDao;

    @Override
    public List<SupplierDto> getList(String status) {
        return supplierDao.getList(status);
    }

    @Override
    public Long save(SupplierDto supplierDto) {
        return supplierDao.save(supplierDto);
    }

    @Override
    public Long delete(SupplierDto supplierDto) {
        return supplierDao.delete(supplierDto);
    }

    @Override
    public SupplierDto getValueById(int id) {
        return supplierDao.getValueById(id);
    }

    @Override
    public Long update(SupplierDto supplierDto) {
        return supplierDao.update(supplierDto);
    }

    @Override
    public int count() {
        return supplierDao.count();
    }
}
