/* 
 * MemberUtil.java  
 * 
 * version TODO
 *
 * 2015年9月11日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.util;


/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月11日 下午4:05:15
 * @since
 */
public class MemberUtil {
    private final static int NUMBER = 15;

    public static String getMemberID(String head, String tail) {
        StringBuffer sb = new StringBuffer();
        sb.append(head);
        int suffixLen = NUMBER - head.length();
        String suffix = String.format("%1$0" + suffixLen + "d",
                Long.parseLong(tail));
        sb.append(suffix);
        return sb.toString();
    } 
}
