/* 
 * TestException.java  
 * 
 * version 1.0
 *
 * 2015年8月24日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zlpatform.acc.exception;

import org.junit.Test;

import com.zlebank.zplatform.acc.exception.AbstractAccException;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年8月24日 上午11:04:06
 * @since 
 */
class TestException extends AbstractAccException{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7707178815030998608L;

	/**
	 *
	 * @return
	 */
	@Override
	public String getCode() {
		 
		return "EASBA0002";
	}
	
	@Test
	public void test(){
		TestException testException = new TestException();
		testException.setParams(20);
		System.out.println(testException.getMessage());
	}
}
