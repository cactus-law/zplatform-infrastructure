/* 
 * RequestType.java  
 * 
 * version 1.0
 *
 * 2015年9月10日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification;


/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月10日 上午9:16:46
 * @since
 */
public enum RequestType {
    /**商户签到**/
    SIGN("SMA101"),
    /**二级商户开户**/
    SYCMINORMERCH("SME101"), 
    /**商户同步查询**/
    SYCMERCHQUERY("SME102"),
    /**商户同步结果通知**/
    SYCMERCHNOTIFY("AME103"),
    /**个人会员同步**/
    SYCINDIVIDUAL("SIN101"),
    /**个人会员同步查询**/
    SYCINDIVIDUALQUERY("SIN102"),
    /**个人会员同步结果通知**/
    SYCINDIVIDUALNOTIFY("AIN103"),
    /**账户收支明细查询**/
    QUERY_TRADE_DETAIL("SAC103"),
    /**会员账户查询**/
    QUERY_MEMBER_ACCOUNT("SAC101"),
    /**通过业务账户查询账户**/
    QUERY_ACCOUNT("SAC102"),
    /**银行行号查询**/
    QUERY_BANK_NO("SPA101"),
    /**未知代码**/
    UNKNOW("FFFFFF");
    private String code;
    private RequestType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    
    public static RequestType formatValue(String code){
        for(RequestType requestType:values()){
            if(requestType.getCode().equals(code)){
                return requestType;
            }
        }
        return UNKNOW;
    }
}
