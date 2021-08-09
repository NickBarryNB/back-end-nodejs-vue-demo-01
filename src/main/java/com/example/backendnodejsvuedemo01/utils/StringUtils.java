package com.example.backendnodejsvuedemo01.utils;

import java.util.Random;

/**
 * @Author Nick
 * @Classname StringUtils
 * @Date 2021/8/6 14:41
 * @Description 工具类
 */
public class StringUtils {

//    生成指定长度随机字符串的方法
    public static String getRandomString(int length){
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int i1 = random.nextInt(base.length());
            sb.append(base.charAt(i1));
        }
        return sb.toString();
    }
}
