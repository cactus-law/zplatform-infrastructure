/* 
 * MemberImpl.java  
 * 
 * version 1.0
 *
 * 2015年9月1日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.mock;

import com.zlebank.zplatform.member.bean.Merchant;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月1日 上午9:17:07
 * @since 
 */
public class MemberMock extends Merchant{

    
    @Override
    public String getMemberId() { 
        return "12345678901";
    }
    @Override
    public String getMerchName() { 
        return "开户测试商户";
    }

    
    @Override
    public String getMerchNo() { 
        return "254121547415214";
    }

     

}
