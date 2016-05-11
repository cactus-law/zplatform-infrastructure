package com.zlebank.zplatform.acc.service.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlebank.zplatform.acc.bean.enums.AccCodeType;
import com.zlebank.zplatform.acc.bean.enums.Usage;
import com.zlebank.zplatform.acc.dao.BusiAcctDAO;
import com.zlebank.zplatform.acc.exception.BusiAcctNotExistException;

@Service
public class AccCodePlaceholderFactory {
    
    @Autowired
    private BusiAcctDAO busiAcctDAO;
    
    private AcctCodePlaceHolder newPlaceholder(final String placeHolderValue,final String businessActorId){
        return new AcctCodePlaceHolder() {
            
            @Override
            public String getAccCode() throws BusiAcctNotExistException {
                Usage usage = Usage.fromValue(placeHolderValue.substring(1));
                
                if(usage==Usage.UNKNOW){
                    // TODO
                    //throw new UnknowusageException();
                }
                return busiAcctDAO.getAccountEntiy(usage, businessActorId).getAcctCode();
            }
        };
    }
    
    private AcctCodePlaceHolder newParentSubject(final String placeHolderValue){
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
    public AcctCodePlaceHolder getAcctCodePlaceholder(AccCodeType accCodeType,String placeHolderValue,String businessActorId){
        switch (accCodeType) {
            case PLACEHODER :
                return newPlaceholder(placeHolderValue,businessActorId);
            case PARENTSUBJECT:
                //TODO
                return null;
            case ACCOUNT:
                return newParentSubject(placeHolderValue);
            default :
                //TODO
                //throw new UnknowPlaceholderException();
                return null;
        }
    }
}
