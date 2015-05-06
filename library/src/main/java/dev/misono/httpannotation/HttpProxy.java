package dev.misono.httpannotation;

import android.content.Context;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import dev.misono.httpannotation.annotation.Field;
import dev.misono.httpannotation.annotation.Format;
import dev.misono.httpannotation.annotation.HttpMethod;
import dev.misono.httpannotation.annotation.TimeOut;
import dev.misono.httpannotation.annotation.URL;
import dev.misono.httpannotation.annotation.UrlEncoding;
import dev.misono.httpannotation.annotation.UserAgent;
import dev.misono.httpannotation.annotation.WithJson;

public class HttpProxy implements InvocationHandler {
    String host;
    HttpAnalyzer httpAnalyzer;

    public HttpProxy(String host, HttpAnalyzer httpAnalyzer) {
        this.host = host;
        this.httpAnalyzer = httpAnalyzer;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getDeclaringClass() == Object.class) {
            return method.invoke(this, args);
        }

        URL url = method.getAnnotation(URL.class);

        if (url == null) {
            throw new IllegalArgumentException("need Url annotation");
        }

        UrlEncoding urlEncoding = method.getAnnotation(UrlEncoding.class);
        UserAgent userAgent = method.getAnnotation(UserAgent.class);
        TimeOut timeOut = method.getAnnotation(TimeOut.class);
        HttpMethod httpMethod = method.getAnnotation(HttpMethod.class);

        HttpMethod.Type type = HttpMethod.Type.GET;
        if(httpMethod != null) {
            type = httpMethod.value();
        }

        HttpWorker httpWorker = new HttpWorker(host, url.value(), type);

        if (urlEncoding != null) {
            httpWorker.urlEncoding = urlEncoding.value();
        }
        if (userAgent != null) {
            httpWorker.userAgent = userAgent.value();
        }
        if (timeOut != null) {
            httpWorker.httpTimeOut = timeOut.value();
        }

        Annotation[][] annotations = method.getParameterAnnotations();

        Context ctx = null;
        HttpCallback callback = null;
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (arg instanceof Context) {
                ctx = (Context) arg;
            } else if (arg instanceof HttpCallback) {
                callback = (HttpCallback) arg;
            } else {
                Annotation[] paramAnnotations = annotations[i];
                if (paramAnnotations != null && paramAnnotations.length > 0) {
                    boolean toJson = false;
                    for (Annotation annotation : paramAnnotations) {
                        Class<? extends Annotation> annotationType =
                                annotation.annotationType();
                        if (annotationType == WithJson.class) {
                            WithJson withJson = (WithJson) annotation;
                            toJson = withJson.value();
                            break;
                        }
                    }

                    for (Annotation annotation : paramAnnotations) {
                        Class<? extends Annotation> annotationType =
                                annotation.annotationType();

                        if (annotationType == Field.class) {
                            Field field = (Field) annotation;
                            if (!toJson) {
                                httpWorker.params.put(field.value(), arg.toString());
                            } else {
                                httpWorker.params.put(field.value(),
                                        httpAnalyzer.toJson(arg));
                            }
                        } else if (annotationType == Format.class) {
                            Format format = (Format) annotation;

                            if (!toJson) {
                                httpWorker.formats.put(format.value(), arg.toString());
                            } else {
                                httpWorker.formats.put(format.value(), httpAnalyzer.toJson(arg));
                            }

                        }

                    }
                }
            }
        }

        httpAnalyzer.workWith(ctx, httpWorker, callback);
        return null;
    }
}
