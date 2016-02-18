/* 
 * PojoSubject.java  
 * 
 * version 1.0
 *
 * 2015年8月20日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年8月20日 下午4:31:48
 * @since
 */
@Entity
@DiscriminatorValue("01")
public class PojoSubject extends PojoAbstractSubject {

    private Set<PojoAbstractSubject> childs = new HashSet<PojoAbstractSubject>();
    
    @OneToMany(mappedBy="parentSubject",fetch=FetchType.LAZY)
    public Set<PojoAbstractSubject> getChilds() {
        return childs;
    }

    public void setChilds(Set<PojoAbstractSubject> childs) {
        this.childs = childs;
    }
    
    public PojoSubject(){
        
    }
    
    public PojoSubject(long id){
        this.id=id;
    }

}
