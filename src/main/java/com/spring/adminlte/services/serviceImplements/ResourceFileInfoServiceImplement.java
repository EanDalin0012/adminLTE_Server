package com.spring.adminlte.services.serviceImplements;

import com.spring.adminlte.core.map.MMap;
import com.spring.adminlte.dao.ResourceFileInfoDao;
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
    public int fileUpload(MMap param) {
        return resourceFileInfoDao.fileUpload(param);
    }

    @Override
    public MMap getResourceById(MMap param) {
        return resourceFileInfoDao.getResourceById(param);
    }

    @Override
    public int deleteById(MMap param) {
        return resourceFileInfoDao.deleteById(param);
    }
}
