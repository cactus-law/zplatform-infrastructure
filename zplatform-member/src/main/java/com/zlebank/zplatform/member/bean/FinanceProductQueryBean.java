/* 
 * FinanceProductQueryBean.java  
 * 
 * version TODO
 *
 * 2016年8月24日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.bean;

import java.io.Serializable;

/**
 * Class Description
 *
 * @author houyong
 * @version
 * @date 2016年8月24日 下午4:14:55
 * @since 
 */
public class FinanceProductQueryBean implements Serializable{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5022486403100270567L;
    /**
     * 产品代码
     */
    private String productCode;
    /**
     * @return the productCode
     */
    public String getProductCode() {
        return productCode;
    }
    /**
     * @param productCode the productCode to set
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    
    
}
