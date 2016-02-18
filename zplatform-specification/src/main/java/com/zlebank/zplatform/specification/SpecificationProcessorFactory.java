/* 
 * SpecificationProcessorFactory.java  
 * 
 * version 1.0
 *
 * 2015年9月11日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification;

import org.springframework.stereotype.Service;

import com.zlebank.zplatform.specification.service.impl.SpringApplicationObjectSupport;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月11日 上午10:01:51
 * @since
 */
@Service
public class SpecificationProcessorFactory extends SpringApplicationObjectSupport {   

    public SpecificationProcessor getProcessor(RequestType requestType) {
        fetchApplicationContext();
        switch (requestType) {
            case SIGN :
                return (SpecificationProcessor) applicationContext
                        .getBean("sign");
            case SYCINDIVIDUAL : 
                return (SpecificationProcessor) applicationContext
                        .getBean("openPerson");
            case QUERY_MEMBER_ACCOUNT : 
                return (SpecificationProcessor) applicationContext
                        .getBean("queryMemberAccount"); 

            case SYCINDIVIDUALQUERY : 
                return (SpecificationProcessor) applicationContext
                        .getBean("queryPerson");

            case SYCMERCHNOTIFY :
              //TODO
                return null;
            case SYCMERCHQUERY :
                return (SpecificationProcessor) applicationContext
                        .getBean("queryMerchant");
            case SYCMINORMERCH :
                return (SpecificationProcessor) applicationContext
                        .getBean("openMerchant");
            case QUERY_ACCOUNT : 
                return (SpecificationProcessor) applicationContext
                        .getBean("queryOneAccount"); 
            case QUERY_TRADE_DETAIL:
                return (SpecificationProcessor) applicationContext
                        .getBean("queryTradeDetail"); 
            case QUERY_BANK_NO:
                return (SpecificationProcessor) applicationContext
                        .getBean("queryBankInfo"); 
            case UNKNOW :
              //TODO
                return null;
            default :
                return null;

        }
    }

}
