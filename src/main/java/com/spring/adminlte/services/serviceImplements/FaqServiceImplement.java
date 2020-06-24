package com.spring.adminlte.services.serviceImplements;

import com.spring.adminlte.core.map.MMap;
import com.spring.adminlte.core.map.MultiMap;
import com.spring.adminlte.dao.FaqDao;
import com.spring.adminlte.services.FaqService;
import com.spring.adminlte.utils.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FaqServiceImplement implements FaqService {

    @Autowired
    private FaqDao faqDao;

    @Override
    public MultiMap getList(MMap param) {
        return faqDao.getList(param);
    }

    @Override
    public int save(MMap param) throws Exception {
        ValidatorUtil.validate(param, "EnTitle", "KHRTitle", "CHTitle");
        return faqDao.save(param);
    }

    @Override
    public int delete(MMap param) {
        return faqDao.delete(param);
    }

    @Override
    public MMap getValueById(MMap param) {
        return faqDao.getValueById(param);
    }

    @Override
    public int update(MMap param) throws  Exception {
        ValidatorUtil.validate(param, "id","EnTitle", "KHRTitle", "CHTitle");
        return faqDao.update(param);
    }

    @Override
    public int count() {
        return faqDao.count();
    }
}
