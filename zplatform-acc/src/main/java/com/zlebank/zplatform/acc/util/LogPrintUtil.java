/* 
 * LogPrintUtil.java  
 * 
 * version TODO
 *
 * 2016年9月30日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.util;

/**
 * Class Description
 *
 * @author houyong
 * @version
 * @date 2016年9月30日 上午11:50:15
 * @since 
 */
public class LogPrintUtil {
      private static final String prefix="【";
      private static final String suffix="】";
      
      public static String logErrorPrint(String ...params){
          StringBuffer errorMsg=new StringBuffer();
          for (String str : params) {
            errorMsg.append(prefix);
            errorMsg.append(str);
            errorMsg.append(suffix);
          }
          return errorMsg.toString();
      }
}
