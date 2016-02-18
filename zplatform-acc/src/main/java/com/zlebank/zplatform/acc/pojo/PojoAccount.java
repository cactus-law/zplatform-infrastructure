/* 
 * PojoAccount.java  
 * 
 * version 1.0
 *
 * 2015年8月20日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.pojo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年8月20日 下午4:31:01
 * @since 
 */
@Entity
@DiscriminatorValue("00")
public class PojoAccount  extends PojoAbstractSubject{
}
