package com.zlebank.member2;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONStringer;
import net.sf.json.util.JSONUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.zlebank.member.test.ApplicationContextUtil;
import com.zlebank.zplatform.commons.dao.pojo.ProductModel;
import com.zlebank.zplatform.commons.test.RandomUtil;
import com.zlebank.zplatform.member.bean.CoopInsti;
import com.zlebank.zplatform.member.bean.CoopInstiMK;
import com.zlebank.zplatform.member.bean.enums.EncryptAlgorithm;
import com.zlebank.zplatform.member.bean.enums.TerminalAccessType;
import com.zlebank.zplatform.member.dao.CoopInstiDAO;
import com.zlebank.zplatform.member.dao.impl.CoopInstiDAOImpl;
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
		instiName = "测试机构" + RandomUtil.randomNumber(4);
	}
	

	//@Test
	public void testCreate() {
		CoopInstiService coopInstiService = (CoopInstiService) context
				.getBean("coopInstiService");
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
	@Ignore
	public void testGetCoopProduct() {
		CoopInstiProductService coopInstiService = (CoopInstiProductService) context
				.getBean("coopInstiProductServiceImpl");
		try {
			// test there is no product related to coop insti
			instiId = 27;
			List<ProductModel> products = coopInstiService.getProducts(instiId);
			Assert.assertTrue(products != null && products.size() == 0);

			// test there are products related to coop insti
			instiId = 25;
			products = coopInstiService.getProducts(instiId);
			Assert.assertTrue(products != null && products.size() != 0);
		} catch (AbstractCoopInstiException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	@Ignore
	public void testGetAllCoopInsti() {
		CoopInstiService coopInstiService = (CoopInstiService) context
				.getBean("coopInstiServiceImpl");
		List<CoopInsti> coopInstis = coopInstiService.getAllCoopInsti();
		Assert.assertTrue(coopInstis != null && coopInstis.size() != 0);
	}

	@Test
	//@Ignore
	public void testGetCoopInstiByInstiCode() {
		CoopInstiService coopInstiService = (CoopInstiService) context
				.getBean("coopInstiServiceImpl");
		CoopInsti coopInstis = coopInstiService
				.getInstiByInstiCode("300000000000014");
		Assert.assertTrue(coopInstis != null
				&& coopInstis.getInstiCode().equals("300000000000014"));
	}
}
