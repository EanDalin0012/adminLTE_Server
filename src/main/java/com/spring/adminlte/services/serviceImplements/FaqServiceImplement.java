package com.spring.adminlte.services.serviceImplements;

import com.spring.adminlte.dao.FaqDao;
import com.spring.adminlte.dto.FaqDto;
import com.spring.adminlte.services.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FaqServiceImplement implements FaqService {

    @Autowired
    private FaqDao faqDao;

    @Override
    public List<FaqDto> getList(String status) {
        return faqDao.getList(status);
    }

    @Override
    public int save(FaqDto faqDto) {
        return faqDao.save(faqDto);
    }

    @Override
    public int delete(FaqDto faqDto) {
        return faqDao.delete(faqDto);
    }

    @Override
    public FaqDto getValueById(int id) {
        return faqDao.getValueById(id);
    }

    @Override
    public int update(FaqDto faqDto) {
        return faqDao.update(faqDto);
    }

    @Override
    public int count() {
        return faqDao.count();
    }
}
