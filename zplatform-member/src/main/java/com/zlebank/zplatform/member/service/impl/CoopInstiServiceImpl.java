package com.zlebank.zplatform.member.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.service.BusiAcctService;
import com.zlebank.zplatform.commons.utils.RSAUtils;
import com.zlebank.zplatform.member.bean.CoopInstiMK;
import com.zlebank.zplatform.member.bean.enums.EncryptAlgorithm;
import com.zlebank.zplatform.member.bean.enums.TerminalAccessType;
import com.zlebank.zplatform.member.dao.CoopInstiDAO;
import com.zlebank.zplatform.member.exception.CoopInstiException;
import com.zlebank.zplatform.member.exception.InstiNameExistedException;
import com.zlebank.zplatform.member.exception.PrimaykeyGeneratedException;
import com.zlebank.zplatform.member.pojo.PojoCoopInsti;
import com.zlebank.zplatform.member.pojo.PojoInstiMK;
import com.zlebank.zplatform.member.service.ICoopInstiService;
import com.zlebank.zplatform.member.service.PrimayKeyService;

@Service
public class CoopInstiServiceImpl implements ICoopInstiService {

    @Autowired
    private BusiAcctService busiAcctService;
    @Autowired
    private CoopInstiDAO coopInstiDAO;
    @Autowired
    private PrimayKeyService primayKeyService;

    private static final String COOPINST_PARA_TYPE = "COOPINSTIBIN";
    private static final String COOPINST_CODE_SEQ = "SEQ_COOP_INSTI_CODE";

    @Override
    public CoopInstiMK getCoopInstiMK(String instiCode,
            TerminalAccessType terminalAccessType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public String createCoopInsti(String instiName) throws CoopInstiException {

        /*
         * check if instiName is repeat
         */
        if (coopInstiDAO.isNameExist(instiName)) {
            throw new InstiNameExistedException();
        }

        /*
         * generate coop insti code
         */
        String coopInstiCode;
        try {
            coopInstiCode = primayKeyService.getNexId(COOPINST_PARA_TYPE,
                    COOPINST_CODE_SEQ);
        } catch (PrimaykeyGeneratedException pge) {
            CoopInstiException cie = new CoopInstiException();
            cie.initCause(pge);
            throw cie;
        } catch (HibernateException he) {
            he.printStackTrace();
            CoopInstiException cie = new CoopInstiException();
            cie.initCause(he);
            throw cie;
        }

        /*
         * instance and init a pojos
         */
        PojoCoopInsti pojoCoopInsti = new PojoCoopInsti();
        pojoCoopInsti.setInstiCode(coopInstiCode);
        pojoCoopInsti.setInstiName(instiName);
        List<PojoInstiMK> instiMKs = new ArrayList<PojoInstiMK>();

        /*
         * generate institution key
         */
        try {
            for (TerminalAccessType terminalAccessType : TerminalAccessType
                    .values()) {
                Map<String, Object> coopInsti_keyMap = RSAUtils.genKeyPair();
                Map<String, Object> plath_keyMap = RSAUtils.genKeyPair();
                String coopInsti_publicKey = RSAUtils
                        .getPublicKey(coopInsti_keyMap);
                String coopInsti_privateKey = RSAUtils
                        .getPrivateKey(coopInsti_keyMap);
                String plath_publicKey = RSAUtils.getPublicKey(plath_keyMap);
                String plath_privateKey = RSAUtils.getPrivateKey(plath_keyMap);
                
                PojoInstiMK instiMK = new PojoInstiMK();
                instiMK.setCoopInsti(pojoCoopInsti);
                instiMK.setEncryptAlgorithm(EncryptAlgorithm.RSA);
                instiMK.setTerminalAccessTyep(terminalAccessType);
                instiMK.setInstiPriKey(coopInsti_privateKey);
                instiMK.setInstiPubKey(coopInsti_publicKey);
                instiMK.setZplatformPriKey(plath_privateKey);
                instiMK.setZplatformPubKey(plath_publicKey);
                instiMKs.add(instiMK);
            }
            pojoCoopInsti.setInstisMKs(instiMKs);
        } catch (Exception e) {
            throw new CoopInstiException();
        }
        
        // open institution account

        // persistent to db

        return null;
    }

}
