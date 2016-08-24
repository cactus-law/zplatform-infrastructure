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
    /**
     * 产品名称
     */
    private String productName;
    
    /**
     * 产品代码
     */
    private String productCode;
    
    private String fundManager;
    /**融资人*/
    private String financier;

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
    /**
     * @return the fundManager
     */
    public String getFundManager() {
        return fundManager;
    }
    /**
     * @param fundManager the fundManager to set
     */
    public void setFundManager(String fundManager) {
        this.fundManager = fundManager;
    }
    /**
     * @return the financier
     */
    public String getFinancier() {
        return financier;
    }
    /**
     * @param financier the financier to set
     */
    public void setFinancier(String financier) {
        this.financier = financier;
    }
       
}
