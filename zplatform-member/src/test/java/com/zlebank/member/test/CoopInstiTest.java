package com.zlebank.member.test;

import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.zlebank.zplatform.commons.dao.pojo.ProductModel;
import com.zlebank.zplatform.member.bean.CoopInsti;
import com.zlebank.zplatform.member.bean.CoopInstiMK;
import com.zlebank.zplatform.member.bean.enums.EncryptAlgorithm;
import com.zlebank.zplatform.member.bean.enums.TerminalAccessType;
import com.zlebank.zplatform.member.exception.AbstractCoopInstiException;
import com.zlebank.zplatform.member.service.CoopInstiProductService;
import com.zlebank.zplatform.member.service.CoopInstiService;

public class CoopInstiTest {

	private ApplicationContext context;
	private String instiName;
	private long instiId;

	@Before
	public void prepare() {
		context = ApplicationContextUtil.get();
		instiName = "测试机构" + String.valueOf(new Random().nextInt(100));
	}

	@Test
	@Ignore
	public void testCreate() {
		CoopInstiService coopInstiService = (CoopInstiService) context
				.getBean("coopInstiServiceImpl");
		String instiCode;
		try {
			// test add a new Coop Insti
			instiCode = coopInstiService.createCoopInsti(instiName, 2);
			Assert.assertTrue(instiCode.startsWith("3"));
			CoopInstiMK coopInstiMK = coopInstiService.getCoopInstiMK(
					instiCode, TerminalAccessType.WIRELESS);
			Assert.assertTrue(coopInstiMK != null);
			Assert.assertTrue(coopInstiMK.getTerminalAccessType() == TerminalAccessType.WIRELESS);
			Assert.assertTrue(coopInstiMK.getEncryptAlgorithm() == EncryptAlgorithm.RSA);
			Assert.assertTrue(coopInstiMK.getZplatformPubKey() != null);
			Assert.assertTrue(coopInstiMK.getZplatformPriKey() != null);
			Assert.assertTrue(coopInstiMK.getInstiPriKey() != null);
			Assert.assertTrue(coopInstiMK.getInstiPubKey() != null);
		} catch (AbstractCoopInstiException e) {
			e.printStackTrace();
			Assert.fail();
		}

		// test if name repeat
		try {
			instiCode = coopInstiService.createCoopInsti(instiName, 2);
		} catch (AbstractCoopInstiException e) {
			Assert.assertEquals(e.getCode(), "EMS020001");
		}
	}

	@Test
	public void testGetCoopProduct() {
		CoopInstiProductService coopInstiService = (CoopInstiProductService) context
				.getBean("coopInstiProductServiceImpl");
		instiId = 24;
		try {
			List<ProductModel> products = coopInstiService
					.getProducts(instiId);
			Assert.assertTrue(products != null && products.size() != 0);
		} catch (AbstractCoopInstiException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void testGetAllCoopInsti() {
		CoopInstiService coopInstiService = (CoopInstiService) context
				.getBean("coopInstiServiceImpl");
		List<CoopInsti> coopInstis = coopInstiService.getAllCoopInsti();
		Assert.assertTrue(coopInstis != null && coopInstis.size() != 0);
	}
}
