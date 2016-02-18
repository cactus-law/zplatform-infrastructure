/* 
 * SendStatus.java  
 * 
 * version 1.0
 *
 * 2015年9月19日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.bean.enums;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月19日 下午3:37:55
 * @since
 */
public enum SendStatus {
    /**
     * 初始状态
     */
    INIT("01"),
    /**
     * 已加载任务，等待发送中
     */
    SENDING("02"),
    /**
     * 已发送，等待响应
     */
    SENDED("03"), /**
     * 响应超时
     */
    OVERTIME("04"),
    /**
     * 发送失败
     */
    FAIL("05"),
    /**
     * 完成，接收方确认结果正确
     */
    CONFIRMED("06"),
    /**
     * 完成，接收方确认结果不正确
     */
    UNCONFIRMED("07"),
    /**
     * 未知的状态
     */
    UNKNOW("FF");
    private String code;
    private SendStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    
    public static SendStatus formatValue(String code){
        for (SendStatus status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        return UNKNOW;
    }
}
