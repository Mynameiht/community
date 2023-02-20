package com.newcoder.community.dao.impl;

import com.newcoder.community.dao.AlphaDao;
import org.springframework.stereotype.Repository;

@Repository("hibernate")
public class AlphaDaoHibernateImpl implements AlphaDao {
    @Override
    public String select() {
        return "Hibernate";
    }

    @Override
    public int selectRow() {
        return 0;
    }
}
