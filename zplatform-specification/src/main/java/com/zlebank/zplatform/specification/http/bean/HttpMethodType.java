package com.zlebank.zplatform.specification.http.bean;
 
public enum HttpMethodType {
	
	GET("GET"),
	POST("POST"),
	DEFAULT("GET");
	private String code;
	public String getCode() {
		return code;
	}
	private HttpMethodType(String code) {
		this.code = code;
	}
	public static HttpMethodType fromValue(String value){
		for(HttpMethodType httpMethodType:values()){
			if(httpMethodType.code.equals(value)){
				return httpMethodType;
			}
		}
		return DEFAULT;
	}
}
