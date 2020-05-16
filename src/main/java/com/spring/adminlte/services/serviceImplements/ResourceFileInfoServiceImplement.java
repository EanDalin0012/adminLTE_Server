package com.spring.adminlte.services.serviceImplements;

import com.spring.adminlte.dao.ResourceFileInfoDao;
import com.spring.adminlte.dto.ResourceFileInfoDto;
import com.spring.adminlte.services.ResourceFileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceFileInfoServiceImplement implements ResourceFileInfoService {
    @Autowired
    private ResourceFileInfoDao resourceFileInfoDao;

    @Override
    public long getLastId() {
        return resourceFileInfoDao.getLastId();
    }

    @Override
    public int addCompanyProfile(ResourceFileInfoDto user) {
        return resourceFileInfoDao.addCompanyProfile(user);
    }

    @Override
    public int updateCompanyProfile(ResourceFileInfoDto user) {
        return resourceFileInfoDao.updateCompanyProfile(user);
    }

    @Override
    public ResourceFileInfoDto getResourceById(String resID) {
        return resourceFileInfoDao.getResourceById(resID);
    }

    @Override
    public int deleteById(String resourceFileInfoId) {
        return resourceFileInfoDao.deleteById(resourceFileInfoId);
    }
}
