package dev.misono.httpannotation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 是否将修饰参数转换为json字符串，
 * 如果不转换则默认调用参数的toString方法
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface WithJson {
    boolean value() default true;
}
