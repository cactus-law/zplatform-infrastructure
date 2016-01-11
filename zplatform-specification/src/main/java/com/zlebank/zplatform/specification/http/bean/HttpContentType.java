package com.zlebank.zplatform.specification.http.bean;

public enum HttpContentType {
	FORM("application/x-www-form-urlencoded"),
	DEFAULT("application/x-www-form-urlencoded");
	private String code;
	public String getCode() {
		return code;
	}
	private HttpContentType(String code) {
		this.code = code;
	}
	public static HttpContentType fromValue(String value){
		for(HttpContentType httpContentType:values()){
			if(httpContentType.code.equals(value)){
				return httpContentType;
			}
		}
		return DEFAULT;
	}
}
