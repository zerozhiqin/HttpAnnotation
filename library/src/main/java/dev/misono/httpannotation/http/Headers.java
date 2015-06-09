package dev.misono.httpannotation.http;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 根据头文件修改</br>
 * 例如 @Headers( {
 *          "Content-Type", "application/x-www-form-urlencoded",
 *          "Set-Cookie", "sc=12345; path=/; domain=.acookie.xxx.com",
 *          "User-Agent","uid-902839210"
 *      })
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Headers {
    String[] value();
}
