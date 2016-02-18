/* 
 * Merchant.java  
 * 
 * version 1.0
 *
 * 2015年8月20日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.bean;

import com.zlebank.zplatform.member.bean.enums.BusinessActorType;

/**
 * 企业会员
 *
 * @author yangying
 * @version
 * @date 2015年8月20日 下午3:46:56
 * @since 
 */
public abstract class Enterprise implements BusinessActor{ 
	public abstract String getBusinessActorName();
	public abstract String getBusinessActorNo();
	@Override
    public BusinessActorType getBusinessActorType(){
        return BusinessActorType.ENTERPRISE;
    }
}
