package com.zlebank.zplatform.acc.service.entry;

import com.zlebank.zplatform.acc.exception.BusiAcctNotExistException;

/**
 * 
 * TODO
 *
 * @author yangying
 * @version
 * @date 2016年5月10日 上午11:41:19
 * @since
 */
public interface AcctCodePlaceHolder {
    
    String getAccCode()throws BusiAcctNotExistException;
    
}
