/* 
 * Request.java  
 * 
 * version 1.0
 *
 * 2015年9月11日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.message;

import net.sf.json.JSONObject;

import com.zlebank.zplatform.specification.RequestType;


/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月11日 上午9:18:38
 * @since
 */
public class Request extends RawMessage {
    private Head head;
    private JSONObject orignJson;

    /**
     *
     * @return
     */
    public void setHead(Head head) {
        this.head = head;
    } 
    
    public Head getHead() {
       return head;
    } 
    
    
    /**
    *
    * @return
    */ 
   public RequestType getRequestType() {
       return getHead().getRequestType();
   }

   /**
    *
    * @return
    */ 
   public String getVersion() {
       return getHead().getVersion();
   }
   
   public JSONObject getOrginJson(){
       return orignJson;
   }
   public void setOrginJson(JSONObject json){
       this.orignJson = json;
   }
}
