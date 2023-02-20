package com.newcoder.community.service.impl;

import com.newcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
@Service
public class AlphaDateImpl implements AlphaService {

    @Autowired
    private SimpleDateFormat simpleDateFormat;
    @Override
    public String alphaDate() {
        String date = simpleDateFormat.format(new Date());
        return date;
    }
}
