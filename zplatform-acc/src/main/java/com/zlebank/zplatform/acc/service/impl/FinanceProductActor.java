/* 
 * FinanceProductActor.java  
 * 
 * version TODO
 *
 * 2016年7月20日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.service.impl;

import com.zlebank.zplatform.acc.bean.FinanceProductBean;
import com.zlebank.zplatform.member.bean.BusinessActor;
import com.zlebank.zplatform.member.bean.enums.BusinessActorType;

/**
 * 金融产品参与者
 *
 * @author houyong
 * @version
 * @date 2016年7月20日 下午4:33:39
 * @since 
 */
public class FinanceProductActor implements BusinessActor {

    private FinanceProductBean productBean;
    /**
     *
     * @return
     */
    @Override
    public String getBusinessActorId() {
        // TODO Auto-generated method stub
        return productBean.getProductCode();
    }

    /**
     *
     * @return
     */
    @Override
    public BusinessActorType getBusinessActorType() {
        // TODO Auto-generated method stub
        return BusinessActorType.PRODUCT;
    }

    /**
     * @return the productBean
     */
    public FinanceProductBean getProductBean() {
        return productBean;
    }

    /**
     * @param productBean the productBean to set
     */
    public void setProductBean(FinanceProductBean productBean) {
        this.productBean = productBean;
    }

    /**
     * @param productBean
     */
    public FinanceProductActor(FinanceProductBean productBean) {
        super();
        this.productBean = productBean;
    }
    
    
}
