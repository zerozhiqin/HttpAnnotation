package dev.misono.httpannotation;

import java.lang.reflect.Proxy;

import dev.misono.httpannotation.annotation.Host;

//Adapter


public class HttpAdapter {
    HttpAnalyzer httpAnalyzer;

    public static HttpAdapter with(HttpAnalyzer httpAnalyzer) {
        HttpAdapter adapter = new HttpAdapter();
        adapter.httpAnalyzer = httpAnalyzer;
        return adapter;
    }

    public <T> T create(Class<T> clazz) {
        Host host = clazz.getAnnotation(Host.class);
        String h = host.value();

        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, new HttpProxy(h, httpAnalyzer));
    }

}
