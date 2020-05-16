package com.spring.adminlte.services;

import com.spring.adminlte.dto.ResourceFileInfoDto;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceFileInfoService {
    public long getLastId();
    public int addCompanyProfile(ResourceFileInfoDto user);
    public int updateCompanyProfile(ResourceFileInfoDto user);
    public ResourceFileInfoDto getResourceById(String resID);
    public int deleteById(String resourceFileInfoId);
}
