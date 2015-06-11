package dev.misono.httpannotation.http;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * host 指向的是  <b>http://127.0.0.1:8080</b>/usr/{userid} 加粗部分
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Host {
    String value();
}
