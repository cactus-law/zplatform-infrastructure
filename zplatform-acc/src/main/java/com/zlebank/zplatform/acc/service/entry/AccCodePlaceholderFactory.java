package com.zlebank.zplatform.acc.service.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlebank.zplatform.acc.bean.TradeInfo;
import com.zlebank.zplatform.acc.bean.enums.AccCodeType;
import com.zlebank.zplatform.acc.bean.enums.Usage;
import com.zlebank.zplatform.acc.dao.BusiAcctDAO;
import com.zlebank.zplatform.acc.exception.BusiAcctNotExistException;

@Service
public class AccCodePlaceholderFactory {
    
    @Autowired
    private BusiAcctDAO busiAcctDAO;
    
    private static final String LITERAL_PAYER = "F";
    private static final String LITERAL_RECEIVER = "S";
    private static final String LITERAL_CHANNEL = "T";
    private static final String LITERAL_COMMISSION_RECE = "Y";
    private static final String LITERAL_COOP_INST = "C";
    private static final String LITERAL_PRODUCT="P";
    private static final String LITERAL_ACCESS_COOP_INST="I";
    
    private AcctCodePlaceHolder newPlaceholder(final String placeHolderValue,final TradeInfo tradeInfo){
        return new AcctCodePlaceHolder() {
            @Override
            public String getAccCode() throws BusiAcctNotExistException {
                Usage usage = Usage.fromValue(placeHolderValue.substring(1));
                String businessActorId = getBusiActorByFlag(String.valueOf(placeHolderValue.charAt(0)).toUpperCase(), tradeInfo);
                if(usage==Usage.UNKNOW){
                    // TODO
                    //throw new UnknowusageException();
                }
                 String accCode = busiAcctDAO.getAccCodeByUsageAndBusiActorId(usage, businessActorId);
                 if(accCode==null||accCode.equals("")){                    
                     throw new RuntimeException("获取的账户号为空.占位符:"+placeHolderValue+",参与者编号:"+businessActorId);
                 }
                 return accCode;
            }
        };
    }
    
    private AcctCodePlaceHolder newAccount(final String placeHolderValue){
        return new AcctCodePlaceHolder() {
            @Override
            public String getAccCode() {
                return placeHolderValue;
            }
        };
    }
    
    /**
     * 根据账户占位符类型(一般指accCodeType)，占位符值(一般指accCode)，参与方id获取账户号类型
     * @param accCodePlacehoderType
     * @param placeHolderValue
     * @param businessActorId
     * @return
     */
    public AcctCodePlaceHolder getAcctCodePlaceholder(AccCodeType accCodeType,String placeHolderValue,TradeInfo tradeInfo){
        switch (accCodeType) {
            case PLACEHODER :
                return newPlaceholder(placeHolderValue,tradeInfo);
            case PARENTSUBJECT:
                //TODO
                return null;
            case ACCOUNT:
                return newAccount(placeHolderValue);
            default :
                //TODO
                //throw new UnknowPlaceholderException();
                return null;
        }
    }
    
    private String getBusiActorByFlag(String flag, TradeInfo tradeInfo) {
        switch (flag) {
            case LITERAL_CHANNEL :
                return tradeInfo.getChannelId();
            case LITERAL_COMMISSION_RECE :
                return tradeInfo.getPayToParentMemberId();
            case LITERAL_PAYER :
                return tradeInfo.getPayMemberId();
            case LITERAL_RECEIVER :
                return tradeInfo.getPayToMemberId();
            case LITERAL_COOP_INST:
                return tradeInfo.getCoopInstCode();
            case LITERAL_PRODUCT:
                return tradeInfo.getProductId();
            case LITERAL_ACCESS_COOP_INST:
                return tradeInfo.getAccess_coopInstCode();
            default :
                return null;
        }
    }
}
