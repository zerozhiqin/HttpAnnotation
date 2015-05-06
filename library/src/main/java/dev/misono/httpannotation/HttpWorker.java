package dev.misono.httpannotation;

import android.support.v4.util.ArrayMap;

import java.util.Iterator;

import dev.misono.httpannotation.annotation.HttpMethod;

/**
 * 包装了注解翻译后的请求相关内容
 */
public class HttpWorker {

    public HttpWorker(String host, String url, HttpMethod.Type requestType) {
        this.host = host;
        this.url = url;
        this.requestType = requestType;
    }

    public String getRequestUrl() {
        String reqUrl = host + url;

        Iterator<String> iterator = formats.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String vaule = formats.get(key);
            reqUrl = reqUrl.replace("{" + key + "}", vaule);
        }

        return reqUrl;
    }

    private String host;
    private String url;

    public HttpMethod.Type requestType;

    public ArrayMap<String, String> formats = new ArrayMap<>();
    public ArrayMap<String, String> params = new ArrayMap<>();

    public boolean urlEncoding = false;
    public String userAgent = null;
    public long httpTimeOut = 30000;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(getRequestUrl());
        if (!params.isEmpty()) {
            char rec = '?';
            for (int i = 0; i < params.size(); i++) {
                sb.append(rec).append(params.keyAt(i)).append("=").append(params.valueAt(i));
                rec = '&';
            }
        }

        return sb.toString();
    }
}
