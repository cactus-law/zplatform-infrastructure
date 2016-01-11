package com.zlebank.zplatform.specification.http.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zlebank.zplatform.specification.exception.HttpRequestErrResCodeException;
import com.zlebank.zplatform.specification.http.HttpProxy;
import com.zlebank.zplatform.specification.http.bean.HttpContentType;
import com.zlebank.zplatform.specification.http.bean.HttpMethodType;

public class HttpProxyImpl implements HttpProxy {
    private static Log log = LogFactory.getLog(HttpProxyImpl.class);
    private URL url;
    private HttpURLConnection httpConnection;
    private HttpMethodType httpMethodType;
    private HttpContentType httpContentType;
    private String httpParameters;
    protected String resMessage;

    public HttpProxyImpl() {
        httpParameters = "";
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public void setMethod(HttpMethodType httpMethodType) {
        this.httpMethodType = httpMethodType;
    }

    public void setContentType(HttpContentType httpContentType) {
        this.httpContentType = httpContentType;
    }

    public String sendHttp(Map<String, String> httpParameters)
            throws IOException, HttpRequestErrResCodeException {
        return sendHttp(httpParameters, false);
    }

    public String sendHttp(Map<String, String> httpParameters,
            boolean isURLEncoder) throws IOException,
            HttpRequestErrResCodeException {
        Iterator<Entry<String, String>> iterator = httpParameters.entrySet()
                .iterator();
        while (iterator.hasNext()) {
            Entry<String, String> currentPara = iterator.next();
            if (this.httpParameters.equals("")) {
                this.httpParameters += currentPara.getKey()
                        + "="
                        + (isURLEncoder ? URLEncoder.encode(
                                currentPara.getValue(), "utf-8") : currentPara
                                .getValue());
            } else {
                this.httpParameters += "&"
                        + currentPara.getKey()
                        + "="
                        + (isURLEncoder ? URLEncoder.encode(
                                currentPara.getValue(), "utf-8") : currentPara
                                .getValue());
            }
        }
        loadHttpPorperties();

        httpConnection.connect();
        httpConnection.getOutputStream().write(this.httpParameters.getBytes());

        StringBuilder sb = new StringBuilder();
        sb.append("send to:");
        sb.append(httpConnection.getURL());
        sb.append("\n");
        sb.append(URLDecoder.decode(this.httpParameters,"utf-8"));
        log.info(sb.toString());

        httpConnection.getOutputStream().flush();
        httpConnection.getOutputStream().close();
        httpConnection.connect();
        int statusCode = httpConnection.getResponseCode();
        BufferedReader reader;
        if (200 == statusCode) {
            reader = new BufferedReader(new InputStreamReader(
                    httpConnection.getInputStream(), "utf-8"));
        } else {
            log.info("http请求失败返回,http状态码:" + statusCode);
            throw new HttpRequestErrResCodeException(statusCode);
        }

        String resMessage;
        while ((resMessage = reader.readLine()) != null) {
            this.resMessage = URLDecoder.decode(resMessage, "utf-8");
        }

        sb = new StringBuilder();
        sb.append("revice from:");
        sb.append(httpConnection.getURL());
        sb.append("\n");
        sb.append(this.resMessage);
        log.info(sb.toString());
        reader.close();
        httpConnection.disconnect();

        return this.resMessage;
    }

    private void loadHttpPorperties() throws IOException {
        httpConnection = (HttpURLConnection) this.url.openConnection();
        httpConnection.setRequestProperty("Cache-Control", "no-cache");
        httpConnection.setRequestProperty("Content-Type",
                this.httpContentType.getCode());
        httpConnection.setRequestMethod(this.httpMethodType.getCode());
        httpConnection.setDoInput(true);
        httpConnection.setDoOutput(true);
    }

}
