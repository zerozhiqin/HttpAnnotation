package dev.misono.httpannotation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 会使用注解参数的值替换URL中相应的value值
 * 例如URL ： 127.0.0.1/{version}/res/
 *
 * 使用@Format("version")即可将{version}替换为相应参数
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Format {
    String value();
}
