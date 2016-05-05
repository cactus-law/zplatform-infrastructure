package com.zlebank.member.test;

import org.junit.Test;

import com.zlebank.zplatform.acc.exception.AccountNotExistException;
import com.zlebank.zplatform.member.exception.CoopInstiNotExistException;

class ExceptionTest {
	@Test
	public void test(){
		Exception exception = new AccountNotExistException();
		System.out.println(exception.getMessage());
		
		exception = new CoopInstiNotExistException("2131231321");
		System.out.println(exception.getMessage());
	}
}
