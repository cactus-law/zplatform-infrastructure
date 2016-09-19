/* 
 * FinanceProductServiceImpl.java  
 * 
 * version TODO
 *
 * 2016年7月20日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.service.impl;

import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.bean.BusiAcct;
import com.zlebank.zplatform.acc.bean.FinanceProductBean;
import com.zlebank.zplatform.acc.dao.FinanceProductDAO;
import com.zlebank.zplatform.acc.pojo.PojoBusiAcctSubjectMapping;
import com.zlebank.zplatform.acc.pojo.PojoFinanceProduct;
import com.zlebank.zplatform.acc.service.BusiAcctService;
import com.zlebank.zplatform.acc.service.FinanceProductService;
import com.zlebank.zplatform.acc.service.SubjectSelector;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.member.bean.BusinessActor;
import com.zlebank.zplatform.member.bean.enums.BusinessActorType;

/**
 * 金融产品
 *
 * @author houyong
 * @version
 * @date 2016年7月20日 下午4:24:21
 * @since 
 */
@Service
public class FinanceProductServiceImpl implements FinanceProductService {
    private Log log = LogFactory.getLog(FinanceProductServiceImpl.class);
    @Autowired
    private SubjectSelector subjectSelector;
    @Autowired
    private BusiAcctService busiAcctService;
    @Autowired
    private FinanceProductDAO financeProductDao;
    /**
     *
     * @param bean
     * @throws Exception 
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
    public void openProduct(FinanceProductBean bean,long userId) throws Exception {
        try {
            openProductAccout(bean, userId);
            saveProduct(bean,userId);
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            throw e;
        }
    }
    /**
     * @param bean
     * @param userId
     */
    private void saveProduct(FinanceProductBean bean, long userId) {
            PojoFinanceProduct pojoFinanceProduct=new PojoFinanceProduct();
            pojoFinanceProduct.setFinancier(bean.getFinancier());
            pojoFinanceProduct.setFundManager(bean.getFundManager());
            pojoFinanceProduct.setProductCode(bean.getProductCode());
            pojoFinanceProduct.setProductName(bean.getProductName());
            financeProductDao.saveA(pojoFinanceProduct);
    }
    private void openProductAccout(FinanceProductBean bean,long userId) throws Exception{
        BusinessActor prouductActor = new FinanceProductActor(bean);
        List<PojoBusiAcctSubjectMapping> busiAcctSubjectMappings = subjectSelector
                .getDefaultList(BusinessActorType.PRODUCT);
        BusiAcct busiAcct;
        for (PojoBusiAcctSubjectMapping busiAcctSubjectMappin : busiAcctSubjectMappings) {
            busiAcct = new BusiAcct();
            busiAcct.setBusiAcctName(bean.getProductName());
            busiAcct.setUsage(busiAcctSubjectMappin.getUsage());
            busiAcctService.openBusiAcct(prouductActor, busiAcct,userId);
        }
    }
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
    public FinanceProductBean getProductByCode(String productCode){
    	PojoFinanceProduct produc = financeProductDao.getProductByCode(productCode);
    	if(produc!=null){
    		return BeanCopyUtil.copyBean(FinanceProductBean.class, produc);
    	}
    	return null;
    }
}
