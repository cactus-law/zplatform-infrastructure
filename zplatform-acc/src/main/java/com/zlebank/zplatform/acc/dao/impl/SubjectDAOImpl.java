/* 
 * SubjectDAOImpl.java  
 * 
 * version 1.0
 *
 * 2015年8月20日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.acc.bean.Subject;
import com.zlebank.zplatform.acc.bean.enums.AcctStatusType;
import com.zlebank.zplatform.acc.dao.SubjectDAO;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.pojo.PojoSubject;
import com.zlebank.zplatform.acc.service.GetDACService;
import com.zlebank.zplatform.commons.dao.impl.AbstractPagedQueryDAOImpl;
import com.zlebank.zplatform.commons.utils.StringUtil;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年8月20日 下午4:43:46
 * @since
 */
    @Repository
    public class SubjectDAOImpl extends AbstractPagedQueryDAOImpl<PojoSubject,Subject>
        implements  SubjectDAO { 
        @Autowired
        private GetDACService dacUtil;
    /**
     * 根据ID得到科目
     *
     * @param id
     * @return PojoSubject
     */
    public PojoSubject get(long id) throws AccBussinessException{
        
        PojoSubject subjec=  (PojoSubject) getSession().get(PojoSubject.class, id);
        if(subjec!=null){
            String DAC=dacUtil.generteDAC(subjec.getAcctCode(),subjec.getBalance() , subjec.getFrozenBalance(),subjec.getTotalBanance());
            if(!DAC.equals(subjec.getDac())){
                throw new AccBussinessException("E100005");
            }
            }
            return subjec;
            
    }
    /**
     * 新增科目
     *
     * @param subject
     * @return
     */
    public PojoSubject save(PojoSubject subject) {
        String dac=dacUtil.generteDAC(subject.getAcctCode(), subject.getBalance(), subject.getFrozenBalance(), subject.getTotalBanance());
        subject.setDac(dac);
        return (PojoSubject) merge(subject);
    }

    /**
     * 根据条件得到科目
     * 
     * @param t
     * @return
     */
    @Override
    protected Criteria buildCriteria(Subject t) {
        Criteria result = this.getSession().createCriteria(PojoSubject.class);
        // 科目代码
        if (!StringUtil.isNull(t.getAcctCode())) {
            result.add(Restrictions.like("acctCode", t.getAcctCode(),
                    MatchMode.ANYWHERE));
        }
        // 科目名称
        if (!StringUtil.isNull(t.getAcctCodeName())) {
            result.add(Restrictions.like("acctCodeName", t.getAcctCodeName()
                    .toString(), MatchMode.ANYWHERE));
        }
        // 父级科目号
        if (t.getParentSubject() != null) {
            result.add(Restrictions.like("parentSubject", t.getParentSubject()));
        }
//        // 科目状态
//        if (!StringUtil.isNull(t.getStatus())) {
//            result.add(Restrictions.eq("status", t.getStatus()));
//        }
//        // 科目类型
//        if (!StringUtil.isNull(t.getAcctType())) {
//            result.add(Restrictions.eq("acctType", t.getAcctType()));
//        }
//        // acctElement会计要素
//        if (!StringUtil.isNull(t.getAcctElement())) {
//            result.add(Restrictions.eq("acctElement", t.getAcctElement()
//                    ));
//        }
        return result;
    }
    /**
     *
     * @param code
     * @return PojoSubject
     * @throws AccBussinessException 
     */
    @Override
    public PojoSubject subjectByCode(String code) throws AccBussinessException {
        PojoSubject subjec = null;
        try {

            subjec = (PojoSubject) this.getSession()
                    .createCriteria(PojoSubject.class)
                    .add(Restrictions.eq("acctCode", code))
                    .add(Restrictions.ne("status", AcctStatusType.LOGOUT))
                    .uniqueResult();
            if (subjec != null) {
                String DAC = dacUtil.generteDAC(subjec.getAcctCode(),
                        subjec.getBalance(), subjec.getFrozenBalance(),
                        subjec.getTotalBanance());
                if (!DAC.equals(subjec.getDac())) {
                    throw new AccBussinessException("E100005");
                }
            }

        } catch (Exception e) {
            
            throw new AccBussinessException("E100002");
        }
        return subjec;
    }
    /**
     *
     * @param parentAcctId
     * @return PojoSubject
     * @throws AccBussinessException 
     */

    @Override
    public PojoSubject getParentByPID(Long  parentAcctId) throws AccBussinessException {
        PojoSubject subjec=  (PojoSubject) this.getSession()
                .createCriteria(PojoSubject.class)
                .add(Restrictions.eq("id", parentAcctId))
                .uniqueResult();
        if(subjec!=null){
            String DAC=dacUtil.generteDAC(subjec.getAcctCode(),subjec.getBalance() , subjec.getFrozenBalance(),subjec.getTotalBanance());
            if(!DAC.equals(subjec.getDac())){
                throw new AccBussinessException("E100005");
            }
            }
            return subjec;
            
    }
    /**
     *
     * @param parentAcctId
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override

 public List<PojoSubject> getSubByOwn(PojoSubject subjectPojo) {      
        return this.getSession().createCriteria(PojoSubject.class)
               .add(Restrictions.eq("parentSubject", subjectPojo))
               .add(Restrictions.ne("status", AcctStatusType.LOGOUT)).list();
               
    }
 
}
