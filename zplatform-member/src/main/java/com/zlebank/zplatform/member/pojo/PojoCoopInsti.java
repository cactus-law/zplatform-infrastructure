package com.zlebank.zplatform.member.pojo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.zlebank.zplatform.commons.dao.pojo.ProductModel;
/**
 * 
 * A POJO represent cooperative institution
 *
 * @author yangying
 * @version
 * @date 2016年1月12日 下午2:59:49
 * @since
 */
public class PojoCoopInsti {
	private long id;
	private String instiCode;
	private String instiName;
	private List<ProductModel> products;
	private List<PojoInstiMK> instisMKs;
	
	@Id
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    
    @Column(name="INSTI_CODE")
    public String getInstiCode() {
        return instiCode;
    }
    public void setInstiCode(String instiCode) {
        this.instiCode = instiCode;
    }
    
    @Column(name="INSTI_NAME")
    public String getInstiName() {
        return instiName;
    }
    public void setInstiName(String instiName) {
        this.instiName = instiName;
    }
    
    @OneToMany
    public List<ProductModel> getProducts() {
        return products;
    }
    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }
    @OneToMany
    public List<PojoInstiMK> getInstisMKs() {
        return instisMKs;
    }
    public void setInstisMKs(List<PojoInstiMK> instisMKs) {
        this.instisMKs = instisMKs;
    }
}
