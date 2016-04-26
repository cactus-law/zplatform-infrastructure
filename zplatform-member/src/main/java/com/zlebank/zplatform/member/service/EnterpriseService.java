/* 
 * MenchService.java  
 * 
 * version TODO
 *
 * 2015年9月11日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.service;

import com.zlebank.zplatform.member.bean.EnterpriseBean;

/**
 * 
 * 企业服务类
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年3月21日 下午2:06:21
 * @since
 */
public interface EnterpriseService { 

    
    /**
     * 根据memberId得到企业信息
     * @param memberId
     * @return
     */
   public  EnterpriseBean  getEnterpriseByMemberId(String memberId); 
}
