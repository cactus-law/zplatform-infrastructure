package com.zlebank.zplatform.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.commons.dao.pojo.ProductModel;
import com.zlebank.zplatform.member.dao.CoopInstiDAO;
import com.zlebank.zplatform.member.exception.AbstractCoopInstiException;
import com.zlebank.zplatform.member.exception.CoopInstiNotExistException;
import com.zlebank.zplatform.member.pojo.PojoCoopInsti;
import com.zlebank.zplatform.member.service.CoopInstiProductService;

@Service
public class CoopInstiProductServiceImpl implements CoopInstiProductService {
	@Autowired
	private CoopInstiDAO coopInstiDAO;

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<ProductModel> getProducts(long coopInstiId)
			throws AbstractCoopInstiException {
		PojoCoopInsti coopInsti = coopInstiDAO.get(coopInstiId);
		if (coopInsti == null) {
			throw new CoopInstiNotExistException(coopInstiId);
		}
		return coopInsti.getProducts();
	}
}
