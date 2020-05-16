package com.spring.adminlte.dao;

import com.spring.adminlte.dto.ResourceFileInfoDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResourceFileInfoDao {
    public long getLastId();
    public int addCompanyProfile(ResourceFileInfoDto user);
    public int updateCompanyProfile(ResourceFileInfoDto user);
    public ResourceFileInfoDto getResourceById(String resID);
    public int deleteById(String resourceFileInfoId);
}
