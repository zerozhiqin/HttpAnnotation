package dev.misono.sample.withasynchttp;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;

import dev.misono.httpannotation.HttpAnalyzer;
import dev.misono.httpannotation.HttpCallback;
import dev.misono.httpannotation.HttpWorker;
import dev.misono.httpannotation.annotation.HttpMethod;

public class AsyncHttpAnalyzer implements HttpAnalyzer {

    Gson gson;

    public AsyncHttpAnalyzer() {
        gson = new Gson();
    }

    @Override
    public String toJson(Object obj) {
        return gson.toJson(obj);
    }

    @Override
    public void workWith(Context ctx, HttpWorker httpWorker, final HttpCallback callback) {

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

        RequestParams requestParams = new RequestParams();
        if (!httpWorker.params.isEmpty()) {
            for (int i = 0; i < httpWorker.params.size(); i++) {
                requestParams.add(httpWorker.params.keyAt(i), httpWorker.params.valueAt(i));
            }
        }

        ResponseHandlerInterface responseHandlerInterface = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200) {
                    try {
                        callback.callback(new String(responseBody, 0,responseBody.length, "UTF-8"), null);
                    } catch (UnsupportedEncodingException e) {
                        callback.callback(null, e);
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if(callback != null) {
                    callback.callback(null, new Exception(error.getLocalizedMessage()));
                }
            }
        };

        Log.v("AHA" , httpWorker.getRequestUrl());

        if (httpWorker.userAgent != null) {
            asyncHttpClient.setUserAgent(httpWorker.userAgent);
        }

        asyncHttpClient.setTimeout((int) httpWorker.httpTimeOut);
        if (httpWorker.requestType == HttpMethod.Type.GET) {
            asyncHttpClient.get(ctx, httpWorker.getRequestUrl(), requestParams, responseHandlerInterface)
            ;
        } else {
            asyncHttpClient.post(ctx, httpWorker.getRequestUrl(), requestParams, responseHandlerInterface);
        }
    }
}
