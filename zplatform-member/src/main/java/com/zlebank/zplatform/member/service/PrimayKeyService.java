/* 
 * PrimayKeyService.java  
 * 
 * version TODO
 *
 * 2015年9月11日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.service;

import com.zlebank.zplatform.member.exception.MemberBussinessException;
import com.zlebank.zplatform.member.exception.PrimaykeyGeneratedException;

/**
 * Sequence id generator for business actors.
 *
 * @author yangpeng
 * @version
 * @date 2015年9月11日 下午3:45:15
 * @since 
 */
public interface PrimayKeyService {
    /**
     * Get a merchant member id by paraType. Return a number string had 15 byte format like this:(ActorType[one byte]+[15-n-1 byte]zero string+sequence[n byte].
     * <p>Deprecated since 1.2.0</>
     * @param paraType
     * @return
     * @throws MemberBussinessException 
     */
    @Deprecated
    public String getNexId(String paraType) throws MemberBussinessException;
    
   /**
    * Get id by paraType and seqName. Return a number string had 15 byte format like this:(ActorType[one byte]+[15-n-1 byte]zero string+sequence[n byte].
    * <p>for example:100000000000234</p>
    * <p>ActorType is determined by paraType,its value get from table "t_para_dic"</p>
    * <p>sequence is determined by seqName,its value get from sequence Definition in db</p>
    * @param paraType a value of column of "para_type" in table "t_para_dic".It should be one of this: <ul><li>MERCHBIN</li><li>INDIBIN</li><li>COOPINSTIBIN</li></ul>
    * @param seqName It should be one of this: <ul><li>SEQ_COOP_INSTI_CODE</li><li>SEQ_T_MERCH_DETA_MEMBERID</li><li>COOPINSTIBIN</li></ul>
    * @return a 15 byte string of sequence value
    * @throws PrimaykeyGeneratedException if generate fail
    */
    public String getNexId(String paraType,String seqName) throws PrimaykeyGeneratedException ;

}
