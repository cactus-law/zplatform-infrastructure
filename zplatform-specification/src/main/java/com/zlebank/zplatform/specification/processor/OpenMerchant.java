/* 
 * SignProcessor.java  
 * 
 * version 1.0
 *
 * 2015年9月11日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.commons.bean.CountyBean;
import com.zlebank.zplatform.commons.dao.BankInfoDAO;
import com.zlebank.zplatform.commons.dao.CityDAO;
import com.zlebank.zplatform.commons.dao.CountyDAO;
import com.zlebank.zplatform.commons.dao.ProvinceDAO;
import com.zlebank.zplatform.commons.dao.pojo.PojoBankInfo;
import com.zlebank.zplatform.commons.dao.pojo.PojoCounty;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.commons.utils.RegExpValidatorUtil;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.member.bean.MerchantBean;
import com.zlebank.zplatform.member.bean.enums.CardType;
import com.zlebank.zplatform.member.bean.enums.IndustryType;
import com.zlebank.zplatform.member.dao.MemberDAO;
import com.zlebank.zplatform.member.dao.MerchDAO;
import com.zlebank.zplatform.member.exception.MemberBussinessException;
import com.zlebank.zplatform.member.service.MerchService;
import com.zlebank.zplatform.specification.bean.enums.EntranceType;
import com.zlebank.zplatform.specification.dao.MemberEntranceLinkDAO;
import com.zlebank.zplatform.specification.message.Message;
import com.zlebank.zplatform.specification.message.Response;
import com.zlebank.zplatform.specification.pojo.PojoMemberEntranceLink;

/**
 * 二级商户开户
 *
 * @author yangying
 * @version
 * @date 2015年9月11日 上午11:12:47
 * @since
 */
@Service
public class OpenMerchant extends RawAbstractProcessor {
    private static final String SUCCESS_CODE = "00";
    private static final String SUCCESS_DES = "二级商户开户成功";
    private static final String FAIL_CODE = "30";
    private static final String FAIL_INSIDE_CODE = "40";
    private static final String FAIL_DES = "商户开户失败：";
    @Autowired
    private MemberEntranceLinkDAO memberELD;
    @Autowired
    private MerchService merch;
    @Autowired
    private CityDAO city;
    @Autowired
    private ProvinceDAO province;
    @Autowired
    private CountyDAO county;
    @Autowired
    private MerchDAO merchDAOImpl;
    @Autowired
    private BankInfoDAO bankInfoDAO;
    @Autowired
    private MemberDAO memberDAOImpl;
    /**
     *
     * @param request
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public Response realProcess(Message request) {
        // 返回结果定义
        Response res = new Response();
        res.setObject("merchNo", request.getString("merchNo"));
        res.setObject("sessionId", request.getString("sessionId"));
        String rtnMemberId = "";
        String errorMsg = "";
        
        // 验证邮箱格式
        if (!RegExpValidatorUtil.isEmail(request.getString("email"))) {
            errorMsg+="邮箱"+request.getString("email")+"格式不合法|";
        }
        // 验证手机格式contactsTelNo
        if (!RegExpValidatorUtil.IsHandset(request.getString("contactsTelNo"))
                && !RegExpValidatorUtil.IsTelephone(request
                        .getString("contactsTelNo"))) {
            errorMsg+="手机格式"+request.getString("contactsTelNo")+"不合法|";
        }
        // 证件类型
        if (CardType.UNKNOW == CardType.fromValue(request
                .getString("corpCertType"))) {
            errorMsg+="证件类型"+request.getString("corpCertType")+"不合法|";
        } else {
            if (CardType.SFZ == CardType.fromValue(request
                    .getString("corpCertType"))) {
                // 验证身份证格式
                if (!RegExpValidatorUtil.IsIDcard(request.getString("corpCertNo"))) {
                    errorMsg+="身份证"+request.getString("corpCertNo")+"格式不合法|";
                }
            }
            if (CardType.HZ_HKB == CardType.fromValue(request
                    .getString("corpCertType"))) {
                // 验证护照
                if (!RegExpValidatorUtil.IsIDcard(request.getString("corpCertNo"))) {
                    errorMsg+="护照"+request.getString("corpCertNo")+"格式不合法|";
                }
            }
            if (CardType.SBZ_JGZ == CardType.fromValue(request
                    .getString("corpCertType"))) {
                // 验证军官证
                if (!RegExpValidatorUtil.IsIDcard(request.getString("corpCertNo"))) {
                    errorMsg+="军官证"+request.getString("corpCertNo")+"格式不合法|";
                }
            }
        }

        // 验证邮编
        if (!RegExpValidatorUtil.IsPostalcode(request.getString("postCode"))) {
            errorMsg+="邮编"+request.getString("postCode")+"格式不合法|";
        }
        // 主营业务
        if (IndustryType.fromValue(request.getInt("primaryBusiness")) == IndustryType.UNKNOW) {
            errorMsg+="主营业务"+request.getInt("primaryBusiness")+"格式不合法|";
        }

        // 数据检查
        PojoCounty countyPo = county.getCountByCode(String.valueOf(request
                .getLong("county")));
        String city = String.valueOf(request.getLong("city"));
        String province = String.valueOf(request.getLong("province"));
        // 验证县
        if (countyPo == null) {
            errorMsg += "县/区代码"+countyPo+"不存在|";
        } else {
            CountyBean countybean = BeanCopyUtil.copyBean(CountyBean.class,
                    countyPo);
            String cxz = countybean.getCxzCode();
            // 验证市
            String pxz = countybean.getPxzCode();
            if (!city.substring(0, city.length() - 2).equals(
                    cxz.substring(0, cxz.length() - 2))) {
                errorMsg += "城市代码"+pxz+"不存在|";
            }
            // 验证省
            if (!province.substring(0, province.length() - 4).equals(
                    pxz.substring(0, pxz.length() - 4))) {
                errorMsg += "省份代码"+province+"不存在|";
            }
        }

        // 验证手机号是否重复
        if (memberDAOImpl.getMemberByphone(request.getString("contactsTelNo")) != null ) {
            errorMsg += "手机号"+request.getString("contactsTelNo")+"重复|";
        }
        // 验证电子邮件是否重复
        if (memberDAOImpl.getMemberByEmail(request.getString("email")) != null ) {
            errorMsg += "电子邮件"+request.getString("email")+"重复|";
        }
        if (merchDAOImpl.getMerchByTaxno(request.getString("taxregNo")) != null) {
            errorMsg += "税务登记号"+request.getString("taxregNo")+"重复|";
        }
        if (merchDAOImpl.getMerchByLicenceNo(request.getString("licenceNo")) != null) {
            errorMsg += "营业执照"+request.getString("licenceNo")+"重复|";
        }
        if (merchDAOImpl.getMerchByEmail(request.getString("email")) != null) {
            errorMsg += "商户email"+request.getString("email")+"重复|";
        }
        PojoBankInfo bankInfo = bankInfoDAO.getByBankNode(request.getString("despositBank"));
        if (bankInfo == null) {
            errorMsg+="银行代码不存在|";
        }
        // 业务检查异常
        if (!StringUtil.isEmpty(errorMsg)) {
            // 返回报文【应答码】
            res.setResCode(FAIL_CODE);
            // 返回报文【应答码描述】
            res.setResDes(errorMsg);
            return res;
        }
        // 得到银行信息
        String bankCode = bankInfo.getBankCode();
        String bankNode = bankInfo.getBankNode();

        // 开通二级商户
        MerchantBean mb = new MerchantBean();
        mb.setParent(request.getString("merchNo"));
        request.getString("sessionId");

        mb.setEmail(request.getString("email"));
        mb.setMerchname(request.getString("name"));
        mb.setAlias(request.getString("alias"));

        mb.setProvince(request.getLong("province"));

        mb.setCity(request.getLong("city"));

        mb.setStreet(request.getLong("county"));

        mb.setTaxno(request.getString("taxregNo"));
        mb.setLicenceno(request.getString("licenceNo"));
        mb.setOrgcode(request.getString("orgCode"));
        mb.setAddress(request.getString("address"));
        mb.setPostcode(request.getInt("postCode") + "");
        mb.setTrade(IndustryType.fromValue(request.getInt("primaryBusiness")));

        mb.setCorporation(request.getString("corporation"));
        // 身份证件
        mb.setCardtype(CardType.fromValue(request.getString("corpCertType")));
        mb.setCorpno(request.getString("corpCertNo"));
        mb.setBanknode(bankNode);
        // 开户名
        mb.setAccname(request.getString("accountName"));
        mb.setBankcode(bankCode);
        mb.setAccnum(request.getString("accountNo"));
        mb.setContact(request.getString("contacts"));
        mb.setContphone(request.getString("contactsTelNo"));
        // 联系地址
        mb.setContaddress(request.getString("contactsAddr"));
        // 联系邮编
        mb.setContpost(request.getString("contactsPostCode"));
        mb.setContemail(request.getString("contactsEmail"));

        request.getString("mac");
        try {

            // 开通二级商户
            String memberId = merch.createMinorMerchant(mb, 0L);
            PojoMemberEntranceLink pmel = new PojoMemberEntranceLink();
            pmel.setBelongMemberId(request.getString("merchNo"));
            pmel.setEntranceMemberId(request.getString("entranceMemId"));
            pmel.setEntranceType(EntranceType.API);

            // PojoMerchDeta pojoMerchDeta = merch.getMerchBymemberId(memberId);
            pmel.setMemberId(memberId);
            // 将商户信息存进映射表里面
            memberELD.merge(pmel);
            rtnMemberId = pmel.getMemberId();
        } catch (MemberBussinessException e) {
            e.printStackTrace();
            errorMsg = e.getMessage();
        } catch (AbstractBusiAcctException e) {
            e.printStackTrace();
            errorMsg = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            res.setResCode(FAIL_INSIDE_CODE);
            errorMsg = e.getMessage();
        }
        // 业务处理异常
        if (!StringUtil.isEmpty(errorMsg)) {
            // 返回报文【应答码】
            if (StringUtil.isEmpty(res.getResCode())) 
                res.setResCode(FAIL_CODE);
            // 返回报文【应答码描述】
            res.setResDes(FAIL_DES + errorMsg);
            return res;
        }
        // 返回报文【应答码】
        res.setResCode(SUCCESS_CODE);
        // 返回报文【应答码描述】
        res.setResDes(SUCCESS_DES);
        // 返回报文【二级商户号】
        res.setObject("minorMerchNo", rtnMemberId);
        
        return res;
    }
}
