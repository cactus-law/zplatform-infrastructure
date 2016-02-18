/* 
 * SubjectServiceImpl.java  
 * 
 * version 1.0
 *
 * 2015年8月20日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.service.impl;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.bean.Subject;
import com.zlebank.zplatform.acc.bean.SubjectQuery;
import com.zlebank.zplatform.acc.bean.enums.AcctStatusType;
import com.zlebank.zplatform.acc.dao.SubjectDAO;
import com.zlebank.zplatform.acc.exception.AbstractAccException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.acc.pojo.PojoSubject;
import com.zlebank.zplatform.acc.service.GetDACService;
import com.zlebank.zplatform.acc.service.SubjectService;
import com.zlebank.zplatform.commons.service.impl.AbstractBasePageService;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年8月20日 下午5:57:10
 * @since
 */
@Service
public class SubjectServiceImpl extends AbstractBasePageService<Subject,Subject>
        implements
            SubjectService {
    private final int codeLength = 2;
    private final int rootLength = 4;
    @Autowired
    private SubjectDAO subjectDAO; 
    @Autowired
    private GetDACService dacUtil;
    
    @Transactional(propagation = Propagation.REQUIRED)
    public String addSubject(Subject subject, long userId)
            throws AbstractAccException {
        try {
        if (subject.getParentSubject() == null) {
            // 一级科目
            if (subject.getAcctCode().length() != rootLength) {
                    // 长度不符合E100001
                    throw new AccBussinessException("E100001",new Object[]{String.valueOf(subject.getAcctCode())});
             
            }
                PojoSubject subjectPo = subjectDAO.subjectByCode(
                        subject.getAcctCode());
                if (subjectPo != null) {
                    // 科目号存在 
                    throw new AccBussinessException("E100004",new Object[]{String.valueOf(subject.getAcctCode())});
                }
                    PojoSubject sp = BeanCopyUtil.copyBean(PojoSubject.class,
                            subject);
                    sp.setStatus(AcctStatusType.NORMAL);
                    sp.setBalance(Money.ZERO);
                    sp.setCreditBalance(Money.ZERO);
                    sp.setDebitBalance(Money.ZERO);
                    sp.setFrozenBalance(Money.ZERO);
                    sp.setTotalBanance(Money.ZERO);
                    sp.setInTime(new Date());
                    sp.setUpTime(new Date());
                    sp.setInUser(userId);      
                    PojoSubject subjec = subjectDAO.save(sp);
                    if (subjec == null) {
                        //新增失败
                        throw new AccBussinessException("E100003");
                    }
                        // 新增成功
                        return subjec.getAcctCode();
        } else {
            // 子级科目
            String parentSubjectCode = subject.getParentSubject().getAcctCode();
            if (subject.getAcctCode().length()!= (parentSubjectCode.length() + codeLength)) {
                // code长度不合法
                throw new AccBussinessException("E100001",new Object[]{String.valueOf(subject.getAcctCode())});
            }
                if (!subject.getAcctCode()
                        .substring(0, subject.getAcctCode().length() - 2)
                        .equals(parentSubjectCode)) {
                    // code父code不等于自身code
                    throw new AccBussinessException("E100001",new Object[]{String.valueOf(subject.getAcctCode())});
                }
                    // 验证科目号是否重复
                    PojoSubject subjectPo = subjectDAO.subjectByCode(
                            subject.getAcctCode());
                    if (subjectPo != null) {
                        // 科目号重复
                        throw new AccBussinessException("E100004",new Object[]{String.valueOf(subject.getAcctCode())});
                    }
                        //通过科目得到subject
                        SubjectQuery subQuery=  this.subjectByCode(parentSubjectCode);
                        if(subQuery==null){
                            //父科目不存在
                            throw new AccBussinessException("E100013");
                        }
                        PojoSubject parentSub=BeanCopyUtil.copyBean(PojoSubject.class, subQuery);
                        PojoSubject sp = BeanCopyUtil.copyBean(
                                PojoSubject.class, subject);
                        sp.setAcctElement(subQuery.getAcctElement());
                        sp.setCrdr(subQuery.getCrdr());
                        sp.setStatus(AcctStatusType.NORMAL);
                        sp.setBalance(Money.ZERO);
                        sp.setCreditBalance(Money.ZERO);
                        sp.setDebitBalance(Money.ZERO);
                        sp.setFrozenBalance(Money.ZERO);
                        sp.setTotalBanance(Money.ZERO);
                        sp.setParentSubject(parentSub);
                        sp.setInTime(new Date());
                        sp.setUpTime(new Date());
                        sp.setInUser(userId);
                        // 新增
                        PojoSubject subjec = subjectDAO.save(sp);
                        if (subjec == null) {
                            //新增失败
                            throw new AccBussinessException("E100003");
                            } //新增成功
                            return subjec.getAcctCode();
            } } catch (Exception e) {
         throw new AccBussinessException("E100003");
        }

    }

   

    /**
     *
     * @param subject
     * @return
     * @throws AccBussinessException 
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public Boolean updateSubject(Subject subject, long userId) throws AccBussinessException {
        
        PojoSubject subjec = subjectDAO.get(subject.getId());
        if (subjec == null) {
            throw new AccBussinessException("E100006");
        }
        //验证DAC
        String oldDac=subject.getDac();
        String nDac=dacUtil.generteDAC(subjec.getAcctCode(),subjec.getBalance() , subjec.getFrozenBalance(),subjec.getTotalBanance());
       if(!oldDac.equals(nDac)){
           throw new AccBussinessException("E100005");
       }else{
            subjec.setUpTime(new Date());
            subjec.setUpUser(userId);
            subjec.setAcctCodeName(subject.getAcctCodeName());
            subjec.setCrdr(subject.getCrdr());
            return true;
        } 
      
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public Boolean stopSubject(Long id) throws AccBussinessException {
        PojoSubject subjectPojo = subjectDAO.get(id);
        if (subjectPojo == null) {
         // 科目不存在 
            throw new AccBussinessException("E100006");
            }
            if (!subjectPojo.getTotalBanance().equalsZero()) {
                // 科目余额不为0 
                throw new AccBussinessException("E100007",new Object[]{String.valueOf(subjectPojo.getTotalBanance())});
           }  // 如果存在子科目 
               List<PojoSubject> li = subjectDAO.getSubByOwn(subjectPojo);
                if (li != null && !li.isEmpty()) {
                    throw new AccBussinessException("E100008");
                    }
        subjectPojo.setStatus(AcctStatusType.LOGOUT);
        // 停用成功 
        return true;
  
    }

    /**
     *
     * @param id
     * @return
     * @throws AccBussinessException 
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public SubjectQuery subjectByID(Long id) throws AccBussinessException {
        PojoSubject subject = subjectDAO.get(id);
        SubjectQuery subjectBean = null;
        if (subject == null) {
            throw new AccBussinessException("E100006");  
            }
            subjectBean = BeanCopyUtil.copyBean(SubjectQuery.class, subject);
           if(subject.getParentSubject()!=null){
            subjectBean.setParentId(subject.getParentSubject().getId());
           }
        return subjectBean;
    }

    /**
     *
     * @param code
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public SubjectQuery subjectByCode(String code)throws AccBussinessException  {
        SubjectQuery subjectBean = null; 
        PojoSubject subjectPo = subjectDAO.subjectByCode(code);
        if (subjectPo == null) {
            throw new AccBussinessException("E100006");  
        }
            subjectBean = BeanCopyUtil.copyBean(SubjectQuery.class, subjectPo);
            if(subjectPo.getParentSubject()!=null){
                subjectBean.setParentId(subjectPo.getParentSubject().getId());
               }
        return subjectBean;
    }



    /**
     *
     * @param example
     * @return
     */
    @Override
    protected long getTotal(Subject example) {
        // TODO Auto-generated method stub
        return 0;
    }



    /**
     *
     * @param offset
     * @param pageSize
     * @param example
     * @return
     */
    @Override
    protected List<Subject> getItem(int offset, int pageSize, Subject example) {
        // TODO Auto-generated method stub
        return null;
    }







}
