package com.spring.adminlte.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SequenceDao {
    int getSequenceMainCategory();
    int getSequenceSubCategory();
}
