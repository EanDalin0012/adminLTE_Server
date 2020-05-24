package com.spring.adminlte.dao;

import com.spring.adminlte.dto.FaqDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface FaqDao {
    List<FaqDto> getList(String status);
    int save(FaqDto faqDto);
    int delete(FaqDto faqDto);
    FaqDto getValueById(int id);
    int update(FaqDto faqDto);
    int count();
}
