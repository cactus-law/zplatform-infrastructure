/* 
 * EnterpriseBean.java  
 * 
 * version TODO
 *
 * 2016年3月21日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.bean;

import java.io.Serializable;

/**
 * 企业 Bean
 * (如果需要更多信息，可以自行添加)
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年3月21日 下午2:08:04
 * @since 
 */
public class EnterpriseBean implements Serializable{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2640101753501197219L;
    /**企业名称**/
    private String enterpriseName;

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }
    
    
}
