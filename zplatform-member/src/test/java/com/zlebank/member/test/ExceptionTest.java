package com.zlebank.member.test;

import com.zlebank.zplatform.acc.exception.AccountNotExistException;
import com.zlebank.zplatform.member.exception.CoopInstiNotExistException;

public class ExceptionTest {
	
	public void test(){
		Exception exception = new AccountNotExistException();
		System.out.println(exception.getMessage());
		
		exception = new CoopInstiNotExistException("2131231321");
		System.out.println(exception.getMessage());
	}
}
