package com.zlebank.zplatform.acc.bean;

/**
 * 
 * 业务账户信息查询
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月19日 下午3:07:06
 * @since
 */
public class QueryBusiCodeInfo {
    /**会计账户Id**/
    private long acctId;
    /**科目号**/
    private String busiCode;

    public long getAcctId() {
        return acctId;
    }
    public void setAcctId(long acctId) {
        this.acctId = acctId;
    }
    public String getBusiCode() {
        return busiCode;
    }
    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }
    
}
