/* 
 * Individual.java  
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
 * 个人会员
 *
 * @author yangying
 * @version
 * @date 2015年8月20日 下午3:48:29
 * @since 
 */
public abstract class Individual implements BusinessActor{
    @Override
    public BusinessActorType getBusinessActorType(){
        return BusinessActorType.INDIVIDUAL;
    }
}
