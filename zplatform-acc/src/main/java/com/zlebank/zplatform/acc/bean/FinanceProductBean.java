/* 
 * FinanceProductBean.java  
 * 
 * version TODO
 *
 * 2016年7月20日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.bean;

import java.io.Serializable;

/**
 * Class Description
 *
 * @author houyong
 * @version
 * @date 2016年7月20日 下午4:21:56
 * @since 
 */
public class FinanceProductBean implements Serializable{
    
       /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8811815001691358450L;

    private String productName;
       
       private String productCode;

    /**
     * @param productName
     * @param productCode
     */
    public FinanceProductBean(String productName, String productCode) {
        super();
        this.productName = productName;
        this.productCode = productCode;
    }
    public FinanceProductBean(){
        
    }
    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

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
