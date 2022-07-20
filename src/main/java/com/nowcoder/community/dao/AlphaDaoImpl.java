package com.nowcoder.community.dao;


import org.springframework.stereotype.Repository;

@Repository("alphaDaoImpl")
public class AlphaDaoImpl implements AlphaDao{
    @Override
    public String select() {
        return "AlphaDao";
    }
}
