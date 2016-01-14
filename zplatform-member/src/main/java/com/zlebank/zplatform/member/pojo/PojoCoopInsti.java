package com.zlebank.zplatform.member.pojo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
	
	@GenericGenerator(name = "id_gen", strategy = "enhanced-table", parameters = {
            @Parameter(name = "table_name", value = "T_C_PRIMAY_KEY"),
            @Parameter(name = "value_column_name", value = "NEXT_ID"),
            @Parameter(name = "segment_column_name", value = "KEY_NAME"),
            @Parameter(name = "segment_value", value = "COOP_INSTI_ID"),
            @Parameter(name = "increment_size", value = "1"),
            @Parameter(name = "optimizer", value = "pooled-lo") })
    @Id
    @GeneratedValue(generator = "id_gen")
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    
    @Column(name="INSTI_CODE",length=15,nullable=false)
    public String getInstiCode() {
        return instiCode;
    }
    public void setInstiCode(String instiCode) {
        this.instiCode = instiCode;
    }
    
    @Column(name="INSTI_NAME",length=256,nullable=false)
    public String getInstiName() {
        return instiName;
    }
    public void setInstiName(String instiName) {
        this.instiName = instiName;
    }
    
    @OneToMany
    @JoinColumn(name="COOP_INSTI_ID",insertable=false,updatable=false)
    public List<ProductModel> getProducts() {
        return products;
    }
    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }
    
    @OneToMany
    @JoinColumn(name="COOP_INSTI_ID",insertable=false,updatable=false)
    public List<PojoInstiMK> getInstisMKs() {
        return instisMKs;
    }
    public void setInstisMKs(List<PojoInstiMK> instisMKs) {
        this.instisMKs = instisMKs;
    } 
}
