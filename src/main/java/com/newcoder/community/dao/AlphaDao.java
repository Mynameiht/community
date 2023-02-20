package com.newcoder.community.dao;

import org.apache.ibatis.annotations.Select;

public interface AlphaDao {

    String select();

    int selectRow();
}
