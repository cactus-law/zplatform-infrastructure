package com.zlebank.zplatform.member.bean;

import java.sql.Timestamp;
import java.util.List;

import com.zlebank.zplatform.commons.dao.pojo.ProductModel;
/**
 * A bean represent cooperative institution
 * @author yangying
 *
 */
public class CoopInsti {
	
	private long id;
	private String instiCode;
	private String instiName;
	private List<ProductModel> products;
	private String status;
	private Timestamp inTime;
	private long inUserId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getInstiCode() {
		return instiCode;
	}
	public void setInstiCode(String instiCode) {
		this.instiCode = instiCode;
	}
	public String getInstiName() {
		return instiName;
	}
	public void setInstiName(String instiName) {
		this.instiName = instiName;
	}
	public List<ProductModel> getProducts() {
		return products;
	}
	public void setProducts(List<ProductModel> products) {
		this.products = products;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getInTime() {
		return inTime;
	}
	public void setInTime(Timestamp inTime) {
		this.inTime = inTime;
	}
	public long getInUserId() {
		return inUserId;
	}
	public void setInUserId(long inUserId) {
		this.inUserId = inUserId;
	}
}
