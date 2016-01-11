/* 
 * SubjectService.java  
 * 
 * version 1.0
 *
 * 2015年8月20日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.service;
import com.zlebank.zplatform.acc.bean.Subject;
import com.zlebank.zplatform.acc.bean.SubjectQuery;
import com.zlebank.zplatform.acc.exception.AbstractAccException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.commons.service.IBasePageService;


/**
 * Class Description
 * 科目service 负责科目的增删改查
 * @author yangpeng
 * @version
 * @date 2015年7月18日 上午10:46:10
 * @since 
 */
public interface SubjectService extends IBasePageService<Subject,Subject>{

    public String addSubject(Subject subject,long userId)throws AbstractAccException;
    /**
     * 修改科目
     * @param subject
     */
    public Boolean  updateSubject(Subject subject,long userId)throws AccBussinessException;
   
    /**
     * 根据ID停用科目
     * @param id
     */
    public Boolean stopSubject(Long id)throws AccBussinessException;
//    /**
//     * 根据ID启用科目
//     * @param id
//     */
//    public Boolean enableSubject(Long id);
    
    /**
     * 根据ID得到科目
     * @param id
     * @return
     */
    public SubjectQuery subjectByID(Long id)throws AccBussinessException ;
        /**
         * 通过科目代码得到科目
         * @param code
         * @return
         */
    public SubjectQuery subjectByCode(String code)throws AccBussinessException ;
    
}
