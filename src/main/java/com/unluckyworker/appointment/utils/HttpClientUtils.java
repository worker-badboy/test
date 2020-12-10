package com.unluckyworker.appointment.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HttpClientUtils {
    public static JSONObject doGet(String url) {
        HttpURLConnection conn = null;
        StringBuilder content = new StringBuilder();
        try {
            URL url1 = new URL(url);
            conn = (HttpURLConnection) url1.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("Accept-Language", "zh-CN");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
            conn.setRequestProperty("Proxy-Connection", "Keep-Alive");
            conn.connect();
            Reader reader = null;
            InputStream in = conn.getInputStream();
            reader = new InputStreamReader(in);
            char[] buffer = new char[1024];
            int head = 0;
            while ((head = reader.read(buffer)) > 0) {
                content.append(new String(buffer, 0, head));
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(content.toString());
    }
}

