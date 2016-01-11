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

import com.zlebank.zplatform.member.bean.enums.MemberType;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年8月20日 下午3:48:29
 * @since 
 */
public abstract class Individual implements Member{
    @Override
    public MemberType getMemberType(){
        return MemberType.Individual;
    }
}
