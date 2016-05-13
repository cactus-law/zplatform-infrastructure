/* 
 * BusinessServiec.java  
 * 
 * version TODO
 *
 * 2015年10月14日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.service;

import java.util.List;

import com.zlebank.zplatform.acc.bean.Business;
import com.zlebank.zplatform.acc.bean.enums.BusiType;


/**
 * 交易类型Serviece
 *
 * @author yangpeng
 * @version
 * @date 2015年10月14日 下午2:45:34
 * @since 
 */
public interface BusinessServiec {
    /**
     * 得到所有交易类型
     * @return
     */
    public List<Business> getAllBusiness(); 
    
    /**
     * 根据交易类型得到数据
     * @return
     */
    public List<Business> getBusinessByType(BusiType busiType);
    
    
    
    
    
}
