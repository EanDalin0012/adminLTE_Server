package com.spring.adminlte.services;

import com.spring.adminlte.dto.FaqDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaqService {
    List<FaqDto> getList(String status);
    int save(FaqDto faqDto);
    int delete(FaqDto faqDto);
    FaqDto getValueById(int id);
    int update(FaqDto faqDto);
    int count();
}
