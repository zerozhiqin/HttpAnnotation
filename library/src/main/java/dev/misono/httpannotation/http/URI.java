package dev.misono.httpannotation.http;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * method {@link dev.misono.httpannotation.http.URI.HttpMethod}<br/>
 * uri 指向的是  http://127.0.0.1:8080<b>/usr/{userid}</b></> 加粗部分
 *
 *
 * eg: @URI(method = POST, uri = "/usr/{userid}")
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface URI {
    HttpMethod method();
    String uri();


    public static enum HttpMethod {
        GET, POST, DELETE, PUT
    }
}
