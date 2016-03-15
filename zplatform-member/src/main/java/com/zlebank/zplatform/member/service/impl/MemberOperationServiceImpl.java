/* 
 * MemberOperationServiceImpl.java  
 * 
 * version v1.2
 *
 * 2016年1月14日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.service.impl;

import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.commons.utils.Md5;
import com.zlebank.zplatform.commons.utils.RegExpValidatorUtil;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.member.bean.MemberBean;
import com.zlebank.zplatform.member.bean.Person;
import com.zlebank.zplatform.member.bean.enums.MemberStatusType;
import com.zlebank.zplatform.member.bean.enums.MemberType;
import com.zlebank.zplatform.member.bean.enums.RealNameLvType;
import com.zlebank.zplatform.member.dao.CoopInstiDAO;
import com.zlebank.zplatform.member.dao.MemberDAO;
import com.zlebank.zplatform.member.dao.PersonDAO;
import com.zlebank.zplatform.member.exception.CreateBusiAcctFailedException;
import com.zlebank.zplatform.member.exception.CreateMemberFailedException;
import com.zlebank.zplatform.member.exception.DataCheckFailedException;
import com.zlebank.zplatform.member.exception.InvalidMemberDataException;
import com.zlebank.zplatform.member.exception.LoginFailedException;
import com.zlebank.zplatform.member.exception.MemberBussinessException;
import com.zlebank.zplatform.member.exception.PrimaykeyGeneratedException;
import com.zlebank.zplatform.member.pojo.PojoCoopInsti;
import com.zlebank.zplatform.member.pojo.PojoMember;
import com.zlebank.zplatform.member.pojo.PojoPersonDeta;
import com.zlebank.zplatform.member.service.MemberOperationService;
import com.zlebank.zplatform.member.service.MemberService;
import com.zlebank.zplatform.member.service.PrimayKeyService;

/**
 * 会员操作服务
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月14日 下午6:11:00
 * @since 
 */
@Service
public class MemberOperationServiceImpl implements MemberOperationService {
    
    private Log log = LogFactory.getLog(MemberOperationServiceImpl.class);

    private final static String PERSONPARATYPE="INDIBIN";// 个人会员
    private final static String MERCHPARATYPE="MERCHBIN";// 企业会员
    
    private final static String MEMBER_IDSEQUENCES = "seq_t_merch_deta_memberid";// 会员号生成用序列

    @Autowired
    private MemberService memberService;
    @Autowired
    private PrimayKeyService primayService;
    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private MemberDAO memberDAO;
    @Autowired
    private CoopInstiDAO coopInstiDAO;
    
    /**
     * 注册会员
     * @param memberType
     * @param member  (属性必填 : loginName/pwd/phone/instiCode)
     * @return String 会员号，失败时返回NULL
     * @throws InvalidMemberDataException 
     * @throws CreateMemberFailedException 
     * @throws CreateBusiAcctFailedException 
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
    public String registMember(MemberType memberType, MemberBean member) throws InvalidMemberDataException, CreateMemberFailedException, CreateBusiAcctFailedException {
        if(log.isDebugEnabled()){
            log.debug("参数1："+memberType);
            log.debug("参数2："+JSONObject.fromObject(member));
        }
        // 数据校验
        dataCheck(memberType, member);
        String memberId=null;
        try {
            // 保存会员数据
            memberId = saveMemberData(memberType, member);
        }
        catch (CreateMemberFailedException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new CreateMemberFailedException("开通会员时发生错误");
        }
        
        try {
            // 开通业务账户和会计账户
            member.setMemberId(memberId);
            openBusiAcct(memberType, member);
        }
        catch (CreateBusiAcctFailedException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new CreateBusiAcctFailedException("开通会员账户时发生错误");
        }

        return memberId;
    }
    
    /**
     *  开通业务账户和会计账户
     * @param memberType
     * @param member
     * @throws CreateBusiAcctFailedException 
     */
    private void openBusiAcct(MemberType memberType, MemberBean member) throws CreateBusiAcctFailedException {
        try {
            memberService.openBusiAcct(member.getMemberName(), member.getMemberId(), member.getOperUserId());
        } catch (MemberBussinessException e) {
            log.error(e.getMessage(), e);
            throw new CreateBusiAcctFailedException(e.getMessage());
        } catch (AbstractBusiAcctException e) {
            log.error(e.getMessage(), e);
            throw new CreateBusiAcctFailedException(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new CreateBusiAcctFailedException(e.getMessage());
        }
    }

    /**
     * 数据保存
     * @param memberType
     * @param member
     * @return memberId 返回会员号，如果出错返回NULL
     * @throws CreateMemberFailedException 
     * @throws InvalidMemberDataException 
     */
    private String saveMemberData(MemberType memberType, MemberBean bean) throws CreateMemberFailedException, InvalidMemberDataException {
        String memberId="";
        // 个人会员开通
        if (MemberType.INDIVIDUAL == memberType && bean instanceof Person) {
            try {
                memberId = primayService.getNexId(PERSONPARATYPE, MEMBER_IDSEQUENCES);
            } catch (PrimaykeyGeneratedException e) {
                log.error(e.getMessage(), e);
            }
            if (StringUtil.isEmpty(memberId)) {
                throw new CreateMemberFailedException("生成会员号时发生错误");
            }
            PojoPersonDeta pojo = new PojoPersonDeta();
            pojo.setMemberId(memberId);// 会员ID
            pojo.setParentMemberId(memberId);// 会员ID
            
            setMemberComm(pojo, bean, memberType); // T_member 属性设定
            Person personBean = (Person)bean;
            pojo.setSex(personBean.getSex());// 性别
            pojo.setTelno(personBean.getTelno());// 固定电话
            pojo.setFaxno(personBean.getFaxno());// 传真
            pojo.setGradeId(personBean.getGradeId());// 等级
            pojo.setWechat(personBean.getWechat());// 微信
            pojo.setQq(personBean.getQq());// QQ
            pojo.setWeibo(personBean.getWeibo());// 微博
            pojo.setProvince(personBean.getProvince());// 省份
            pojo.setCity(personBean.getCity());// 城市
            pojo.setStreet(personBean.getStreet());// 街道
            pojo.setAddress(personBean.getAddress());// 详细地址
            pojo.setBirthday(personBean.getBirthday());// 生日
            pojo.setQuestion1(personBean.getQuestion1());// 安全问题1
            pojo.setAnswer1(personBean.getAnswer1());// 问题1答案
            pojo.setQuestion2(personBean.getQuestion2());// 安全问题2
            pojo.setAnswer2(personBean.getAnswer2());// 问题2答案
            pojo.setQuestion3(personBean.getQuestion3());// 安全问题3
            pojo.setAnswer3(personBean.getAnswer3());// 问题3答案
            Date current = new Date();
            pojo.setIntime(current);// 会员注册时间
            pojo.setUptime(current);// 修改时间
            personDAO.merge(pojo);
        } else {
            throw new InvalidMemberDataException("非法的会员类型");
        }
        // 企业会员
        if (MemberType.ENTERPRISE == memberType) {
            try {
                memberId = primayService.getNexId(MERCHPARATYPE, MEMBER_IDSEQUENCES);
            } catch (PrimaykeyGeneratedException e) {
                log.error(e.getMessage(), e);
            }
            if (StringUtil.isEmpty(memberId)) {
                throw new CreateMemberFailedException("生成会员号时发生错误");
            }
            // TODO: 下一版本开发
        }
        return memberId;
    }

    /**
     * 设置会员共通属性
     * @param pojo
     * @param bean
     * @param memberType 
     */
    private void setMemberComm(PojoMember pojo, MemberBean bean, MemberType memberType) {
        PojoCoopInsti pojoCoopInsit  = coopInstiDAO.getByInstiCode(bean.getInstiCode());
        pojo.setInstiId(pojoCoopInsit.getId());// 合作机构
        pojo.setMemberName(bean.getMemberName());// 会员昵称
        pojo.setLoginName(bean.getLoginName());// 登录名
        pojo.setPwd(Md5.getInstance().md5s(bean.getPwd()));// 登录密码
        pojo.setRealnameLv(RealNameLvType.LV1);// 实名等级，01-未实名，02-姓名+身份证,03-银行卡校验,04-证件审核
        pojo.setPhone(bean.getPhone());// 手机
        pojo.setEmail(bean.getEmail());// 邮箱
        pojo.setMemberType(memberType);// 会员类型，01-个人，02-企业
        pojo.setStatus(MemberStatusType.NORMAL);// 会员状态，00-正常，02-冻结，99-注销
        pojo.setRegisterIdent("01");// 注册认证，01-手机认证，02-邮箱认证，03-Both
        Date current = new Date();
        pojo.setInTime(current);// 会员注册时间
        pojo.setUpTime(current);// 修改时间
    }
    /**
     * 数据校验
     * @param memberType
     * @param member
     * @return
     * @throws InvalidMemberDataException 
     */
    private void dataCheck(MemberType memberType, MemberBean member) throws InvalidMemberDataException {
        if (MemberType.INDIVIDUAL != memberType && MemberType.ENTERPRISE != memberType) {
            throw new InvalidMemberDataException("非法的会员类型");
        }
        if (StringUtil.isEmpty(member.getLoginName())) {
            throw new InvalidMemberDataException("登陆名不可为空");
        }
        if (StringUtil.isEmpty(member.getPhone())) {
            throw new InvalidMemberDataException("手机号不可为空");
        }
        // 验证手机格式
        if (!RegExpValidatorUtil.IsHandset(member.getPhone())
                && !RegExpValidatorUtil.IsTelephone(member.getPhone())) {
            throw new InvalidMemberDataException("手机格式不合法");
        }
        if (StringUtil.isEmpty(member.getPwd())) {
            throw new InvalidMemberDataException("登陆密码不可为空");
        }
        if (!RegExpValidatorUtil.IsPassword(member.getPwd())) {
            throw new InvalidMemberDataException("登陆密码不符合要求（要求长度6-18位且字符与数字同时出现）");
        }
        if (StringUtil.isEmpty(member.getInstiCode())) {
            throw new InvalidMemberDataException("合作机构不可为空");
        }
    }

    /**
     * 会员登陆/验证登陆密码
     * @param memberType (属性必填 : [phone或loginName]/pwd/instiCode )
     * @param member
     * @return 会员号，失败时返回NULL
     * @throws DataCheckFailedException 
     * @throws LoginFailedException 
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
    public String login(MemberType memberType, MemberBean member) throws DataCheckFailedException, LoginFailedException {
        if(log.isDebugEnabled()){
            log.debug("参数1："+memberType);
            log.debug("参数2："+JSONObject.fromObject(member));
        }
        if (StringUtil.isEmpty(member.getLoginName()) && StringUtil.isEmpty(member.getPhone())) {
            throw new DataCheckFailedException("手机号和登陆名不可同时为空");
        }
        if (StringUtil.isEmpty(member.getInstiCode())) {
            throw new DataCheckFailedException("机构号不可为空");
        }
        PojoMember pm=null;
        if (StringUtil.isNotEmpty(member.getLoginName())) {
            pm = memberDAO.getMemberByLoginNameAndCoopInsti(member.getLoginName(), member.getInstiCode());
        } else if (StringUtil.isNotEmpty(member.getPhone())) {
            pm = memberDAO.getMemberByPhoneAndCoopInsti(member.getPhone(), member.getInstiCode());
        }
        if (pm == null) {
            throw new LoginFailedException("用户名不存在");
        }
        String requestPwd = Md5.getInstance().md5s(member.getPwd());
        if (requestPwd.equals(pm.getPwd())) {
            return pm.getMemberId();
        } else {
            throw new LoginFailedException("密码不正确");
        }
    }
    
    /**
     * 验证支付密码 (属性必填 : [phone或loginName]/paypwd/instiCode )
     * @param memberType
     * @param member
     * @return
     * @throws DataCheckFailedException
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
    public boolean verifyPayPwd(MemberType memberType, MemberBean member) throws DataCheckFailedException {
        if(log.isDebugEnabled()){
            log.debug("参数1："+memberType);
            log.debug("参数2："+JSONObject.fromObject(member));
        }
        if (StringUtil.isEmpty(member.getLoginName()) && StringUtil.isEmpty(member.getPhone())) {
            throw new DataCheckFailedException("手机号和登陆名不可同时为空");
        }
        if (StringUtil.isEmpty(member.getInstiCode())) {
            throw new DataCheckFailedException("机构号不可为空");
        }
        if (StringUtil.isEmpty(member.getPaypwd())) {
            throw new DataCheckFailedException("支付密码不可为空");
        }
        PojoMember pm=null;
        if (StringUtil.isNotEmpty(member.getLoginName())) {
            pm = memberDAO.getMemberByLoginNameAndCoopInsti(member.getLoginName(), member.getInstiCode());
        } else if (StringUtil.isNotEmpty(member.getPhone())) {
            pm = memberDAO.getMemberByPhoneAndCoopInsti(member.getPhone(), member.getInstiCode());
        }
        if (pm == null) {
            return false;
        }
        String requestPayPwd = Md5.getInstance().md5s(member.getPaypwd());
        if (requestPayPwd.equals(pm.getPayPwd())) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 重置登陆密码
     * @param memberType
     * @param member (属性必填 : [phone或loginName]/pwd/instiCode )
     * @param newPwd 必填
     * @return boolean 重置成功返回true 其它情况返回false
     * @throws DataCheckFailedException
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
    public boolean resetLoginPwd(MemberType memberType, MemberBean member, String newPwd, boolean isCheckOldPassword) throws DataCheckFailedException {
        if(log.isDebugEnabled()){
            log.debug("参数1："+memberType);
            log.debug("参数2："+JSONObject.fromObject(member));
            log.debug("参数3："+newPwd);
            log.debug("参数4："+isCheckOldPassword);
        }
        if (StringUtil.isEmpty(member.getLoginName()) && StringUtil.isEmpty(member.getPhone())) {
            throw new DataCheckFailedException("手机号和登陆名不可同时为空");
        }
        if (StringUtil.isEmpty(member.getInstiCode())) {
            throw new DataCheckFailedException("机构号不可为空");
        }
        PojoMember pm=null;
        if (StringUtil.isNotEmpty(member.getLoginName())) {
            pm = memberDAO.getMemberByLoginNameAndCoopInsti(member.getLoginName(), member.getInstiCode());
        } else if (StringUtil.isNotEmpty(member.getPhone())) {
            pm = memberDAO.getMemberByPhoneAndCoopInsti(member.getPhone(), member.getInstiCode());
        }
        if (pm == null) {
            return false;
        }
        String requestPwd = StringUtil.isEmpty(member.getPwd()) ? null : Md5 .getInstance().md5s(member.getPwd());
        if (!isCheckOldPassword || requestPwd.equals(pm.getPwd())) {
            // 设置新的登陆密码
            String setPwd = Md5.getInstance().md5s(newPwd);
            pm.setPwd(setPwd);
            pm.setUpTime(new Date());
            memberDAO.update(pm);
            return true;
        } else {
            throw new DataCheckFailedException("旧密码输入错误");
        }
    }
    
    /**
     * 设置/重置支付密码
     * @param memberType
     * @param member  (属性必填 : [phone或loginName]/paypwd/instiCode )
     * @param newPayPwd 必填 新支付密码
     * @return
     * @throws DataCheckFailedException
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
    public boolean resetPayPwd(MemberType memberType, MemberBean member, String newPayPwd, boolean isCheckOldPassword) throws DataCheckFailedException {
        if(log.isDebugEnabled()){
            log.debug("参数1："+memberType);
            log.debug("参数2："+JSONObject.fromObject(member));
            log.debug("参数3："+newPayPwd);
        }
        if (StringUtil.isEmpty(member.getLoginName()) && StringUtil.isEmpty(member.getPhone())) {
            throw new DataCheckFailedException("手机号和登陆名不可同时为空");
        }
        if (StringUtil.isEmpty(member.getInstiCode())) {
            throw new DataCheckFailedException("机构号不可为空");
        }
        PojoMember pm=null;
        if (StringUtil.isNotEmpty(member.getLoginName())) {
            pm = memberDAO.getMemberByLoginNameAndCoopInsti(member.getLoginName(), member.getInstiCode());
        } else if (StringUtil.isNotEmpty(member.getPhone())) {
            pm = memberDAO.getMemberByPhoneAndCoopInsti(member.getPhone(), member.getInstiCode());
        }
        if (pm == null) {
            return false;
        }
        String requestPwd = StringUtil.isEmpty(member.getPaypwd()) ? null : Md5.getInstance().md5s(member.getPaypwd());
        if (!isCheckOldPassword || requestPwd.equals(pm.getPayPwd())) {
            // 设置新的支付密码
            String setPwd = Md5.getInstance().md5s(newPayPwd);
            pm.setPayPwd(setPwd);
            pm.setUpTime(new Date());
            memberDAO.update(pm);
            return true;
        } else {
            throw new DataCheckFailedException("旧密码输入错误");
        }
    }
}
