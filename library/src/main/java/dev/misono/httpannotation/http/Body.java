package dev.misono.httpannotation.http;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 指示post时使用的body对象，默认会使用json转换对象
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Body {
    Convert value() default Convert.ToJson;

    public static enum Convert {
        ToJson, ToString
    }
}
