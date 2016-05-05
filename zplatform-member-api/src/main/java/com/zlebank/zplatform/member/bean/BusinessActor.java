/* 
 * Customer.java  
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
 * 业务参与者（个人/企业 会员、合作机构、通道）
 *
 * @author yangying
 * @version
 * @date 2015年8月20日 下午3:49:43
 * @since
 */
public interface BusinessActor {
    public String getBusinessActorId();
    public BusinessActorType getBusinessActorType();  
}
