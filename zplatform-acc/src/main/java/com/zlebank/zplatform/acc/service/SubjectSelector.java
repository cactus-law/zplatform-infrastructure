/* 
 * AcctSubjectMapHandler.java  
 * 
 * version 1.0
 *
 * 2015年8月21日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.service;

import com.zlebank.zplatform.acc.bean.BusiAcct;
import com.zlebank.zplatform.acc.bean.Subject;
import com.zlebank.zplatform.acc.exception.BusiAcctToSubjectMappingNullException;
import com.zlebank.zplatform.member.bean.BusinessActor;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年8月21日 下午5:53:59
 * @since
 */
public interface SubjectSelector {
    //通过 会员编号  会员类型  用途   业务账户号 得到科目
    public Subject select(BusinessActor member,BusiAcct busiAcct)
            throws BusiAcctToSubjectMappingNullException;
}
