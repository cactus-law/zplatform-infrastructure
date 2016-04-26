package com.zlebank.zplatform.member.pojo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
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
@Entity
@Table(name = "T_COOP_INSTI")
public class PojoCoopInsti {
    private long id;
    private String instiCode;
    private String instiName;
    private List<ProductModel> products;
    private List<PojoInstiMK> instisMKs;
    private String status;
    private Timestamp inTime;
    private long inUserId;

    @GenericGenerator(name = "id_gen", strategy = "enhanced-table", parameters = {
            @Parameter(name = "table_name", value = "T_C_PRIMAY_KEY"),
            @Parameter(name = "value_column_name", value = "NEXT_ID"),
            @Parameter(name = "segment_column_name", value = "KEY_NAME"),
            @Parameter(name = "segment_value", value = "COOP_INSTI_ID"),
            @Parameter(name = "increment_size", value = "1"),
            @Parameter(name = "optimizer", value = "pooled-lo")})
    @Id
    @GeneratedValue(generator = "id_gen")
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "INSTI_CODE", length = 15, nullable = false)
    public String getInstiCode() {
        return instiCode;
    }
    public void setInstiCode(String instiCode) {
        this.instiCode = instiCode;
    }

    @Column(name = "INSTI_NAME", length = 256, nullable = false)
    public String getInstiName() {
        return instiName;
    }
    public void setInstiName(String instiName) {
        this.instiName = instiName;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "TL_COOPINSTI_PRODUCT", joinColumns = {@JoinColumn(name = "COOP_INSTI_ID")}, inverseJoinColumns = {@JoinColumn(name = "PROUCT_ID")})
    public List<ProductModel> getProducts() {
        return products;
    }
    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }

    @OneToMany(mappedBy = "coopInsti")
    @Cascade(value = {CascadeType.SAVE_UPDATE})
    public List<PojoInstiMK> getInstisMKs() {
        return instisMKs;
    }
    public void setInstisMKs(List<PojoInstiMK> instisMKs) {
        this.instisMKs = instisMKs;
    }
    @Column(name = "STATUS", length = 2, nullable = false)
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Column(name = "IN_TIME")
    public Timestamp getInTime() {
        return inTime;
    }
    public void setInTime(Timestamp inTime) {
        this.inTime = inTime;
    }
    @Column(name = "IN_USER")
    public long getInUserId() {
        return inUserId;
    }
    public void setInUserId(long inUserId) {
        this.inUserId = inUserId;
    }
}
