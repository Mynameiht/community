package com.newcoder.community.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;
import sun.security.provider.MD5;

import java.util.UUID;

public class CommunityUtil {

    //生成随机字符串
    public static String generateUUID(){

        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        return uuid;
    }

    public static String md5(String key){
        if(StringUtils.isBlank(key)){
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }
}
