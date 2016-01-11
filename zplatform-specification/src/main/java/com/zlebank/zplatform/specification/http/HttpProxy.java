package com.zlebank.zplatform.specification.http;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import com.zlebank.zplatform.specification.exception.HttpRequestErrResCodeException;
import com.zlebank.zplatform.specification.http.bean.HttpContentType;
import com.zlebank.zplatform.specification.http.bean.HttpMethodType;
/**
 * http代理类，包装了java.net.HttpURLConnection类来处理http请求和响应
 * @author yangying
 *
 */
public interface HttpProxy {
	/**
	 * 设置http请求发送的URL
	 * @param url
	 */
	public void setUrl(URL url);
	/**
	 * 设置http请求发送的方法 
	 * @param httpMethodType
	 */
	public void setMethod(HttpMethodType httpMethodType);
	 
	/**
	 * 设置http请求类型
	 * @param httpMethodType
	 */
	public void setContentType(HttpContentType httpContentType);
	/**
	 *  发送http请求  
	 * @param paraValue   需要发送的参数名-值对
	 * @param httpParameters
	 * @throws IOException
	 * @throws HttpRequestErrResCodeException
	 * @return remote response message
	 */
	public String sendHttp(Map<String,String> httpParameters) throws IOException, HttpRequestErrResCodeException;
	
	/**
	 *  发送http请求  
	 * @param paraValue   需要发送的参数名-值对
	 * @param isURLEncoder 是否需要URLEncoder编码处理
	 * @throws IOException
	 * @throws HttpRequestErrResCodeException
	 * @return remote response message
	 */
	public String sendHttp(Map<String,String> httpParameters,boolean isURLEncoder)throws IOException,HttpRequestErrResCodeException;
	
}
