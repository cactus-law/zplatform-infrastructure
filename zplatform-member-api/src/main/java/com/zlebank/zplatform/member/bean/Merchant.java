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

import com.zlebank.zplatform.member.bean.enums.MemberType;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年8月20日 下午3:46:56
 * @since 
 */
public abstract class Merchant implements Member{ 
	public abstract String getMerchName();
	public abstract String getMerchNo();
	@Override
    public MemberType getMemberType(){
        return MemberType.MERCHANT;
    }
}
