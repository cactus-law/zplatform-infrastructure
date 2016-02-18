package com.zlebank.zplatform.specification.exception;


public class HttpRequestErrResCodeException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4668948945228428153L;
	
	private int httpResCode;
	public HttpRequestErrResCodeException(int httpResCode){
	    super();
	    this.httpResCode = httpResCode;
	}
	
	public int getHttpResCode(){
	    return httpResCode;
	}
}
