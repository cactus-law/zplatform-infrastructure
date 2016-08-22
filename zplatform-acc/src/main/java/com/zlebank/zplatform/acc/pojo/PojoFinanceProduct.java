/* 
 * PojoFinanceProduct.java  
 * 
 * version TODO
 *
 * 2016年8月22日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.zlebank.zplatform.commons.dao.pojo.Pojo;

/**
 * Class Description
 *
 * @author houyong
 * @version
 * @date 2016年8月22日 下午2:33:59
 * @since 
 */
@Entity
@Table(name="T_FINANCE_PRODUCT")
public class PojoFinanceProduct extends Pojo {
    /**主键*/
    private long pId;
    /**产品编号*/
    private String productCode;
    /**产品名称*/
    private String productName;
    /**资管人*/
    private String fundManager;
    /**融资人*/
    private String financier;
    /**
     *
     * @return
     */
    @GenericGenerator(name = "id_gen", strategy = "enhanced-table", parameters = {
            @Parameter(name = "table_name", value = "T_C_PRIMAY_KEY"),
            @Parameter(name = "value_column_name", value = "NEXT_ID"),
            @Parameter(name = "segment_column_name", value = "KEY_NAME"),
            @Parameter(name = "segment_value", value = "T_FinanceProduct_ID"),
            @Parameter(name = "increment_size", value = "1"),
            @Parameter(name = "optimizer", value = "pooled-lo")})
    @Id
    @Column(name = "pid")
    @GeneratedValue(generator = "id_gen")
    @Override
    public long getId() {
        // TODO Auto-generated method stub
        return pId;
    }
    /**
     * @param pId the pId to set
     */
    public void setId(long id) {
        this.pId = id;
    }
    /**
     * @return the productCode
     */
    @Column(name="PRODUCT_CODE")
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
     * @return the productName
     */
    @Column(name="PRODUCT_NAME")
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
     * @return the fundManager
     */
    @Column(name="FUND_MANAGER")
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
    @Column(name="FINANCIER")
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
