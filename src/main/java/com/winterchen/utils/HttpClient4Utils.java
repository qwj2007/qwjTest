/*
 * @(#) HttpClientUtils.java 2016年2月3日
 * 
 * Copyright 2010 NetEase.com, Inc. All rights reserved.
 */
package com.winterchen.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * HttpClient工具类
 * @author hzgaomin
 * @version 2016年2月3日
 */
public class HttpClient4Utils {
    /**
     * 实例化HttpClient
     * @param maxTotal
     * @param maxPerRoute
     * @param socketTimeout
     * @param connectTimeout
     * @param connectionRequestTimeout
     * @return
     */
    public static HttpClient createHttpClient(int maxTotal, int maxPerRoute, int socketTimeout, int connectTimeout,
                    int connectionRequestTimeout) {
        RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout)
                        .setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectionRequestTimeout).build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(maxTotal);
        cm.setDefaultMaxPerRoute(maxPerRoute);
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm)
                        .setDefaultRequestConfig(defaultRequestConfig).build();
        return httpClient;
    }
    // 加线程池的 httpclient  get请求============================================
    private static Logger logger = LoggerFactory.getLogger(HttpClient4Utils.class);
    private static CloseableHttpClient httpClient = null;
    private static int MAX_CONNECTION_NUM = 400;
    private static int MAX_PER_ROUTE = 80;
    private static PoolingHttpClientConnectionManager cm = null;
    private static Object LOCAL_LOCK = new Object();

    private static RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(2000)
            .setConnectTimeout(2000)
            .setConnectionRequestTimeout(2000)
            .build();

    private static PoolingHttpClientConnectionManager getPoolManager() {
        final String methodName = "getPoolManager";
        logger.info(methodName, "initPoolManager");
        if (null == cm) {
            synchronized (LOCAL_LOCK) {
                if (null == cm) {
                    SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
                    try {
                        sslContextBuilder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
                        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
                                sslContextBuilder.build());
                        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                                .register("https", socketFactory)
                                .register("http", new PlainConnectionSocketFactory())
                                .build();
                        cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
                        cm.setMaxTotal(MAX_CONNECTION_NUM);
                        cm.setDefaultMaxPerRoute(MAX_PER_ROUTE);
                    } catch (Exception e) {
                        logger.error(methodName, "init PoolingHttpClientConnectionManager Error" + e);
                    }
                }
            }
        }
        logger.info(methodName, "initPoolManager");
        return cm;
    }

    public static CloseableHttpClient getHttpsClient(RequestConfig config) {
        final String methodName = "getHttpsClient";
        logger.info(methodName, "initHttpsClient");
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(config)
                .setConnectionManager(getPoolManager())
                .build();
        logger.info(methodName, "initHttpsClient");
        return httpClient;
    }

    /**
     * 发送Get请求
     *
     * @param url
     * @return
     */
    public static String sendHttpGet(String url) {
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        HttpGet httpGet = null;
        try {
            httpClient = getHttpsClient(requestConfig);
            httpGet = new HttpGet(url);
            httpGet.setConfig(requestConfig);
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
            response.close();
        } catch (Exception e) {
            if (e instanceof SocketTimeoutException) {
                logger.error("server request time out");
            } else if (e instanceof ConnectTimeoutException) {
                logger.error("server response time out");
            }
            logger.error(e.getMessage());
        } finally {
            httpGet.releaseConnection();
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                    response.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
        return responseContent;
    }
    // 有线程池的httpclient post请求=========================================================
    /**
     * 发送post请求
     * @param httpClient
     * @param url 请求地址
     * @param params 请求参数
     * @param encoding 编码
     * @return
     */
    public static String sendPost(HttpClient httpClient, String url, Map<String, String> params, Charset encoding) {
        String resp = "";
        HttpPost httpPost = new HttpPost(url);
        if (params != null && params.size() > 0) {
            List<NameValuePair> formParams = new ArrayList<NameValuePair>();
            Iterator<Map.Entry<String, String>> itr = params.entrySet().iterator();
            while (itr.hasNext()) {
                Map.Entry<String, String> entry = itr.next();
                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(formParams, encoding);
            httpPost.setEntity(postEntity);
        }
        CloseableHttpResponse response = null;
        try {
            response = (CloseableHttpResponse) httpClient.execute(httpPost);
            resp = EntityUtils.toString(response.getEntity(), encoding);
        } catch (Exception e) {
            // log
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    // log
                    e.printStackTrace();
                }
            }
        }
        return resp;
    }
}
