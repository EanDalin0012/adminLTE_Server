package com.spring.adminlte.services;

import com.spring.adminlte.core.map.MMap;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceFileInfoService {
     long getLastId();
     int fileUpload(MMap param);
     MMap getResourceById(MMap param);
     int deleteById(MMap param);
}
