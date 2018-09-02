package com.winterchen.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
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
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * Created by chendd on 2017/5/19.
 */
public class HttpUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    private static CloseableHttpClient httpClient = null;
    private static int MAX_CONNECTION_NUM = 400;
    private static int MAX_PER_ROUTE = 80;
    private static PoolingHttpClientConnectionManager cm = null;
    private static Object LOCAL_LOCK = new Object();

    private static RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(500)
            .setConnectTimeout(500)
            .setConnectionRequestTimeout(500)
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
}
