/* 
 * RegionServiceImpl.java  
 * 
 * version TODO
 *
 * 2016年8月19日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlebank.zplatform.commons.dao.CityDAO;
import com.zlebank.zplatform.commons.dao.CountyDAO;
import com.zlebank.zplatform.commons.dao.ProvinceDAO;
import com.zlebank.zplatform.commons.dao.pojo.PojoCity;
import com.zlebank.zplatform.commons.dao.pojo.PojoCounty;
import com.zlebank.zplatform.commons.dao.pojo.PojoProvince;
import com.zlebank.zplatform.member.service.RegionService;

/**
 * Class Description
 *
 * @author guojia
 * @version
 * @date 2016年8月19日 下午4:45:45
 * @since 
 */
@Service
public class RegionServiceImpl implements RegionService{

	@Autowired
	private ProvinceDAO provinceDAO;
	@Autowired
	private CityDAO cityDAO;
	@Autowired
	private CountyDAO countyDAO;
	
	public List<PojoProvince> getAllProvince(){
		return provinceDAO.getAllProvice();
	}
	
	public List<PojoCity> getCityByPID(Long pid){
		return cityDAO.getCityByPID(pid);
	}
	
	public List<PojoCounty> getCountyByCID(Long cid){
		return countyDAO.getCountyByCID(cid);
	}
}
