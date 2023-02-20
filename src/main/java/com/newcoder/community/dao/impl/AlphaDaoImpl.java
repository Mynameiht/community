package com.newcoder.community.dao.impl;

import com.newcoder.community.dao.AlphaDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class AlphaDaoImpl implements AlphaDao {
    @Override
    public String select() {
        return "Mybatis";
    }

    @Override
    public int selectRow() {
        return 0;
    }
}
