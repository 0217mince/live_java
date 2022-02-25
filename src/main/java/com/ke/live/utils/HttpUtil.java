package com.ke.live.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;
import java.util.Random;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Created by WALY on 2016/4/18.
 */
public class HttpUtil {
    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

    public static String httpPostAsString(String urlStr,String data){
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.addRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.getOutputStream().write(data.getBytes("UTF-8"));// 输入参数
            InputStream inStream = connection.getInputStream();
            return getStreamString(inStream);
        } catch (MalformedURLException e) {
            log.error("httpPostAsString error, urlStr[{}], data[{}]", urlStr, data, e);
        } catch (ProtocolException e) {
            log.error("httpPostAsString error, urlStr[{}], data[{}]", urlStr, data, e);
        } catch (IOException e) {
            log.error("httpPostAsString error, urlStr[{}], data[{}]", urlStr, data, e);
        }
        return null;
    }
    /*
	 * 将输入流转为字符串
	 */
    public static String getStreamString(InputStream tInputStream){
        if (tInputStream != null){
            try{
                BufferedReader tBufferedReader = new BufferedReader(new InputStreamReader(tInputStream));
                StringBuffer tStringBuffer = new StringBuffer();
                String sTempOneLine;
                while ((sTempOneLine = tBufferedReader.readLine()) != null){
                    tStringBuffer.append(sTempOneLine);
                }
                return tStringBuffer.toString();
            }catch (Exception ex){
                ex.printStackTrace();
            }finally {
                try {
                    tInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    /**
     * 获取一定长度的随机字符串
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     *
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static Map<String, Object> httpsGet(String url) {
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet get = new HttpGet(url);
            HttpResponse response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity);
            Map<String, Object> map = JSONUtils.parse(content.toString(), Map.class);
            return map;
        } catch (IOException e){

        }
        return null;
    }


    public static String doGet(String urlPath, HashMap<String, Object> params)
            throws Exception {
        StringBuilder sb = new StringBuilder(urlPath);
        if (params != null && !params.isEmpty()) { // 说明有参数
            sb.append("?");

            Set<Entry<String, Object>> set = params.entrySet();
            for (Entry<String, Object> entry : set) { // 遍历map里面的参数
                String key = entry.getKey();
                String value = "";
                if (null != entry.getValue()) {
                    value = entry.getValue().toString();
                    // 转码
                    value = URLEncoder.encode(value, "UTF-8");
                }
                sb.append(key).append("=").append(value).append("&");
            }

            sb.deleteCharAt(sb.length() - 1); // 删除最后一个&
        }
        // System.out.println(sb.toString());
        URL url = new URL(sb.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000); // 5s超时
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() == HttpStatus.SC_OK) {// HttpStatus.SC_OK ==
            // 200
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            StringBuilder sbs = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sbs.append(line);
            }
            return sbs.toString();
        }

        return null;
    }
}
